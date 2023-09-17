package com.example.appbanhangandroidjava.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.adapters.CartAdapter;
import com.example.appbanhangandroidjava.models.EventBus.SumEvent;
import com.example.appbanhangandroidjava.models.GioHang;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView tvCartNull, tvSumPrice;
    Toolbar toolbar;
    RecyclerView rcv;
    Button btnMuaHang;
    CartAdapter cartAdapter;
    long sumMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        initControl();
        SumMoney();
    }


    private void SumMoney(){
         sumMoney = 0;
        for(int i = 0; i<Utils.mangGioHang.size(); i++){
            sumMoney = sumMoney + (Utils.mangGioHang.get(i).getGiasp() * Utils.mangGioHang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
         tvSumPrice.setText(decimalFormat.format(sumMoney));

    }
    private void initControl() {
        rcv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        if(Utils.mangGioHang.size() == 0){
            tvCartNull.setVisibility(View.VISIBLE);
        }else {
            cartAdapter = new CartAdapter(getApplicationContext(), Utils.mangGioHang);
            rcv.setAdapter(cartAdapter);
        }


        btnMuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPay = new Intent(CartActivity.this,PayImmediatelyActivity.class);
                intentPay.putExtra("sumprice",sumMoney);
                startActivity(intentPay);
            }
        });

    }

    private void initView() {
        rcv = findViewById(R.id.rcvCart);
        tvCartNull = findViewById(R.id.tvGioHangTrong);
        tvSumPrice = findViewById(R.id.tvTongTien);
        toolbar= findViewById(R.id.toolbar);
        btnMuaHang = findViewById(R.id.btnMuaHang);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventSum(SumEvent sumEvent){
        if(sumEvent != null){
            SumMoney();
        }
    }
}