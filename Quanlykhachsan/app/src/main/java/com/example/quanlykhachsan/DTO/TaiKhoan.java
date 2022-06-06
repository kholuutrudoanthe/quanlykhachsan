package com.example.quanlykhachsan.DTO;

public class TaiKhoan {
    String EMAIL, HOTEN, MATKHAU;

    public TaiKhoan() {
    }

    public TaiKhoan(String EMAIL, String HOTEN, String MATKHAU) {
        this.EMAIL = EMAIL;
        this.HOTEN = HOTEN;
        this.MATKHAU = MATKHAU;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getHOTEN() {
        return HOTEN;
    }

    public void setHOTEN(String HOTEN) {
        this.HOTEN = HOTEN;
    }

    public String getMATKHAU() {
        return MATKHAU;
    }

    public void setMATKHAU(String MATKHAU) {
        this.MATKHAU = MATKHAU;
    }
}
