package com.example.quanlykhachsan.BUS;

import static com.example.quanlykhachsan.DAO.DatPhongDAO.Sua_dp;
import static com.example.quanlykhachsan.DAO.DatPhongDAO.Them_DatPhong;
import static com.example.quanlykhachsan.DAO.DatPhongDAO.xoa_dp;
import static com.example.quanlykhachsan.DAO.LichSuDAO.Them_LichSu;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.DS_LPhong;
import static com.example.quanlykhachsan.DAO.LoaiPhongDAO.TT_LPhong;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.Sua_Nhanvien;
import static com.example.quanlykhachsan.DAO.NhanVienDAO.xoa_nv;
import static com.example.quanlykhachsan.DAO.PhongDAO.DS_Phong;
import static com.example.quanlykhachsan.DAO.PhongDAO.Sua_Trangthai;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlykhachsan.DTO.DatPhong;
import com.example.quanlykhachsan.DTO.LICHSU;
import com.example.quanlykhachsan.DTO.LoaiPhong;
import com.example.quanlykhachsan.DTO.NhanVien;
import com.example.quanlykhachsan.DTO.Phong;
import com.example.quanlykhachsan.GUI.QLdatphong.QLdatphong;
import com.example.quanlykhachsan.R;

import java.util.ArrayList;
import java.util.List;

public class DatPhongAdapter extends RecyclerView.Adapter<DatPhongAdapter.datphongViewholdle> {
    List<DatPhong> lists;
    Context context;
    Dialog dialog;
    private String loai;

    public DatPhongAdapter(List<DatPhong> lists, Context context, Dialog dialog) {
        this.lists = lists;
        this.context = context;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public datphongViewholdle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dp, parent, false);
        return new datphongViewholdle(view);
    }

    @Override
    public void onBindViewHolder(@NonNull datphongViewholdle holder, int position) {
        DatPhong datPhong = lists.get(position);


        holder.textmadp.setText(datPhong.getMADP());
        holder.texttp.setText(datPhong.getTENPHONG());
        holder.texttkh.setText(datPhong.getTENKH());
        holder.textsn.setText(datPhong.getSONGUOI() + "");
        holder.texttt.setText(datPhong.getTONGTIEN() + "");
        holder.imageViewedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                btnthem.setText("Sửa");
                final ArrayAdapter adapter = new ArrayAdapter(context, R.layout.dropdown_item, DS_Phong());
                spinner.setAdapter(adapter);
                int giatri = -1;
                for (int i = 0; i < DS_Phong().size(); i++) {
                    if (DS_Phong().get(i).getTENPHONG().equalsIgnoreCase(datPhong.getTENPHONG())) {
                        giatri = i;
                        break;
                    }
                }
                spinner.setSelection(giatri);
                editTextmp.setEnabled(false);
                editTextmp.setText(datPhong.getMADP());
                editTexttkh.setText(datPhong.getTENKH());
                editTextsonguoi.setText(datPhong.getSONGUOI());
                editTextg.setText(datPhong.getTHOIGIAN() + "");
                editTextt.setText(datPhong.getTONGTIEN() + "");
                Phong Phong = (Phong) spinner.getSelectedItem();
                String mlp = Phong.getMALP();
                LoaiPhong loaiPhong = TT_LPhong(mlp);
                List<Phong>list=new ArrayList<>();
                for (Phong phong :
                        DS_Phong()) {
                    if (!phong.getTRANGTHAI().equals("Đang sử dụng")){
                        list.add(phong);
                    }
                }
                if (list.size()==0){
                    list.add(Phong);
                    final ArrayAdapter adapter1 = new ArrayAdapter(context, R.layout.dropdown_item,list);
                    spinner.setAdapter(adapter1);
                }else {
                    list.add(Phong);
                    final ArrayAdapter adapter1 = new ArrayAdapter(context, R.layout.dropdown_item,list);
                    spinner.setAdapter(adapter1);
                }


                if (datPhong.getLOAI() == 0) {
                    loai = "0";
                    editTexgt.setText(loaiPhong.getDONGIANGAY() + "");
                    radioButton.setChecked(true);
                }
                if (datPhong.getLOAI() == 1) {
                    loai = "1";
                    editTexgt.setText(loaiPhong.getGIAGIO() + "");
                    radioButton2.setChecked(true);
                }
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
                        String m = editTextmp.getText().toString().trim();
                        String t = editTexttkh.getText().toString().trim();
                        String sn = editTextsonguoi.getText().toString().trim();
                        String tg = editTextg.getText().toString().trim();
                        String tt = editTextt.getText().toString().trim();


                        if (m.isEmpty() || t.isEmpty() || sn.isEmpty() || tg.isEmpty() || loai.isEmpty() || tt.isEmpty()) {
                            Toast.makeText(context, "Vui lòng điền đủ thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            DatPhong DatPhong = new DatPhong(m, Phong.getTENPHONG(), t, sn, Integer.parseInt(loai), Integer.parseInt(tg), Integer.parseInt(tt));
                            if (Sua_dp(DatPhong)) {
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
        holder.traphong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LICHSU lichsu=new LICHSU(datPhong.getTENKH(),datPhong.getTONGTIEN(),0);
                Them_LichSu(lichsu);
                Sua_Trangthai(datPhong.getTENPHONG(),"Phòng trống");
                if (xoa_dp(datPhong.getMADP())) {
                    Toast.makeText(context, "trả phòng thành công", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context, "trả phòng thất bại", Toast.LENGTH_SHORT).show();
                }

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

    public class datphongViewholdle extends RecyclerView.ViewHolder {
        TextView textmadp, texttp, texttkh, textsn, texttt;
        ImageView imageViewedit, imageViewDelete;
        Button traphong;

        public datphongViewholdle(@NonNull View itemView) {
            super(itemView);
            textmadp = itemView.findViewById(R.id.textmadp);
            texttp = itemView.findViewById(R.id.texttp);
            texttkh = itemView.findViewById(R.id.texttkh);
            textsn = itemView.findViewById(R.id.textsn);
            texttt = itemView.findViewById(R.id.texttt);
            traphong = itemView.findViewById(R.id.traphong);
            imageViewedit = itemView.findViewById(R.id.imageViewedit);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
