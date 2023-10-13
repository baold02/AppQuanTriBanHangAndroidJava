package com.example.appbanhangandroidjava.Utils;

import com.example.appbanhangandroidjava.models.GioHang;
import com.example.appbanhangandroidjava.models.User;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static final String BASE_URL ="http://192.168.0.106/banHang/";
    public static List<GioHang> mangGioHang;
    public static List<GioHang> mangMuaHang = new ArrayList<>();

    public static User user_current = new User();
}
