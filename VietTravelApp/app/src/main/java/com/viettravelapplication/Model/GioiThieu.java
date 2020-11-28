package com.viettravelapplication.Model;

public class GioiThieu {
    private String noidung;
    private String dieukhoan;
    private String diachi;
    private String lienlac;

    public GioiThieu(String noidung, String dieukhoan, String diachi, String lienlac) {
        this.noidung = noidung;
        this.dieukhoan = dieukhoan;
        this.diachi = diachi;
        this.lienlac = lienlac;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getDieukhoan() {
        return dieukhoan;
    }

    public void setDieukhoan(String dieukhoan) {
        this.dieukhoan = dieukhoan;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getLienlac() {
        return lienlac;
    }

    public void setLienlac(String lienlac) {
        this.lienlac = lienlac;
    }

    @Override
    public String toString() {
        return "GioiThieu{" +
                "noidung='" + noidung + '\'' +
                ", dieukhoan='" + dieukhoan + '\'' +
                ", diachi='" + diachi + '\'' +
                ", lienlac='" + lienlac + '\'' +
                '}';
    }
}
