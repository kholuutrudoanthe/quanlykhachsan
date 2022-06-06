package com.example.quanlykhachsan.GUI.QLdatphong;

import static com.example.quanlykhachsan.DAO.DatPhongDAO.DS_DatPhong;
import static com.example.quanlykhachsan.DAO.DatPhongDAO.Them_DatPhong;
import static com.example.quanlykhachsan.DAO.DatPhongDAO.timkiem_tkh;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.DS_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.TT_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.Them_LoaiPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.kiemtratlp;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.timkiem_tlp;
import static com.example.quanlykhachsan.DAO.PhongDAO.DS_Phong;
import static com.example.quanlykhachsan.DAO.PhongDAO.Sua_Trangthai;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlykhachsan.BUS.DatPhongAdapter;
import com.example.quanlykhachsan.BUS.LoaiPhongAdapter;
import com.example.quanlykhachsan.DTO.DatPhong;
import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.Phong;
import com.example.quanlykhachsan.GUI.MainActivityHome;
import com.example.quanlykhachsan.GUI.QLLoaiPhong.QLLoaiPhong;
import com.example.quanlykhachsan.GUI.QLPhong.QLPhong;
import com.example.quanlykhachsan.R;

import java.util.ArrayList;
import java.util.List;

public class QLdatphong extends AppCompatActivity {
    private DatPhongAdapter DatPhongAdapter;
    private RecyclerView ds;
    private EditText timkiem;
    private ImageButton them, cn;
    private ImageView ImageView_OnBack;
    private Dialog dialog;
    private String loai;
    private List<Phong>list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qldatphong);
        ds = findViewById(R.id.ds);
        ImageView_OnBack = findViewById(R.id.ImageView_OnBack);
        timkiem = findViewById(R.id.timkiem);
        them = findViewById(R.id.them);
        cn = findViewById(R.id.cn);
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialog_them_sua_dp);
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
        DatPhongAdapter = new DatPhongAdapter(DS_DatPhong(), this, dialog);
        ds.setAdapter(DatPhongAdapter);

        ImageView_OnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QLdatphong.this, MainActivityHome.class));
                finish();
            }
        });

        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Phong phong :
                        DS_Phong()) {
                    if (!phong.getTRANGTHAI().equals("Đang sử dụng")){
                        list.add(phong);
                    }

                }
                if (DS_Phong().size() == 0||list.size()==0) {
                    Toast.makeText(QLdatphong.this, "Vui lòng thêm phòng không sử dụng!", Toast.LENGTH_SHORT).show();
                } else {

                    EditText editTextmp = dialog.findViewById(R.id.editTextmp);
                    Spinner spinner = dialog.findViewById(R.id.spinner);
                    EditText editTexttkh = dialog.findViewById(R.id.editTexttkh);
                    EditText editTextsonguoi = dialog.findViewById(R.id.editTextsonguoi);
                    RadioButton radioButton = dialog.findViewById(R.id.radioButton);
                    RadioButton radioButton2 = dialog.findViewById(R.id.radioButton2);
                    EditText editTexgt = dialog.findViewById(R.id.editTexgt);
                    EditText editTextg = dialog.findViewById(R.id.editTextg);
                    EditText editTextt = dialog.findViewById(R.id.editTextt);

                    TextView ten = dialog.findViewById(R.id.ten);
                    Button btnthem = dialog.findViewById(R.id.btnthem);
                    Button btnthoat = dialog.findViewById(R.id.btnthoat);
                    editTextmp.setEnabled(true);
                    btnthem.setText("Thêm");
                    ten.setText("Thêm loại phòng");
                    final ArrayAdapter adapter = new ArrayAdapter(QLdatphong.this, R.layout.dropdown_item,list);
                    spinner.setAdapter(adapter);
                    spinner.setSelection(0);

                    editTexttkh.setText("");
                    editTextsonguoi.setText("");
                    radioButton.setChecked(false);
                    radioButton2.setChecked(false);
                    editTextmp.setText("");
                    editTexgt.setText("");
                    editTextg.setText("");
                    editTextt.setText("");
                    Phong Phong = (Phong) spinner.getSelectedItem();
                    String mlp = Phong.getMALP();
                    LoaiPhong loaiPhong = TT_LPhong(mlp);
                    radioButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loai = "0";
                            editTexgt.setText(loaiPhong.getDONGIANGAY() + "");
                            radioButton2.setChecked(false);
                            if (editTextg.getText().toString().trim().isEmpty()) {
                                editTextt.setText("");
                            } else {
                                if (!editTexgt.getText().toString().trim().isEmpty()) {
                                    editTextt.setText(Integer.parseInt(editTexgt.getText().toString().trim()) * Integer.parseInt(editTextg.getText().toString().trim()) + "");
                                }
                            }
                        }
                    });
                    radioButton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loai = "1";
                            editTexgt.setText(loaiPhong.getGIAGIO() + "");
                            radioButton.setChecked(false);
                            if (editTextg.getText().toString().trim().isEmpty()) {
                                editTextt.setText("");
                            } else {
                                if (!editTexgt.getText().toString().trim().isEmpty()) {
                                    editTextt.setText(Integer.parseInt(editTexgt.getText().toString().trim()) * Integer.parseInt(editTextg.getText().toString().trim()) + "");
                                }
                            }
                        }
                    });
                    editTextg.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if (editTextg.getText().toString().trim().isEmpty()) {
                                editTextt.setText("");
                            } else {
                                if (!editTexgt.getText().toString().trim().isEmpty()) {
                                    editTextt.setText(Integer.parseInt(editTexgt.getText().toString().trim()) * Integer.parseInt(editTextg.getText().toString().trim()) + "");
                                }
                            }
                        }
                    });
                    btnthoat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.clear();
                            dialog.dismiss();
                        }
                    });
                    btnthem.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String m = editTextmp.getText().toString().trim();
                            String t = editTexttkh.getText().toString().trim();
                            String sn = editTextsonguoi.getText().toString().trim();
                            String tg = editTextg.getText().toString().trim();
                            String tt = editTextt.getText().toString().trim();


                            if (m.isEmpty() || t.isEmpty() || sn.isEmpty() || tg.isEmpty() || loai.isEmpty() || tt.isEmpty()) {
                                Toast.makeText(QLdatphong.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                            } else {
                                DatPhong DatPhong = new DatPhong(m, Phong.getTENPHONG(), t, sn, Integer.parseInt(loai), Integer.parseInt(tg), Integer.parseInt(tt));
                                if (Them_DatPhong(DatPhong)) {
                                    Sua_Trangthai(Phong.getTENPHONG(),"Đang sử dụng");
                                    list.clear();
                                    editTextmp.setText("");
                                    editTexttkh.setText("");
                                    editTextsonguoi.setText("");
                                    editTextg.setText("");
                                    editTextt.setText("");
                                    Toast.makeText(QLdatphong.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(QLdatphong.this, "Mã đặt phòng này đã tồn tại", Toast.LENGTH_SHORT).show();
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
                DatPhongAdapter = new DatPhongAdapter(DS_DatPhong(), QLdatphong.this, dialog);
                ds.setAdapter(DatPhongAdapter);

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
                    DatPhongAdapter = new DatPhongAdapter(DS_DatPhong(), QLdatphong.this, dialog);
                    ds.setAdapter(DatPhongAdapter);
                } else {
                    DatPhongAdapter = new DatPhongAdapter(timkiem_tkh(timkiem.getText().toString().trim()), QLdatphong.this, dialog);
                    ds.setAdapter(DatPhongAdapter);
                }
            }
        });
    }
}