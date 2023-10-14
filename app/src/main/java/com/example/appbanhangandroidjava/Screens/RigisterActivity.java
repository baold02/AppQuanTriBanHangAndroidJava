package com.example.appbanhangandroidjava.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.retrofits.APIBanHang;
import com.example.appbanhangandroidjava.retrofits.Retrofitclient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RigisterActivity extends AppCompatActivity {

    EditText edtEmail, edtPass, edtUsername, edtPhoneNumber;
    Button btnRigister;
    TextView txtSigin;
    APIBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        init();
        initControl();
    }

    private void initControl(){

        txtSigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent siginActivity = new Intent(RigisterActivity.this,SiginActivity.class);
                startActivity(siginActivity);
                finish();
            }
        });
        btnRigister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String str_email = edtEmail.getText().toString().trim();
        String str_pass = edtPass.getText().toString().trim();
        String str_username = edtUsername.getText().toString().trim();
        String str_phone = edtPhoneNumber.getText().toString().trim();
        
        if (str_email.isEmpty()){
            Toast.makeText(this, "Email cannot empty", Toast.LENGTH_SHORT).show();
        }else if(str_pass.isEmpty()){
            Toast.makeText(this, "Passworld cannot empty", Toast.LENGTH_SHORT).show();
        }else if(str_username.isEmpty()){
            Toast.makeText(this, "Username cannot empty", Toast.LENGTH_SHORT).show();
        }else if(str_phone.isEmpty()){
            Toast.makeText(this, "Phone number cannot empty", Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(str_email,str_pass)
                    .addOnCompleteListener(RigisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              FirebaseUser user = firebaseAuth.getCurrentUser();
                              if(user != null){
                                  postData(str_email,str_pass,str_username,str_phone, user.getUid());
                              }
                          }else {
                              Toast.makeText(RigisterActivity.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                          }
                        }
                    });
        }


    }

    private void postData(String str_email,String str_pass,String str_username,String str_phone, String uid){
        compositeDisposable.add(apiBanHang.Rigister(str_email,str_pass,str_username,str_phone,uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){
                                Utils.user_current.setEmail(str_email);
                                Utils.user_current.setPass(str_pass);
                                Intent intent = new Intent(RigisterActivity.this, SiginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(this, "Creat new account successfuly", Toast.LENGTH_SHORT).show();

                            }else {
                                Toast.makeText(this, userModel.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                )
        );
    }


    private void init(){
        apiBanHang = Retrofitclient.getInstance(Utils.BASE_URL).create(APIBanHang.class);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        edtUsername = findViewById(R.id.edtUsername);
        edtPhoneNumber = findViewById(R.id.edtPhone);
        txtSigin = findViewById(R.id.siginText);
        btnRigister = findViewById(R.id.btnRigister);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}