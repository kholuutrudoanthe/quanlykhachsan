package com.example.quanlykhachsan.GUI.QLNhanVien;

import static com.example.quanlykhachsan.DAO.NhanVienDAO.DS_NhanVien;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.Them_NhanVien;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.timkiem_mnv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlykhachsan.BUS.NhanVienAdapter;
import com.example.quanlykhachsan.DTO.NhanVien;
import com.example.quanlykhachsan.GUI.MainActivityHome;
import com.example.quanlykhachsan.R;

public class MainActivityQLNhanVien extends AppCompatActivity {
    private NhanVienAdapter nhanVienAdapter;
    private RecyclerView dsnv;
    private EditText timkiem;
    private ImageButton them, cn;
    private ImageView ImageView_OnBack;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlnhan_vien);
        dsnv = findViewById(R.id.dsnv);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        timkiem = findViewById(R.id.timkiem);
        them = findViewById(R.id.them);
        cn = findViewById(R.id.cn);
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_them);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams w = window.getAttributes();
        w.gravity = Gravity.CENTER;
        window.setAttributes(w);
        dialog.setCancelable(false);
        dsnv.setLayoutManager(new LinearLayoutManager(this));
        nhanVienAdapter = new NhanVienAdapter(DS_NhanVien(), this,dialog);
        dsnv.setAdapter(nhanVienAdapter);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityQLNhanVien.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextmnv = dialog.findViewById(R.id.editTextmnv);
                EditText editTexttnv = dialog.findViewById(R.id.editTexttnv);
                EditText editTextdc = dialog.findViewById(R.id.editTextdc);
                EditText editTextsdt = dialog.findViewById(R.id.editTextsdt);
                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button btnthoat = dialog.findViewById(R.id.btnthoat);
                TextView ten = dialog.findViewById(R.id.ten);
                editTextmnv.setEnabled(true);
                btnthem.setText("Thêm");
                ten.setText("Thêm nhân viên");
                editTextmnv.setText("");
                editTexttnv.setText("");
                editTextdc.setText("");
                editTextsdt.setText("");
                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mnv=editTextmnv.getText().toString().trim();
                        String tnv=editTexttnv.getText().toString().trim();
                        String dc=editTextdc.getText().toString().trim();
                        String sdt=editTextsdt.getText().toString().trim();

                        Log.e("manv",mnv+tnv+dc+sdt);
                        if (mnv.isEmpty()||tnv.isEmpty()||dc.isEmpty()||sdt.isEmpty()){
                            Toast.makeText(MainActivityQLNhanVien.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            NhanVien nhanVien=new NhanVien(mnv,tnv,dc,sdt);
                            if (Them_NhanVien(nhanVien)){
                                editTextmnv.setText("");
                                editTexttnv.setText("");
                                editTextdc.setText("");
                                editTextsdt.setText("");
                                Toast.makeText(MainActivityQLNhanVien.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivityQLNhanVien.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();

            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienAdapter = new NhanVienAdapter(DS_NhanVien(), MainActivityQLNhanVien.this,dialog);
                dsnv.setAdapter(nhanVienAdapter);

            }
        });
        timkiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (timkiem.getText().toString().trim().isEmpty()) {
                    nhanVienAdapter = new NhanVienAdapter(DS_NhanVien(), MainActivityQLNhanVien.this,dialog);
                    dsnv.setAdapter(nhanVienAdapter);
                } else {
                    nhanVienAdapter = new NhanVienAdapter(timkiem_mnv(timkiem.getText().toString().trim()), MainActivityQLNhanVien.this,dialog);
                    dsnv.setAdapter(nhanVienAdapter);
                }
            }
        });
    }
}