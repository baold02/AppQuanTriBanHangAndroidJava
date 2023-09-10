package com.example.appbanhangandroidjava.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.retrofits.APIBanHang;
import com.example.appbanhangandroidjava.retrofits.Retrofitclient;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RigisterActivity extends AppCompatActivity {

    EditText edtEmail, edtPass, edtUsername, edtPhoneNumber;
    Button btnRigister;
    APIBanHang apiBanHang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister);
        init();
        initControl();
    }

    private void initControl(){
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

        compositeDisposable.add(apiBanHang.Rigister(str_email,str_pass,str_username,str_phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                           if(userModel.isSuccess()){
                               Utils.user_current.setEmail(str_email);
                               Utils.user_current.setPass(str_pass);
                               Intent intent = new Intent(RigisterActivity.this, SignupActivity.class);
                               startActivity(intent);
                               finish();
                               Toast.makeText(this, "Tao tai khoan thanh cong", Toast.LENGTH_SHORT).show();

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
        btnRigister = findViewById(R.id.btnRigister);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}