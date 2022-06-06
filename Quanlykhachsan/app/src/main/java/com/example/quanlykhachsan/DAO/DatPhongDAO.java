package com.example.quanlykhachsan.DAO;

import static com.example.quanlykhachsan.GUI.MainActivity.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlykhachsan.DTO.DatPhong;
import com.example.quanlykhachsan.DTO.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class DatPhongDAO {
    public static Boolean Them_DatPhong(DatPhong DatPhong) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MADP", DatPhong.getMADP());
        contentValues.put("TENPHONG", DatPhong.getTENPHONG());
        contentValues.put("TENKH", DatPhong.getTENKH());
        contentValues.put("SONGUOI", DatPhong.getSONGUOI());
        contentValues.put("LOAI", DatPhong.getLOAI());
        contentValues.put("THOIGIAN", DatPhong.getTHOIGIAN());
        contentValues.put("TONGTIEN", DatPhong.getTONGTIEN());
        long result = MyDB.insert("DatPhong", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<DatPhong> DS_DatPhong() {
        List<DatPhong> DatPhongs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From DatPhong", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                DatPhong DatPhong = new DatPhong();
                DatPhong.setMADP(cursor.getString(0));
                DatPhong.setTENPHONG(cursor.getString(1));
                DatPhong.setTENKH(cursor.getString(2));
                DatPhong.setSONGUOI(cursor.getString(3));
                DatPhong.setLOAI(cursor.getInt(4));
                DatPhong.setTHOIGIAN(cursor.getInt(5));
                DatPhong.setTONGTIEN(cursor.getInt(6));
                DatPhongs.add(DatPhong);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return DatPhongs;
    }

    public static DatPhong TT_DatPhong(String MANV) {
        DatPhong DatPhong = new DatPhong();
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from DatPhong where MADP = ?", new String[]{MANV});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            DatPhong.setMADP(cursor.getString(0));
            DatPhong.setTENPHONG(cursor.getString(1));
            DatPhong.setTENKH(cursor.getString(2));
            DatPhong.setSONGUOI(cursor.getString(3));
            DatPhong.setLOAI(cursor.getInt(4));
            DatPhong.setTHOIGIAN(cursor.getInt(5));
            DatPhong.setTONGTIEN(cursor.getInt(6));
            cursor.close();
            return DatPhong;
        }
        return null;
    }

    public static List<DatPhong> timkiem_tkh(String TENKH) {
        List<DatPhong> DatPhongs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From DatPhong where TENKH LIKE ?", new String[]{"%" + TENKH + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                DatPhong DatPhong = new DatPhong();
                DatPhong.setMADP(cursor.getString(0));
                DatPhong.setTENPHONG(cursor.getString(1));
                DatPhong.setTENKH(cursor.getString(2));
                DatPhong.setSONGUOI(cursor.getString(3));
                DatPhong.setLOAI(cursor.getInt(4));
                DatPhong.setTHOIGIAN(cursor.getInt(5));
                DatPhong.setTONGTIEN(cursor.getInt(6));
                DatPhongs.add(DatPhong);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return DatPhongs;
    }

    public static boolean Sua_dp(DatPhong DatPhong) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MADP", DatPhong.getMADP());
        contentValues.put("TENPHONG", DatPhong.getTENPHONG());
        contentValues.put("TENKH", DatPhong.getTENKH());
        contentValues.put("SONGUOI", DatPhong.getSONGUOI());
        contentValues.put("LOAI", DatPhong.getLOAI());
        contentValues.put("THOIGIAN", DatPhong.getTHOIGIAN());
        contentValues.put("TONGTIEN", DatPhong.getTONGTIEN());
        long result = MyDB.update("DatPhong", contentValues, "MADP=?", new String[]{DatPhong.getMADP()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_dp(String MANV) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        long result = MyDB.delete("DatPhong", "MADP=?", new String[]{MANV});
        if (result == -1)
            return false;
        else
            return true;
    }
}
