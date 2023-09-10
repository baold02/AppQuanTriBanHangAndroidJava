package com.example.appbanhangandroidjava.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.models.GioHang;
import com.example.appbanhangandroidjava.models.SanPhamNew;
import com.nex3z.notificationbadge.NotificationBadge;

public class DetailsProductActivity extends AppCompatActivity {
    ImageView imgChiTiet;
    TextView tvname, tvPrice,tvMoTa;
    Button btnAddToCart;
    Toolbar toolbarl;
    Spinner spinner;
    SanPhamNew zSanPhamNew;
    NotificationBadge notificationBadge;

    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_phone);
        initUI();
//        ActionToolBar();
        initData();
        initControl();
    }

    private void initData() {
         zSanPhamNew = (SanPhamNew) getIntent().getSerializableExtra("chitiet");
        tvname.setText(zSanPhamNew.getTensp());
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this, com.airbnb.lottie.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(arrayAdapter);
    }

    private void initControl(){
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
    }

    private void addToCart() {
        if(Utils.mangGioHang.size() > 0 ){
            boolean flag = false;
            int soLuong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0;i < Utils.mangGioHang.size(); i++){
                if(Utils.mangGioHang.get(i).getIdsp() == zSanPhamNew.getId()){
                    Utils.mangGioHang.get(i).setSoluong(soLuong + Utils.mangGioHang.get(i).getSoluong());
                    long price = Long.parseLong(zSanPhamNew.getGiasp()) * Utils.mangGioHang.get(i).getSoluong();
                    Utils.mangGioHang.get(i).setGiasp(price);
                    flag = true;
                }
            }
            if (flag == false){
                long price = Long.parseLong(zSanPhamNew.getGiasp()) * soLuong;
                GioHang gioHang = new GioHang();
                gioHang.setGiasp(price);
                gioHang.setSoluong(soLuong);
                gioHang.setIdsp(zSanPhamNew.getId());
                gioHang.setTensp(zSanPhamNew.getTensp());
                gioHang.setHinhsp(zSanPhamNew.getHinhanh());
                Utils.mangGioHang.add(gioHang);
            }

        }else {
            int soLuong = Integer.parseInt(spinner.getSelectedItem().toString());
            long price = Long.parseLong(zSanPhamNew.getGiasp()) * soLuong;
            GioHang gioHang = new GioHang();
            gioHang.setGiasp(price);
            gioHang.setSoluong(soLuong);
            gioHang.setIdsp(zSanPhamNew.getId());
            gioHang.setTensp(zSanPhamNew.getTensp());
            gioHang.setHinhsp(zSanPhamNew.getHinhanh());
            Utils.mangGioHang.add(gioHang);

        }
        notificationBadge.setText(String.valueOf(Utils.mangGioHang.size()));
    }

    private void initUI() {
        frameLayout = findViewById(R.id.frameCart);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCart = new Intent(DetailsProductActivity.this, CartActivity.class);
                startActivity(intentCart);
            }
        });
        imgChiTiet = findViewById(R.id.imgChiTiet);
        tvname = findViewById(R.id.tvNameDetails);
        tvPrice = findViewById(R.id.tvpriceDetails);
        tvMoTa = findViewById(R.id.txtMoTaDetails);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        toolbarl = findViewById(R.id.toolbar);
        spinner = findViewById(R.id.spinner);
        notificationBadge = findViewById(R.id.menu_sl);
        int totalItem = 0;
        if (Utils.mangGioHang != null) {
            totalItem = 0;
            for (int i = 0; i < Utils.mangGioHang.size(); i++) {
                totalItem = totalItem + Utils.mangGioHang.get(i).getSoluong();
            }
        }
        notificationBadge.setText(String.valueOf(totalItem));

    }

    private void ActionToolBar(){
        setSupportActionBar(toolbarl);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarl.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}