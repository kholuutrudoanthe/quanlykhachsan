package com.example.quanlykhachsan.BUS;

import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.Sua_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.kiemtratlp;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.xoa_lp;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.Sua_Nhanvien;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.xoa_nv;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.NhanVien;
import com.example.quanlykhachsan.GUI.QLLoaiPhong.QLLoaiPhong;
import com.example.quanlykhachsan.R;

import java.util.List;

public class LoaiPhongAdapter extends RecyclerView.Adapter<LoaiPhongAdapter.LoaiPhongViewholdle> {
    List<LoaiPhong> lists;
    Context context;
    Dialog dialog;

    public LoaiPhongAdapter(List<LoaiPhong> lists, Context context, Dialog dialog) {
        this.lists = lists;
        this.context = context;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public LoaiPhongViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lp, parent, false);
        return new LoaiPhongViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiPhongViewholdle holder, int position) {
        LoaiPhong loaiPhong = lists.get(position);
        holder.textmalp.setText(loaiPhong.getMAP());
        holder.texttlp.setText(loaiPhong.getTENPHONG());
        holder.textptn.setText(loaiPhong.getDONGIANGAY() + "");
        holder.textptg.setText(loaiPhong.getGIAGIO() + "");
        holder.textsl.setText(loaiPhong.getSIZE() + "");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
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
                btnthem.setText("Sửa");
                editTextmlp.setEnabled(false);
                editTexttlp.setText(loaiPhong.getTENPHONG());
                editTextgtn.setText(loaiPhong.getDONGIANGAY() + "");
                editTextgtg.setText(loaiPhong.getGIAGIO() + "");
                editTextmlp.setText(loaiPhong.getMAP());
                editTextsize.setText(loaiPhong.getSIZE() + "");
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
                        String mlp = editTextmlp.getText().toString().trim();
                        String tlp = editTexttlp.getText().toString().trim();
                        String gtn = editTextgtn.getText().toString().trim();
                        String gtg = editTextgtg.getText().toString().trim();
                        String size = editTextsize.getText().toString().trim();

                        if (mlp.isEmpty() || tlp.isEmpty() || gtn.isEmpty() || gtg.isEmpty() || size.isEmpty()) {
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {

                                LoaiPhong loaiPhong = new LoaiPhong(mlp, tlp, Integer.parseInt(gtn), Integer.parseInt(gtg), Integer.parseInt(size));
                                if (Sua_LPhong(loaiPhong)) {
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
                builder.setMessage("Bạn có muốn xóa loại phòng này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (xoa_lp(loaiPhong.getMAP())) {
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

    public class LoaiPhongViewholdle extends RecyclerView.ViewHolder {
        TextView textmalp, texttlp, textptn, textptg, textsl;
        ImageView imageViewedit, imageViewDelete;

        public LoaiPhongViewholdle(@NonNull View itemView) {
            super(itemView);
            textmalp = itemView.findViewById(R.id.textmalp);
            texttlp = itemView.findViewById(R.id.texttlp);
            textptn = itemView.findViewById(R.id.textptn);
            textptg = itemView.findViewById(R.id.textptg);
            textsl = itemView.findViewById(R.id.textsl);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
