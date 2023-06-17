package com.example.appbanhangandroidjava.retrofits;

import com.example.appbanhangandroidjava.models.LoaiSp;
import com.example.appbanhangandroidjava.models.LoaiSpModel;
import com.example.appbanhangandroidjava.models.SanPhamNewModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIBanHang {
//    http://localhost/banHang/getLoaiSP.php
    @GET("getLoaiSP.php")
    Observable<LoaiSpModel> getLoaiSanPham();

    @GET("getSanPham.php")
    Observable<SanPhamNewModel> getSanPhamNew();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamNewModel> getSanPham(
            @Field("page") int page,
            @Field("loai") int loai
    );


}
