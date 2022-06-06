package com.example.quanlykhachsan.GUI;

import static com.example.quanlykhachsan.DAO.TaiKhoanDAO.Them_TaiKhoan;
import static com.example.quanlykhachsan.DAO.TaiKhoanDAO.kiem_tra_EMAIL_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlykhachsan.DTO.TaiKhoan;
import com.example.quanlykhachsan.R;

public class MainActivityDangKy extends AppCompatActivity {
    EditText editTextemail, editTexthoten, editTextmk, editTextnlmk;
    TextView  textViewdangnhap;
    CheckBox hienmkdk;
    Button buttondangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);

        editTextemail = findViewById(R.id.editTextemail);
        editTexthoten = findViewById(R.id.editTexthoten);
        editTextmk = findViewById(R.id.editTextmk);
        editTextnlmk = findViewById(R.id.editTextnlmk);
        textViewdangnhap = findViewById(R.id.textViewdangnhap);
        hienmkdk = findViewById(R.id.hienmkdk);
        buttondangky = findViewById(R.id.buttondangky);


        hienmkdk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editTextmk.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editTextnlmk.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    editTextmk.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editTextnlmk.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }

            }
        });

        textViewdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityDangKy.this,MainActivity.class));
                finish();
            }
        });

        buttondangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String EMAIL=editTextemail.getText().toString().trim();
                String HOTEN=editTexthoten.getText().toString().trim();
                String MK=editTextmk.getText().toString().trim();
                String NLMK=editTextnlmk.getText().toString().trim();
                if (EMAIL.isEmpty()||HOTEN.isEmpty()||MK.isEmpty()||NLMK.isEmpty()){
                    Toast.makeText(MainActivityDangKy.this, "Vui lòng điền đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    if (MK.equals(NLMK)){
                        if (kiem_tra_EMAIL_(EMAIL)){
                            Toast.makeText(MainActivityDangKy.this, "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        TaiKhoan Tk=new TaiKhoan(EMAIL,HOTEN,MK);
                        if(Them_TaiKhoan(Tk)){
                            Toast.makeText(MainActivityDangKy.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivityDangKy.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(MainActivityDangKy.this, "Tạo tài khoản thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(MainActivityDangKy.this, "Vui lòng nhập mật khẩu trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}