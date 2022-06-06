package com.example.quanlykhachsan.DAO;

import static com.example.quanlykhachsan.GUI.MainActivity.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlykhachsan.DTO.TaiKhoan;

public class TaiKhoanDAO {
    public static Boolean Them_TaiKhoan(TaiKhoan tk) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", tk.getEMAIL());
        contentValues.put("HOTEN", tk.getHOTEN());
        contentValues.put("MATKHAU", tk.getMATKHAU());
        long result = MyDB.insert("TaiKhoan", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static Boolean kiem_tra_EMAIL_(String EMAIL) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where EMAIL = ?", new String[]{EMAIL});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }


    public static Boolean KiemTra_DN(String EMAIL, String MATKHAU) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from taikhoan where EMAIL = ? and MATKHAU = ? ", new String[]{EMAIL, MATKHAU});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public static TaiKhoan get_thongtin(String email) {
        TaiKhoan studen = new TaiKhoan();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from taikhoan where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
//            studen.setIdtk(cursor.getInt(0));
//            studen.setEmail(cursor.getString(1));
//            studen.setHoten(cursor.getString(2));
//            studen.setMk(cursor.getString(3));
//            studen.setSdt(cursor.getString(4));
//            studen.setDiachi(cursor.getString(5));
            cursor.close();
            return studen;
        }
        return null;
    }
}
