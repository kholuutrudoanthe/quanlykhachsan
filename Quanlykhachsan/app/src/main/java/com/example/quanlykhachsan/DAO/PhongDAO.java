package com.example.quanlykhachsan.DAO;

import static com.example.quanlykhachsan.GUI.MainActivity.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.Phong;

import java.util.ArrayList;
import java.util.List;

public class PhongDAO {
        //      db.execSQL("CREATE TABLE IF NOT EXISTS Phong(MAP TEXT PRIMARY KEY, TENLPHONG TEXT, TENPHONG TEXT,VITRI TEXT,TRANGTHAI TEXT)");

    public static Boolean Them_Phong(Phong Phong) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.e("", Phong.getMAP()+","+Phong.getMALP()+","+ Phong.getTENPHONG()+","+ Phong.getVITRI()+","+Phong.getTRANGTHAI());
        contentValues.put("MAP", Phong.getMAP());
        contentValues.put("TENLPHONG", Phong.getMALP());
        contentValues.put("TENPHONG", Phong.getTENPHONG());
        contentValues.put("VITRI", Phong.getVITRI());
        contentValues.put("TRANGTHAI", Phong.getTRANGTHAI());
        long result = MyDB.insert("Phong", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<Phong> DS_Phong() {
        List<Phong> Phongs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From Phong", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Phong Phong = new Phong();
                Phong.setMAP(cursor.getString(0));
                Phong.setMALP(cursor.getString(1));
                Phong.setTENPHONG(cursor.getString(2));
                Phong.setVITRI(cursor.getString(3));
                Phong.setTRANGTHAI(cursor.getString(4));
                Phongs.add(Phong);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return Phongs;
    }
    public static Boolean kiemtratp(String tenp) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Phong where TENPHONG = ?", new String[]{tenp});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    public static Phong TT_Phong(String MAP) {
        Phong Phong = new Phong();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from Phong where MAP = ?", new String[]{MAP});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Phong.setMAP(cursor.getString(0));
            Phong.setMALP(cursor.getString(1));
            Phong.setTENPHONG(cursor.getString(2));
            Phong.setVITRI(cursor.getString(3));
            Phong.setTRANGTHAI(cursor.getString(4));
            cursor.close();
            return Phong;
        }
        return null;
    }

    public static List<Phong> timkiem_tp(String MANV) {
        List<Phong> Phongs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From Phong where TENPHONG LIKE ?", new String[]{"%" + MANV + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Phong Phong = new Phong();
                Phong.setMAP(cursor.getString(0));
                Phong.setMALP(cursor.getString(1));
                Phong.setTENPHONG(cursor.getString(2));
                Phong.setVITRI(cursor.getString(3));
                Phong.setTRANGTHAI(cursor.getString(4));
                Phongs.add(Phong);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return Phongs;
    }

    public static boolean Sua_Phong(Phong Phong) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAP", Phong.getMAP());
        contentValues.put("TENLPHONG", Phong.getMALP());
        contentValues.put("TENPHONG", Phong.getTENPHONG());
        contentValues.put("VITRI", Phong.getVITRI());
        contentValues.put("TRANGTHAI", Phong.getTRANGTHAI());
        long result = MyDB.update("Phong", contentValues, "MAP=?", new String[]{Phong.getMAP()});
        if (result == -1)
            return false;
        else
            return true;
    }
    public static boolean Sua_Trangthai(String map,String trangthai) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TRANGTHAI",trangthai);
        long result = MyDB.update("Phong", contentValues, "TENPHONG=?", new String[]{map});
        if (result == -1)
            return false;
        else
            return true;
    }
    public static boolean xoa_p(String MANV) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("Phong", "MAP=?", new String[]{MANV});
        if (result == -1)
            return false;
        else
            return true;
    }
}
