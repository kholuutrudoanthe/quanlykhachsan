package com.example.quanlykhachsan.GUI.QLLoaiPhong;

import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.DS_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.Them_LoaiPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.kiemtratlp;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.timkiem_tlp;

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

import com.example.quanlykhachsan.BUS.LoaiPhongAdapter;
import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.GUI.MainActivityHome;
import com.example.quanlykhachsan.R;

public class QLLoaiPhong extends AppCompatActivity {
    private LoaiPhongAdapter LoaiPhongAdapter;
    private RecyclerView ds;
    private EditText timkiem;
    private ImageButton them, cn;
    private ImageView ImageView_OnBack;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlloaiphong);
        ds = findViewById(R.id.ds);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        timkiem = findViewById(R.id.timkiem);
        them = findViewById(R.id.them);
        cn = findViewById(R.id.cn);
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_them_sualp);
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
        ds.setLayoutManager(new LinearLayoutManager(this));
        LoaiPhongAdapter = new LoaiPhongAdapter(DS_LPhong(), this,dialog);
        ds.setAdapter(LoaiPhongAdapter);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QLLoaiPhong.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextmlp = dialog.findViewById(R.id.editTextmlp);
                EditText editTexttlp = dialog.findViewById(R.id.editTexttlp);
                EditText editTextgtn = dialog.findViewById(R.id.editTextgtn);
                EditText editTextgtg = dialog.findViewById(R.id.editTextgtg);
                EditText editTextsize = dialog.findViewById(R.id.editTextsize);
                TextView ten = dialog.findViewById(R.id.ten);
                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button btnthoat = dialog.findViewById(R.id.btnthoat);
                editTextmlp.setEnabled(true);
                btnthem.setText("Thêm");
                ten.setText("Thêm loại phòng");
                editTextmlp.setText("");
                editTexttlp.setText("");
                editTextgtn.setText("");
                editTextgtg.setText("");
                editTextsize.setText("");
                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String m=editTextmlp.getText().toString().trim();
                        String t=editTexttlp.getText().toString().trim();
                        String gtn=editTextgtn.getText().toString().trim();
                        String gtg=editTextgtg.getText().toString().trim();
                        String size=editTextsize.getText().toString().trim();
                        if (m.isEmpty()||t.isEmpty()||gtn.isEmpty()||gtg.isEmpty()||size.isEmpty()){
                            Toast.makeText(QLLoaiPhong.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        }else {
                            LoaiPhong LoaiPhong=new LoaiPhong(m,t,Integer.parseInt(gtn),Integer.parseInt(gtg),Integer.parseInt(size));
                            if (kiemtratlp(t)){
                                Toast.makeText(QLLoaiPhong.this, "Tên loại phòng này đã tồn tại", Toast.LENGTH_SHORT).show();
                            }else {
                                if (Them_LoaiPhong(LoaiPhong)){
                                    editTextmlp.setText("");
                                    editTexttlp.setText("");
                                    editTextgtn.setText("");
                                    editTextgtg.setText("");
                                    editTextsize.setText("");
                                    Toast.makeText(QLLoaiPhong.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(QLLoaiPhong.this, "Mã loại phòng này đã tồn tại", Toast.LENGTH_SHORT).show();
                                }
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
                LoaiPhongAdapter = new LoaiPhongAdapter(DS_LPhong(), QLLoaiPhong.this,dialog);
                ds.setAdapter(LoaiPhongAdapter);

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
                    LoaiPhongAdapter = new LoaiPhongAdapter(DS_LPhong(), QLLoaiPhong.this,dialog);
                    ds.setAdapter(LoaiPhongAdapter);
                } else {
                    LoaiPhongAdapter = new LoaiPhongAdapter(timkiem_tlp(timkiem.getText().toString().trim()), QLLoaiPhong.this,dialog);
                    ds.setAdapter(LoaiPhongAdapter);
                }
            }
        });
    }
}