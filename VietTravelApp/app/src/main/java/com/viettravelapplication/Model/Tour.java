package com.viettravelapplication.Model;

import java.util.Date;

public class Tour {
    private int matour;
    private int macategory;
    private  int mauudai;
    private String diemdi;
    private String diemden;
    private Date thoigiandi;
    private Date thoigianve;
    private String mota;
    private String anh;
    private int gia;

    public Tour(int macategory, int mauudai, String diemdi, String diemden, Date thoigiandi, Date thoigianve, String mota, String anh, int gia) {
//        this.matour = matour;
        this.macategory = macategory;
        this.mauudai = mauudai;
        this.diemdi = diemdi;
        this.diemden = diemden;
        this.thoigiandi = thoigiandi;
        this.thoigianve = thoigianve;
        this.mota = mota;
        this.anh = anh;
        this.gia = gia;
    }

    public int getMatour() {
        return matour;
    }

    public void setMatour(int matour) {
        this.matour = matour;
    }

    public int getMacategory() {
        return macategory;
    }

    public void setMacategory(int macategory) {
        this.macategory = macategory;
    }

    public int getMauudai() {
        return mauudai;
    }

    public void setMauudai(int mauudai) {
        this.mauudai = mauudai;
    }

    public String getDiemdi() {
        return diemdi;
    }

    public void setDiemdi(String diemdi) {
        this.diemdi = diemdi;
    }

    public String getDiemden() {
        return diemden;
    }

    public void setDiemden(String diemden) {
        this.diemden = diemden;
    }

    public Date getThoigiandi() {
        return thoigiandi;
    }

    public void setThoigiandi(Date thoigiandi) {
        this.thoigiandi = thoigiandi;
    }

    public Date getThoigianve() {
        return thoigianve;
    }

    public void setThoigianve(Date thoigianve) {
        this.thoigianve = thoigianve;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "matour=" + matour +
                ", macategory=" + macategory +
                ", mauudai=" + mauudai +
                ", diemdi='" + diemdi + '\'' +
                ", diemden='" + diemden + '\'' +
                ", thoigiandi=" + thoigiandi +
                ", thoigianve=" + thoigianve +
                ", mota='" + mota + '\'' +
                ", anh='" + anh + '\'' +
                ", gia=" + gia +
                '}';
    }
}
