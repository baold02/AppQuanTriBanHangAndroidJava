package com.example.appbanhangandroidjava.retrofits;

import com.example.appbanhangandroidjava.models.LoaiSp;
import com.example.appbanhangandroidjava.models.LoaiSpModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface APIBanHang {
//    http://localhost/banHang/getLoaiSP.php
    @GET("getLoaiSP.php")
    Observable<LoaiSpModel> getLoaiSanPham();
}
