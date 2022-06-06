package com.example.quanlykhachsan.BUS;

import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.DS_LPhong;
import static com.example.quanlykhachsan.DAO.PhongDAO.Sua_Phong;
import static com.example.quanlykhachsan.DAO.PhongDAO.xoa_p;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlykhachsan.DTO.LICHSU;
import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.Phong;
import com.example.quanlykhachsan.R;

import java.util.List;

public class DoanhThuAdapter extends RecyclerView.Adapter<DoanhThuAdapter.DTViewholdle> {
    List<LICHSU> lists;

    public DoanhThuAdapter(List<LICHSU> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public DTViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dt, parent, false);
        return new DTViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DTViewholdle holder, int position) {
        LICHSU LICHSU = lists.get(position);
        holder.madt.setText(LICHSU.getMADT()+"");
        holder.tenkh.setText(LICHSU.getTENKH());
        holder.tongtien.setText(LICHSU.getTONGTIEN() + "");

    }

    @Override
    public int getItemCount() {
        if (lists != null) {
            return lists.size();
        }
        return 0;
    }

    public class DTViewholdle extends RecyclerView.ViewHolder {
        TextView madt, tenkh, tongtien;

        public DTViewholdle(@NonNull View itemView) {
            super(itemView);
            madt = itemView.findViewById(R.id.madt);
            tenkh = itemView.findViewById(R.id.tenkh);
            tongtien = itemView.findViewById(R.id.tongtien);
        }
    }
}
