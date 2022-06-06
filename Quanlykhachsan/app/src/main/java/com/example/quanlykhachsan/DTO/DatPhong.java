package com.example.quanlykhachsan.DTO;

public class DatPhong {
    String MADP,TENPHONG,TENKH,SONGUOI;
    int LOAI,THOIGIAN,TONGTIEN;

    public DatPhong() {
    }

    public DatPhong(String MADP, String TENPHONG, String TENKH, String SONGUOI, int LOAI, int THOIGIAN, int TONGTIEN) {
        this.MADP = MADP;
        this.TENPHONG = TENPHONG;
        this.TENKH = TENKH;
        this.SONGUOI = SONGUOI;
        this.LOAI = LOAI;
        this.THOIGIAN = THOIGIAN;
        this.TONGTIEN = TONGTIEN;
    }

    public String getMADP() {
        return MADP;
    }

    public void setMADP(String MADP) {
        this.MADP = MADP;
    }

    public String getTENPHONG() {
        return TENPHONG;
    }

    public void setTENPHONG(String TENPHONG) {
        this.TENPHONG = TENPHONG;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getSONGUOI() {
        return SONGUOI;
    }

    public void setSONGUOI(String SONGUOI) {
        this.SONGUOI = SONGUOI;
    }

    public int getLOAI() {
        return LOAI;
    }

    public void setLOAI(int LOAI) {
        this.LOAI = LOAI;
    }

    public int getTHOIGIAN() {
        return THOIGIAN;
    }

    public void setTHOIGIAN(int THOIGIAN) {
        this.THOIGIAN = THOIGIAN;
    }

    public int getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(int TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }
}
