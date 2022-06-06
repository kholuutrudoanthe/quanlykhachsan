package com.example.quanlykhachsan.DTO;

public class NhanVien {
    private String manv, tennv, diachi, sdt;

    public NhanVien() {
    }

    public NhanVien(String manv, String tennv, String diachi, String sdt) {
        this.manv = manv;
        this.tennv = tennv;
        this.diachi = diachi;
        this.sdt = sdt;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
