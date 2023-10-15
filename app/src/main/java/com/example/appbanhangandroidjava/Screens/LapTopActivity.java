    package com.example.appbanhangandroidjava.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.appbanhangandroidjava.R;
import com.example.appbanhangandroidjava.Utils.Utils;
import com.example.appbanhangandroidjava.adapters.PhoneAdapter;
import com.example.appbanhangandroidjava.models.SanPhamNew;
import com.example.appbanhangandroidjava.retrofits.APIBanHang;
import com.example.appbanhangandroidjava.retrofits.Retrofitclient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

    public class LapTopActivity extends AppCompatActivity {
        Toolbar toolbar;
        RecyclerView recyclerView;
        APIBanHang apiBanHang;
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        int page = 1;
        int loai;

        PhoneAdapter phoneAdapter;
        List<SanPhamNew> sanPhamNews;
        LinearLayoutManager linearLayoutManager;
        Handler handler = new Handler();
        boolean isLoading  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);
        apiBanHang = Retrofitclient.getInstance(Utils.BASE_URL).create(APIBanHang.class);
        loai = getIntent().getIntExtra("loai",2);
        init();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

        private void addEventLoad() {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(isLoading == false){
                        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == sanPhamNews.size()-1){
                            isLoading = true;
                            loadMore();
                        }
                    }
                }
            });
        }

        private void loadMore() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    sanPhamNews.add(null);
                    phoneAdapter.notifyItemInserted(sanPhamNews.size()-1);
                }
            });
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //remove null
                    sanPhamNews.remove(sanPhamNews.size()-1);
                    phoneAdapter.notifyItemRemoved(sanPhamNews.size());
                    page = page+1;
                    getData(page);
                    phoneAdapter.notifyDataSetChanged();
                    isLoading = false;
                }
            },2000);
        }

        private void getData(int page) {
            compositeDisposable.add(apiBanHang.getSanPham(page,loai)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            sanPhamNewModel -> {
                                if(sanPhamNewModel.isSuccess()){
                                    if(phoneAdapter == null){
                                        sanPhamNews = sanPhamNewModel.getReslut();
                                        phoneAdapter = new PhoneAdapter(getApplicationContext(),sanPhamNews);
                                        recyclerView.setAdapter(phoneAdapter);
                                    }else {
                                        int vitri = sanPhamNews.size()-1;
                                        int soLuongAdd = sanPhamNewModel.getReslut().size();
                                        for (int i = 0; i<soLuongAdd;i++){
                                            sanPhamNews.add(sanPhamNewModel.getReslut().get(i));
                                            phoneAdapter.notifyItemRangeInserted(vitri,soLuongAdd);
                                        }
                                    }

                                }else {
                                    Toast.makeText(this, "data is exhausted...", Toast.LENGTH_SHORT).show();
                                    isLoading = true;
                                }
                            },
                            throwable -> {
                                Toast.makeText(this, "Cannot connect to sever", Toast.LENGTH_SHORT).show();
                            }
                    )
            );
        }

        private void ActionToolBar(){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        private void init(){
            toolbar = findViewById(R.id.toolbar);
            recyclerView = findViewById(R.id.rcvPhone);
            linearLayoutManager =  new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            sanPhamNews = new ArrayList<>();
        }

        @Override
        protected void onDestroy() {
            compositeDisposable.clear();
            super.onDestroy();
        }
}