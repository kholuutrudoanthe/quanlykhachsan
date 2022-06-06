package com.example.quanlykhachsan.DTO;

public class LoaiPhong {

    String MAP, TENPHONG;
    int DONGIANGAY, GIAGIO,SIZE;

    public LoaiPhong() {
    }

    public LoaiPhong(String MAP, String TENPHONG, int DONGIANGAY, int GIAGIO, int SIZE) {
        this.MAP = MAP;
        this.TENPHONG = TENPHONG;
        this.DONGIANGAY = DONGIANGAY;
        this.GIAGIO = GIAGIO;
        this.SIZE = SIZE;
    }

    public String getMAP() {
        return MAP;
    }

    public void setMAP(String MAP) {
        this.MAP = MAP;
    }

    public String getTENPHONG() {
        return TENPHONG;
    }

    public void setTENPHONG(String TENPHONG) {
        this.TENPHONG = TENPHONG;
    }

    public int getDONGIANGAY() {
        return DONGIANGAY;
    }

    public void setDONGIANGAY(int DONGIANGAY) {
        this.DONGIANGAY = DONGIANGAY;
    }

    public int getGIAGIO() {
        return GIAGIO;
    }

    public void setGIAGIO(int GIAGIO) {
        this.GIAGIO = GIAGIO;
    }

    public int getSIZE() {
        return SIZE;
    }

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    @Override
    public String toString() {
        return getTENPHONG();
    }
}
