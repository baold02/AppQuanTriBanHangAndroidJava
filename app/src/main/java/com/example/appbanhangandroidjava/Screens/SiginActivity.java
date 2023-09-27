package com.example.appbanhangandroidjava.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangandroidjava.MainActivity;
import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.retrofits.APIBanHang;
import com.example.appbanhangandroidjava.retrofits.Retrofitclient;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SiginActivity extends AppCompatActivity {
    EditText edtAccount, edtPass;
    TextView edtSignup;
    Button btnSignin;
    APIBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin);
        initView();
        initControl();
    }

    private void initControl() {

      edtSignup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent SignUpActivity = new Intent(SiginActivity.this,RigisterActivity.class);
              startActivity(SignUpActivity);
          }
      });

      btnSignin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                String str_email = edtAccount.getText().toString().trim();
                String str_pass = edtPass.getText().toString().trim();
                Paper.book().write("email", str_email);
                Paper.book().write("pass", str_pass);
                if(str_email.isEmpty()){
                    Toast.makeText(SiginActivity.this, "Email cannot empty", Toast.LENGTH_SHORT).show();
                }else if(str_pass.isEmpty()){
                    Toast.makeText(SiginActivity.this, "Passworld cannot empty", Toast.LENGTH_SHORT).show();
                }else {
                    Sigin(str_email,str_pass);

                }
          }
      });
    }

    private void initView(){
        Paper.init(this);
        apiBanHang = Retrofitclient.getInstance(Utils.BASE_URL).create(APIBanHang.class);
        edtAccount = findViewById(R.id.edtAccount);
        edtPass = findViewById(R.id.edtPass);
        edtSignup = findViewById(R.id.signupText);
        btnSignin = findViewById(R.id.btnSignin);

        if(Paper.book().read("email") != null && Paper.book().read("pass") != null ){
            edtAccount.setText(Paper.book().read("email"));
            edtAccount.setText(Paper.book().read("pass"));
            if(Paper.book().read("islogin") != null){
                boolean flag = Paper.book().read("islogin");
                if (flag){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Sigin(Paper.book().read("email"),Paper.book().read("pass"));
                        }
                    },1000);
                }
            }
        }
    }

    private void Sigin(String str_email, String str_pass) {
        compositeDisposable.add(apiBanHang.signin(str_email,str_pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){
                                isLogin = true;
                                 Paper.book().write("islogin", isLogin);

                                Utils.user_current = userModel.getReslut().get(0);
                                Toast.makeText(SiginActivity.this, "Sigin successfuly", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SiginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(this, "Email or Pass wrong!!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable ->{
                            Toast.makeText(SiginActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_current.getEmail() != null && Utils.user_current.getPass() != null){
            edtAccount.setText(Utils.user_current.getEmail());
            edtAccount.setText(Utils.user_current.getPass());
        }else {

        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}