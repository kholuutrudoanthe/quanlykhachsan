package com.example.quanlykhachsan.DAO;

import static com.example.quanlykhachsan.GUI.MainActivity.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlykhachsan.DTO.LoaiPhong;

import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO {
 //   db.execSQL("CREATE TABLE IF NOT EXISTS LoaiPhong(MAP TEXT PRIMARY KEY,TENPHONG TEXT,
    //   DONGIANGAY DOUBLE, SOLUONGN DOUBLE, GIAGIO DOUBLE, SOGIO DOUBLE)");

    public static Boolean Them_LoaiPhong(LoaiPhong loaiPhong) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MALP", loaiPhong.getMAP());
        contentValues.put("TENPHONG", loaiPhong.getTENPHONG());
        contentValues.put("DONGIANGAY", loaiPhong.getDONGIANGAY());
        contentValues.put("GIAGIO", loaiPhong.getGIAGIO());
        contentValues.put("SIZE", loaiPhong.getSIZE());
        long result = MyDB.insert("LoaiPhong", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<LoaiPhong> DS_LPhong() {
        List<LoaiPhong> loaiPhongs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From LoaiPhong", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                LoaiPhong LoaiPhong = new LoaiPhong();
                LoaiPhong.setMAP(cursor.getString(0));
                LoaiPhong.setTENPHONG(cursor.getString(1));
                LoaiPhong.setDONGIANGAY(cursor.getInt(2));
                LoaiPhong.setGIAGIO(cursor.getInt(3));
                LoaiPhong.setSIZE(cursor.getInt(4));
                loaiPhongs.add(LoaiPhong);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiPhongs;
    }

    public static LoaiPhong TT_LPhong(String MAP) {
        LoaiPhong LoaiPhong = new LoaiPhong();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from LoaiPhong where TENPHONG = ?", new String[]{MAP});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            LoaiPhong.setMAP(cursor.getString(0));
            LoaiPhong.setTENPHONG(cursor.getString(1));
            LoaiPhong.setDONGIANGAY(cursor.getInt(2));
            LoaiPhong.setGIAGIO(cursor.getInt(3));
            LoaiPhong.setSIZE(cursor.getInt(4));
            cursor.close();
            return LoaiPhong;
        }
        return null;
    }
    public static Boolean kiemtratlp(String tenlp) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from LoaiPhong where TENPHONG = ?", new String[]{tenlp});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    public static List<LoaiPhong> timkiem_tlp(String MANV) {
        List<LoaiPhong> loaiPhongs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From LoaiPhong where TENPHONG LIKE ?", new String[]{"%" + MANV + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                LoaiPhong LoaiPhong = new LoaiPhong();
                LoaiPhong.setMAP(cursor.getString(0));
                LoaiPhong.setTENPHONG(cursor.getString(1));
                LoaiPhong.setDONGIANGAY(cursor.getInt(2));
                LoaiPhong.setGIAGIO(cursor.getInt(3));
                LoaiPhong.setSIZE(cursor.getInt(4));
                loaiPhongs.add(LoaiPhong);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return loaiPhongs;
    }

    public static boolean Sua_LPhong(LoaiPhong loaiPhong) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MALP", loaiPhong.getMAP());
        contentValues.put("TENPHONG", loaiPhong.getTENPHONG());
        contentValues.put("DONGIANGAY", loaiPhong.getDONGIANGAY());
        contentValues.put("GIAGIO", loaiPhong.getGIAGIO());
        contentValues.put("SIZE", loaiPhong.getSIZE());
        long result = MyDB.update("LoaiPhong", contentValues, "MALP=?", new String[]{loaiPhong.getMAP()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_lp(String MANV) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("LoaiPhong", "MALP=?", new String[]{MANV});
        if (result == -1)
            return false;
        else
            return true;
    }
}
