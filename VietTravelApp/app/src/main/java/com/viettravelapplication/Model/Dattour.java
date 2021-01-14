package com.viettravelapplication.Model;

public class Dattour {
    private String hoten;
    private String phone;
    private String diachi;
    private String email;
    private int tourid;

    public Dattour() {
    }

    public Dattour(String hoten, String phone, String diachi, String email, int tourid) {
        this.hoten = hoten;
        this.phone = phone;
        this.diachi = diachi;
        this.email = email;
        this.tourid = tourid;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTourid() {
        return tourid;
    }

    public void setTourid(int tourid) {
        this.tourid = tourid;
    }
}
