package com.example.appbanhangandroidjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appbanhangandroidjava.Screens.LapTopActivity;
import com.example.appbanhangandroidjava.Screens.PhoneActivity;
import com.example.appbanhangandroidjava.Screens.ViewHistoryActivity;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.adapters.LoaiSpAdapter;
import com.example.appbanhangandroidjava.adapters.PhoneAdapter;
import com.example.appbanhangandroidjava.adapters.SanPhamNewAdapter;
import com.example.appbanhangandroidjava.models.LoaiSp;
import com.example.appbanhangandroidjava.models.SanPhamNew;
import com.example.appbanhangandroidjava.models.SanPhamNewModel;
import com.example.appbanhangandroidjava.retrofits.APIBanHang;
import com.example.appbanhangandroidjava.retrofits.Retrofitclient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ListView lvLoai;
    RecyclerView rcvNewSp;
    LoaiSpAdapter adapter;
    List<LoaiSp> loaiSp;

    PhoneAdapter phoneAdapter;
    List<SanPhamNew> sanPhamNew;
    SanPhamNewAdapter sanPhamNewAdapter;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanHang apiBanHang;
    NotificationBadge notificationBadge;

    EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang = Retrofitclient.getInstance(Utils.BASE_URL).create(APIBanHang.class);
        init();
        getToken();
        if (isConnect(this)){
            Toast.makeText(this, "Connect network success", Toast.LENGTH_SHORT).show();
            getLoaiSp();
            getSpNew();
            getEventClick();
        }else {
            Toast.makeText( this, "Connect network faile", Toast.LENGTH_SHORT).show();
        }
    }



    private void getSpNew() {
        compositeDisposable.add(apiBanHang.getSanPhamNew()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamNewModel -> {
                            sanPhamNew = sanPhamNewModel.getReslut();
                            sanPhamNewAdapter = new SanPhamNewAdapter(sanPhamNew,this);
                            rcvNewSp.setAdapter(sanPhamNewAdapter);
                        }
                )
        );
    }

    private void getLoaiSp() {
        compositeDisposable.add(apiBanHang.getLoaiSanPham()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSpModel -> {
                            if (loaiSpModel.isSuccess()){
                                loaiSp = loaiSpModel.getReslut();
                                adapter = new LoaiSpAdapter(loaiSp,this);
                                lvLoai.setAdapter(adapter);
                                Toast.makeText(this, loaiSpModel.getReslut().get(0).getTensanpham(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    private void init(){
        apiBanHang = Retrofitclient.getInstance(Utils.BASE_URL).create(APIBanHang.class);
         edtSearch = findViewById(R.id.edtSearch);
         edtSearch.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if(s.length() == 0){
                     sanPhamNew.clear();
                     phoneAdapter = new PhoneAdapter(getApplicationContext(),sanPhamNew);
                     rcvNewSp.setAdapter(phoneAdapter);
                 }
                 getDataSearch(s.toString());

             }

             @Override
             public void afterTextChanged(Editable s) {
             }
         });
        TextView textView = findViewById(R.id.tvOder);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewHistoryActivity.class);
                startActivity(intent);
            }
        });
        lvLoai = findViewById(R.id.lvLoai);
        rcvNewSp = findViewById(R.id.rcvSpNew);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        rcvNewSp.setLayoutManager(layoutManager);
        rcvNewSp.setHasFixedSize(true);
        loaiSp = new ArrayList<>();
        sanPhamNew = new ArrayList<>();
        if (Utils.mangGioHang == null){
            Utils.mangGioHang = new ArrayList<>();
        }


    }

    private void getDataSearch(String s) {
        sanPhamNew.clear();
        compositeDisposable.add(apiBanHang.findProduct(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamNewModel -> {
                            if ((sanPhamNewModel.isSuccess())) {
                                sanPhamNew = sanPhamNewModel.getReslut();
                                    phoneAdapter = new PhoneAdapter(getApplicationContext(),sanPhamNew);
                                    rcvNewSp.setAdapter(phoneAdapter);
                            }
                        },
                        throwable -> {

                        }
                ));
    }


    private void getToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>(){
                    @Override
                    public void onSuccess(String s) {
                             if(!TextUtils.isEmpty(s)){
                                compositeDisposable.add(apiBanHang.updatetoken(Utils.user_current.getId(),s)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(
                                                messageModel -> {

                                                },
                                                throwable -> {

                                                }
                                        ));
                             }
                    }
                });
    }

    private void getEventClick(){
        lvLoai.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent2 = new Intent(MainActivity.this, PhoneActivity.class);
                        intent2.putExtra("loai",1);
                        startActivity(intent2);
                        break;
                    case 1:
                        Intent intent = new Intent(MainActivity.this, LapTopActivity.class);
                        intent.putExtra("loai",2);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private boolean isConnect(Context context){
        ConnectivityManager  connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile =connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())){
            return  true;
        }else {
            return  false;
        }

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}