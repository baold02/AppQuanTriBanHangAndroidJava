package com.example.appbanhangandroidjava.models;

import java.util.List;

public class UserModel {
    boolean success;
    String message;
    List<User> reslut;

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

    public List<User> getReslut() {
        return reslut;
    }

    public void setReslut(List<User> reslut) {
        this.reslut = reslut;
    }
}
