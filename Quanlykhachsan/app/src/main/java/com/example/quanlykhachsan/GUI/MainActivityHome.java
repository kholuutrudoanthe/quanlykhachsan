package com.example.quanlykhachsan.GUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quanlykhachsan.GUI.DoanThu.DoanhThu;
import com.example.quanlykhachsan.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.quanlykhachsan.GUI.QLLoaiPhong.QLLoaiPhong;
import com.example.quanlykhachsan.GUI.QLPhong.QLPhong;
import com.example.quanlykhachsan.GUI.QLdatphong.QLdatphong;
import com.example.quanlykhachsan.R;

public class MainActivityHome extends AppCompatActivity {
    private CardView qlnv, qlp, qllp, qldp, dt;
    private Button dx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);


        qlnv = findViewById(R.id.qlnv);

        qlp = findViewById(R.id.qlp);

        qllp = findViewById(R.id.qllp);

        qldp = findViewById(R.id.qldp);

        dt = findViewById(R.id.dt);


        dx = findViewById(R.id.dx);


        qlnv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivityQLNhanVien.class));
            }
        });
        dx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, MainActivity.class));
            }
        });
        qllp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, QLLoaiPhong.class));
            }
        });
        qlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(MainActivityHome.this, QLPhong.class));
            }
        });
        qldp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, QLdatphong.class));
            }
        });
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityHome.this, DoanhThu.class));
            }
        });

    }

    private long time;
    private Toast mtoast;

    @Override
    public void onBackPressed() {
        if (time + 2000 > System.currentTimeMillis()) {
            mtoast.cancel();
            finish();
            return;
        } else {
            mtoast = Toast.makeText(MainActivityHome.this, "Nhấn 2 lần để đồng ý thoát", Toast.LENGTH_SHORT);
            mtoast.show();
        }
        time = System.currentTimeMillis();
    }
}