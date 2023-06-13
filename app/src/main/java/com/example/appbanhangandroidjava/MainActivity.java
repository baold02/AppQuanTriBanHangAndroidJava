package com.example.appbanhangandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.adapters.LoaiSpAdapter;
import com.example.appbanhangandroidjava.models.LoaiSp;
import com.example.appbanhangandroidjava.retrofits.APIBanHang;
import com.example.appbanhangandroidjava.retrofits.Retrofitclient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    ListView lvLoai;
    LoaiSpAdapter adapter;
    List<LoaiSp> loaiSp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBanHang apiBanHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang = Retrofitclient.getInstance(Utils.BASE_URL).create(APIBanHang.class);
        init();
        if (isConnect(this)){
            Toast.makeText(this, "Connect network success", Toast.LENGTH_SHORT).show();
            getLoaiSp();
        }else {
            Toast.makeText(this, "Connect network faile", Toast.LENGTH_SHORT).show();
        }
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
        lvLoai = findViewById(R.id.lvLoai);
        loaiSp = new ArrayList<>();
        loaiSp.add(new LoaiSp("Dien thoai","ssss"));

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