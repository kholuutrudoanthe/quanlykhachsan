package com.example.quanlykhachsan.DTO;

public class LICHSU {
    String TENKH;
    int TONGTIEN,MADT;

    public LICHSU() {
    }

    public LICHSU(String TENKH, int TONGTIEN, int MADT) {
        this.TENKH = TENKH;
        this.TONGTIEN = TONGTIEN;
        this.MADT = MADT;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public int getTONGTIEN() {
        return TONGTIEN;
    }

    public void setTONGTIEN(int TONGTIEN) {
        this.TONGTIEN = TONGTIEN;
    }

    public int getMADT() {
        return MADT;
    }

    public void setMADT(int MADT) {
        this.MADT = MADT;
    }
}
