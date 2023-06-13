package com.example.appbanhangandroidjava.models;

import java.util.List;

public class LoaiSpModel {
    boolean success;
    String message;
    List<LoaiSp> reslut;

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

    public List<LoaiSp> getReslut() {
        return reslut;
    }

    public void setReslut(List<LoaiSp> reslut) {
        this.reslut = reslut;
    }
}
