package com.example.appbanhangandroidjava.models;

import java.util.List;

public class SanPhamNewModel {
    boolean success;
    String message;
    List<SanPhamNew> reslut;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SanPhamNew> getReslut() {
        return reslut;
    }

    public void setReslut(List<SanPhamNew> reslut) {
        this.reslut = reslut;
    }
}
