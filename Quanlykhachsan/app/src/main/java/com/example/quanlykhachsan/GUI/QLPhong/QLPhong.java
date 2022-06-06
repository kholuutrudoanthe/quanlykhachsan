package com.example.quanlykhachsan.GUI.QLPhong;

import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.DS_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.Them_LoaiPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.timkiem_tlp;
import static com.example.quanlykhachsan.DAO.PhongDAO.DS_Phong;
import static com.example.quanlykhachsan.DAO.PhongDAO.Them_Phong;
import static com.example.quanlykhachsan.DAO.PhongDAO.kiemtratp;
import static com.example.quanlykhachsan.DAO.PhongDAO.timkiem_tp;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlykhachsan.BUS.LoaiPhongAdapter;
import com.example.quanlykhachsan.BUS.PhongAdapter;
import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.Phong;
import com.example.quanlykhachsan.GUI.MainActivityHome;
import com.example.quanlykhachsan.GUI.QLLoaiPhong.QLLoaiPhong;
import com.example.quanlykhachsan.R;

public class QLPhong extends AppCompatActivity {
    private PhongAdapter PhongAdapter;
    private RecyclerView ds;
    private EditText timkiem;
    private ImageButton them, cn;
    private ImageView ImageView_OnBack;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlphong);
        ds = findViewById(R.id.ds);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        timkiem = findViewById(R.id.timkiem);
        them = findViewById(R.id.them);
        cn = findViewById(R.id.cn);
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_them_suap);
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
        PhongAdapter = new PhongAdapter(DS_Phong(), this, dialog);
        ds.setAdapter(PhongAdapter);
        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QLPhong.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DS_LPhong().size()==0){
                    Toast.makeText(QLPhong.this, "Vui lòng thêm loại phòng trước!", Toast.LENGTH_SHORT).show();

                }else {
                    EditText editTextmp = dialog.findViewById(R.id.editTextmp);
                    EditText editTexttp = dialog.findViewById(R.id.editTexttp);
                    Spinner spinner = dialog.findViewById(R.id.spinner);
                    EditText editTextvitri = dialog.findViewById(R.id.editTextvitri);
                    EditText editTexttt = dialog.findViewById(R.id.editTexttt);
                    TextView ten = dialog.findViewById(R.id.ten);
                    Button btnthem = dialog.findViewById(R.id.btnthem);
                    Button btnthoat = dialog.findViewById(R.id.btnthoat);
                    final ArrayAdapter adapter = new ArrayAdapter(QLPhong.this, R.layout.dropdown_item, DS_LPhong());
                    spinner.setAdapter(adapter);
                    spinner.setSelection(0);
                    editTextmp.setEnabled(true);
                    btnthem.setText("Thêm");
                    ten.setText("Thêm phòng");

                    editTextmp.setText("");
                    editTexttp.setText("");
                    editTextvitri.setText("");
                    editTexttt.setText("Phòng trống");
                    LoaiPhong loaiPhong = (LoaiPhong) spinner.getSelectedItem();

                    btnthoat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    btnthem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String mp = editTextmp.getText().toString().trim();
                            String tp = editTexttp.getText().toString().trim();
                            String vt = editTextvitri.getText().toString().trim();
                            String tt = editTexttt.getText().toString().trim();
                            String mlp = loaiPhong.getTENPHONG();
                            if (mlp.isEmpty() || mp.isEmpty() || tp.isEmpty() || vt.isEmpty() || tt.isEmpty()) {
                                Toast.makeText(QLPhong.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else {
                                if (kiemtratp(tp)) {
                                    Toast.makeText(QLPhong.this, "Tên phòng này đã tồn tại", Toast.LENGTH_SHORT).show();
                                } else {
                                    Phong Phong = new Phong(mp, mlp, tp, vt, tt);
                                    if (Them_Phong(Phong)) {
                                        editTextmp.setText("");
                                        editTexttp.setText("");
                                        editTextvitri.setText("");
                                        editTexttt.setText("Phòng trống");
                                        spinner.setSelection(0);
                                        Toast.makeText(QLPhong.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(QLPhong.this, "Mã phòng này đã tồn tại thêm thất bại", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }
                    });
                    dialog.show();
                }


            }
        });
        cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhongAdapter = new PhongAdapter(DS_Phong(), QLPhong.this, dialog);
                ds.setAdapter(PhongAdapter);

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
                    PhongAdapter = new PhongAdapter(DS_Phong(), QLPhong.this, dialog);
                    ds.setAdapter(PhongAdapter);
                } else {
                    PhongAdapter = new PhongAdapter(timkiem_tp(timkiem.getText().toString().trim()), QLPhong.this, dialog);
                    ds.setAdapter(PhongAdapter);
                }
            }
        });
    }
}