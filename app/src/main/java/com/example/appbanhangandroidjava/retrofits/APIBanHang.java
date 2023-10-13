package com.example.appbanhangandroidjava.retrofits;

import com.example.appbanhangandroidjava.models.LoaiSp;
import com.example.appbanhangandroidjava.models.LoaiSpModel;
import com.example.appbanhangandroidjava.models.MessageModel;
import com.example.appbanhangandroidjava.models.OrderModel;
import com.example.appbanhangandroidjava.models.SanPhamNewModel;
import com.example.appbanhangandroidjava.models.UserModel;

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

    @POST("rigister.php")
    @FormUrlEncoded
    Observable<UserModel> Rigister(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("usernane") String usernane,
            @Field("phone") String phone
    );

    @POST("signin.php")
    @FormUrlEncoded
    Observable<UserModel> signin(
            @Field("email") String email,
            @Field("pass") String pass
    );


    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> creatOder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );

    @POST("historyPayment.php")
    @FormUrlEncoded
    Observable<OrderModel> xemDonHang(
            @Field("iduser") int id
    );

    @POST("findProduct.php")
    @FormUrlEncoded
    Observable<SanPhamNewModel> findProduct(
            @Field("search") String search
    );



    @POST("insertProduct.php")
    @FormUrlEncoded
    Observable<MessageModel> insertProduct(
            @Field("tensp") String name,
            @Field("giasp") String price,
            @Field("hinhanh") String hinhanh,
            @Field("mota") String mota,
            @Field("loai") int loai

    );

}
