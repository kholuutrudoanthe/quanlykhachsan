package com.example.quanlykhachsan.DAO;

import static com.example.quanlykhachsan.GUI.MainActivity.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlykhachsan.DTO.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public static Boolean Them_NhanVien(NhanVien nhanVien) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MANV", nhanVien.getManv());
        contentValues.put("TENNV", nhanVien.getTennv());
        contentValues.put("DIACHI", nhanVien.getDiachi());
        contentValues.put("SDT", nhanVien.getSdt());
        long result = MyDB.insert("Nhanvien", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<NhanVien> DS_NhanVien() {
        List<NhanVien> nhanViens = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From Nhanvien", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setManv(cursor.getString(0));
                nhanVien.setTennv(cursor.getString(1));
                nhanVien.setDiachi(cursor.getString(2));
                nhanVien.setSdt(cursor.getString(3));
                nhanViens.add(nhanVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return nhanViens;
    }

    public static NhanVien TT_NhanVien(String MANV) {
        NhanVien nhanVien = new NhanVien();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Nhanvien where MANV = ?", new String[]{MANV});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            nhanVien.setManv(cursor.getString(0));
            nhanVien.setTennv(cursor.getString(1));
            nhanVien.setDiachi(cursor.getString(2));
            nhanVien.setSdt(cursor.getString(3));
            cursor.close();
            return nhanVien;
        }
        return null;
    }

    public static List<NhanVien> timkiem_mnv(String MANV) {
        List<NhanVien> nhanViens = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From Nhanvien where TENNV LIKE ?", new String[]{"%" + MANV + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setManv(cursor.getString(0));
                nhanVien.setTennv(cursor.getString(1));
                nhanVien.setDiachi(cursor.getString(2));
                nhanVien.setSdt(cursor.getString(3));
                nhanViens.add(nhanVien);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return nhanViens;
    }

    public static boolean Sua_Nhanvien(NhanVien nhanVien) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENNV", nhanVien.getTennv());
        contentValues.put("DIACHI", nhanVien.getDiachi());
        contentValues.put("SDT", nhanVien.getSdt());
        long result = MyDB.update("Nhanvien", contentValues, "MANV=?", new String[]{nhanVien.getManv()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_nv(String MANV) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("Nhanvien", "MANV=?", new String[]{MANV});
        if (result == -1)
            return false;
        else
            return true;
    }
}
