package com.example.quanlykhachsan.GUI.DoanThu;

import static com.example.quanlykhachsan.DAO.DatPhongDAO.DS_DatPhong;
import static com.example.quanlykhachsan.DAO.LichSuDAO.DS_LICHSU;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlykhachsan.BUS.DatPhongAdapter;
import com.example.quanlykhachsan.BUS.DoanhThuAdapter;
import com.example.quanlykhachsan.DTO.LICHSU;
import com.example.quanlykhachsan.GUI.MainActivityHome;
import com.example.quanlykhachsan.GUI.QLdatphong.QLdatphong;
import com.example.quanlykhachsan.R;

public class DoanhThu extends AppCompatActivity {
    com.example.quanlykhachsan.BUS.DoanhThuAdapter DoanhThuAdapter;
    ImageView ImageView_OnBack;
    TextView tongtien;
    RecyclerView ds;
    int tong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        tongtien = findViewById(R.id.tongtien);
        ds = findViewById(R.id.ds);
        ds.setLayoutManager(new LinearLayoutManager(this));
        DoanhThuAdapter = new DoanhThuAdapter(DS_LICHSU());
        ds.setAdapter(DoanhThuAdapter);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoanhThu.this, MainActivityHome.class));
                finish();
            }
        });

        if (DS_LICHSU().size()!=0){

            for (LICHSU ls :
                    DS_LICHSU()) {
                tong = tong + ls.getTONGTIEN();
            }
        }
        tongtien.setText("Tổng tiền: "+tong);
    }
}