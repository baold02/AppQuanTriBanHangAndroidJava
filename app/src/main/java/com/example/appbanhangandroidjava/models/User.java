package com.example.appbanhangandroidjava.models;

public class User {
    int id;
    private String email;
    private String pass;
    private String usernane;
    private String phone;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User() {
    }

    public User(int id, String email, String pass, String usernane, String phone) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.usernane = usernane;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsernane() {
        return usernane;
    }

    public void setUsernane(String usernane) {
        this.usernane = usernane;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
