package com.example.quanlykhachsan.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "Quanlyks.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TaiKhoan(EMAIL TEXT PRIMARY KEY, HOTEN TEXT, MATKHAU TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Nhanvien(MANV TEXT PRIMARY KEY, TENNV TEXT, DIACHI TEXT,SDT TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS LoaiPhong(MALP TEXT PRIMARY KEY,TENPHONG TEXT, DONGIANGAY DOUBLE,GIAGIO DOUBLE,SIZE DOUBLE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Phong(MAP TEXT PRIMARY KEY, TENLPHONG TEXT, TENPHONG TEXT,VITRI TEXT,TRANGTHAI TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS DatPhong(MADP TEXT PRIMARY KEY, TENPHONG TEXT,  TENKH TEXT,SONGUOI TEXT, LOAI DOUBLE,THOIGIAN DOUBLE, TONGTIEN DOUBLE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS LICHSU(MADT INTEGER PRIMARY KEY AUTOINCREMENT, TENKH TEXT, TONGTIEN DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
