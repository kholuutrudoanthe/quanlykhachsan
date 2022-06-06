package com.example.quanlykhachsan.BUS;


import static com.example.quanlykhachsan.DAO.NhanVienDAO.Sua_Nhanvien;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.Them_NhanVien;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.xoa_nv;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quanlykhachsan.DTO.NhanVien;
import com.example.quanlykhachsan.GUI.QLNhanVien.MainActivityQLNhanVien;
import com.example.quanlykhachsan.R;

import java.util.List;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanvienViewholdle> {
    List<NhanVien> lists;
    Context context;
    Dialog dialog;

    public NhanVienAdapter(List<NhanVien> lists, Context context, Dialog dialog) {
        this.lists = lists;
        this.context = context;
        this.dialog = dialog;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NhanvienViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nv, parent, false);
        return new NhanvienViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanvienViewholdle holder, int position) {
        NhanVien nhanVien = lists.get(position);
        holder.textmanv.setText(nhanVien.getManv());
        holder.texttennv.setText(nhanVien.getTennv());
        holder.textdiachi.setText(nhanVien.getDiachi());
        holder.textsdt.setText(nhanVien.getSdt());
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextmnv = dialog.findViewById(R.id.editTextmnv);
                EditText editTexttnv = dialog.findViewById(R.id.editTexttnv);
                EditText editTextdc = dialog.findViewById(R.id.editTextdc);
                EditText editTextsdt = dialog.findViewById(R.id.editTextsdt);
                TextView ten = dialog.findViewById(R.id.ten);
                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button btnthoat = dialog.findViewById(R.id.btnthoat);
                btnthem.setText("Sửa");
                editTextmnv.setEnabled(false);
                editTextmnv.setText(nhanVien.getManv());
                editTexttnv.setText(nhanVien.getTennv());
                editTextdc.setText(nhanVien.getDiachi());
                editTextsdt.setText(nhanVien.getSdt());
                ten.setText("Sửa thông tin");
                btnthoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnthem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mnv = editTextmnv.getText().toString().trim();
                        String tnv = editTexttnv.getText().toString().trim();
                        String dc = editTextdc.getText().toString().trim();
                        String sdt = editTextsdt.getText().toString().trim();

                        if (mnv.isEmpty() || tnv.isEmpty() || dc.isEmpty() || sdt.isEmpty()) {
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            NhanVien nhanVien = new NhanVien(mnv, tnv, dc, sdt);
                            if (Sua_Nhanvien(nhanVien)) {
                                dialog.dismiss();
                                Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
                dialog.show();

            }
        });
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn xóa nhân viên này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (xoa_nv(nhanVien.getManv())) {
                                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });

                builder.create();
                builder.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    public class NhanvienViewholdle extends RecyclerView.ViewHolder {
        TextView textmanv, texttennv, textdiachi, textsdt;
        ImageView imageViewedit, imageViewDelete;

        public NhanvienViewholdle(@NonNull View itemView) {
            super(itemView);
            textmanv = itemView.findViewById(R.id.textmanv);
            texttennv = itemView.findViewById(R.id.texttennv);
            textdiachi = itemView.findViewById(R.id.textdiachi);
            textsdt = itemView.findViewById(R.id.textsdt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
