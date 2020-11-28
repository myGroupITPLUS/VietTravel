package com.viettravelapplication.Model;

import java.util.Date;

public class UuDai{
    private int mauudai;
    private String sale;
    private String mota;
    private String anh;

    public UuDai(String sale, String mota, String anh) {
        this.sale = sale;
        this.mota = mota;
        this.anh = anh;
    }

    public int getMauudai() {
        return mauudai;
    }

    public void setMauudai(int mauudai) {
        this.mauudai = mauudai;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    @Override
    public String toString() {
        return "UuDai{" +
                "mauudai=" + mauudai +
                ", sale='" + sale + '\'' +
                ", mota='" + mota + '\'' +
                ", anh='" + anh + '\'' +
                '}';
    }
}

