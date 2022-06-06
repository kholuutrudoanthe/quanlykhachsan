package com.example.quanlykhachsan.BUS;

import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.DS_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.Sua_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.TT_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.xoa_lp;
import static com.example.quanlykhachsan.DAO.PhongDAO.Sua_Phong;
import static com.example.quanlykhachsan.DAO.PhongDAO.kiemtratp;
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

import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.Phong;
import com.example.quanlykhachsan.R;

import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewholdle> {
    List<Phong> lists;
    Context context;
    Dialog dialog;

    public PhongAdapter(List<Phong> lists, Context context, Dialog dialog) {
        this.lists = lists;
        this.context = context;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public PhongViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_p, parent, false);
        return new PhongViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongViewholdle holder, int position) {
        Phong Phong = lists.get(position);
        holder.textmap.setText(Phong.getMAP());
        holder.texttp.setText(Phong.getTENPHONG());
        holder.texttlp.setText(Phong.getMALP() + "");
        holder.textvitri.setText(Phong.getVITRI() + "");
        holder.texttt.setText(Phong.getTRANGTHAI() + "");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextmp = dialog.findViewById(R.id.editTextmp);
                EditText editTexttp = dialog.findViewById(R.id.editTexttp);
                Spinner spinner = dialog.findViewById(R.id.spinner);
                EditText editTextvitri = dialog.findViewById(R.id.editTextvitri);
                EditText editTexttt = dialog.findViewById(R.id.editTexttt);

                TextView ten = dialog.findViewById(R.id.ten);
                Button btnthem = dialog.findViewById(R.id.btnthem);
                Button btnthoat = dialog.findViewById(R.id.btnthoat);
                btnthem.setText("Sửa");
                ten.setText("Sửa thông tin");
                final ArrayAdapter adapter = new ArrayAdapter(context, R.layout.dropdown_item, DS_LPhong());
                spinner.setAdapter(adapter);
                int giatri = -1;
                for (int i = 0; i < DS_LPhong().size(); i++) {
                    if (DS_LPhong().get(i).toString().equalsIgnoreCase(Phong.getTENPHONG())) {
                        giatri = i;
                        break;
                    }
                }
                spinner.setSelection(giatri);

                editTextmp.setEnabled(false);
                editTextmp.setText(Phong.getMAP());
                editTexttp.setText(Phong.getTENPHONG() + "");
                editTextvitri.setText(Phong.getVITRI() + "");
                editTexttt.setText(Phong.getTRANGTHAI() + "");


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
                        LoaiPhong loaiPhong = (LoaiPhong) spinner.getSelectedItem();
                        String mlp = loaiPhong.getTENPHONG();

                        if (mlp.isEmpty() || mp.isEmpty() || tp.isEmpty() || vt.isEmpty() || tt.isEmpty()) {
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {

                                Phong Phong = new Phong(mp, mlp, tp, vt, tt);
                                if (Sua_Phong(Phong)) {
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
                builder.setMessage("Bạn có muốn xóa phòng này ko")
                        .setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (xoa_p(Phong.getMAP())) {
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

    public class PhongViewholdle extends RecyclerView.ViewHolder {
        TextView textmap, texttp, texttlp, textvitri, texttt;
        ImageView imageViewedit, imageViewDelete;

        public PhongViewholdle(@NonNull View itemView) {
            super(itemView);
            textmap = itemView.findViewById(R.id.textmap);
            texttp = itemView.findViewById(R.id.texttp);
            texttlp = itemView.findViewById(R.id.texttlp);
            textvitri = itemView.findViewById(R.id.textvitri);
            texttt = itemView.findViewById(R.id.texttt);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
