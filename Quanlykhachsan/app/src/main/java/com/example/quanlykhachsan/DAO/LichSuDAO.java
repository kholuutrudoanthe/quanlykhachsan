package com.example.quanlykhachsan.DAO;

import static com.example.quanlykhachsan.GUI.MainActivity.database_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.quanlykhachsan.DTO.LICHSU;
import com.example.quanlykhachsan.DTO.LoaiPhong;

import java.util.ArrayList;
import java.util.List;

public class LichSuDAO {
    public static Boolean Them_LichSu(LICHSU LICHSU) {
        SQLiteDatabase MyDB = database_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENKH", LICHSU.getTENKH());
        contentValues.put("TONGTIEN", LICHSU.getTONGTIEN());
        long result = MyDB.insert("LICHSU", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<LICHSU> DS_LICHSU() {
        List<LICHSU> LICHSUs = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = database_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From LICHSU", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                LICHSU LICHSU = new LICHSU();
                LICHSU.setMADT(cursor.getInt(0));
                LICHSU.setTENKH(cursor.getString(1));
                LICHSU.setTONGTIEN(cursor.getInt(2));

                LICHSUs.add(LICHSU);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return LICHSUs;
    }
}
