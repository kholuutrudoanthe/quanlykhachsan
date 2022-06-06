package com.example.quanlykhachsan.GUI;

import static com.example.quanlykhachsan.DAO.TaiKhoanDAO.KiemTra_DN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlykhachsan.DAO.SQLite;
import com.example.quanlykhachsan.R;

public class MainActivity extends AppCompatActivity {
    public static SQLite database_;

    private EditText editTexttdn, editTextmk;
    private ImageView checkoff, checkon;
    private Button buttondangnhap;
    private TextView textViewdangky;
    private int checkmk = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database_ = new SQLite(MainActivity.this);

        editTexttdn = findViewById(R.id.editTexttdn);
        editTextmk = findViewById(R.id.editTextmk);
        checkoff = findViewById(R.id.checkoff);
        checkon = findViewById(R.id.checkon);
        buttondangnhap = findViewById(R.id.buttondangnhap);
        textViewdangky = findViewById(R.id.textViewdangky);



        textViewdangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivityDangKy.class));
            }
        });
        checkoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkmk == 1) {
                    editTextmk.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    checkmk = 0;
                    checkon.setVisibility(View.VISIBLE);
                    checkoff.setVisibility(View.GONE);
                }
            }
        });
        checkon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkmk == 0) {
                    editTextmk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    checkmk = 1;
                    checkon.setVisibility(View.GONE);
                    checkoff.setVisibility(View.VISIBLE);
                }
            }
        });
        buttondangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TDN = editTexttdn.getText().toString().trim();
                String MK = editTextmk.getText().toString().trim();
                if (TDN.isEmpty() || MK.isEmpty() ) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập thông tin tài khoản", Toast.LENGTH_SHORT).show();
                }
                if (KiemTra_DN(TDN, MK)) {
                    startActivity(new Intent(getApplicationContext(), MainActivityHome.class));
                } else {
                    Toast.makeText(MainActivity.this, "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}