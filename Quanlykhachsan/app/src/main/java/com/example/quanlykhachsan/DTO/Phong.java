package com.example.quanlykhachsan.DTO;

public class Phong {
    String MAP,MALP,TENPHONG,VITRI,TRANGTHAI;

    public Phong() {
    }

    public Phong(String MAP, String MALP, String TENPHONG, String VITRI, String TRANGTHAI) {
        this.MAP = MAP;
        this.MALP = MALP;
        this.TENPHONG = TENPHONG;
        this.VITRI = VITRI;
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMAP() {
        return MAP;
    }

    public void setMAP(String MAP) {
        this.MAP = MAP;
    }

    public String getMALP() {
        return MALP;
    }

    public void setMALP(String MALP) {
        this.MALP = MALP;
    }

    public String getTENPHONG() {
        return TENPHONG;
    }

    public void setTENPHONG(String TENPHONG) {
        this.TENPHONG = TENPHONG;
    }

    public String getVITRI() {
        return VITRI;
    }

    public void setVITRI(String VITRI) {
        this.VITRI = VITRI;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    @Override
    public String toString() {
        return getTENPHONG();
    }
}
