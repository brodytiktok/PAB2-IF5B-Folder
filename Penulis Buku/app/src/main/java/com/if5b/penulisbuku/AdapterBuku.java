package com.if5b.penulisbuku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Tekan Lampu Merah Implement Methods
public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.ViewHolderBuku> {
    private Context ctx;
    private ArrayList arId, arJudul, arIsbn, arPenulis, arTahun, arDeskripsi; // bikin constructor

    public AdapterBuku(Context ctx, ArrayList arId, ArrayList arJudul, ArrayList arIsbn, ArrayList arPenulis, ArrayList arTahun, ArrayList arDeskripsi) {
        this.ctx = ctx;
        this.arId = arId;
        this.arJudul = arJudul;
        this.arIsbn = arIsbn;
        this.arPenulis = arPenulis;
        this.arTahun = arTahun;
        this.arDeskripsi = arDeskripsi;
    }

    @NonNull
    @Override
    public ViewHolderBuku onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // menempelkan card view didalam recycle views
        LayoutInflater pump = LayoutInflater.from(ctx); // pompa
        // parent recycle views
        View view = pump.inflate(R.layout.card_item, parent, false);
        return new ViewHolderBuku(view);
    } // untuk menyatu recycle dan card holder

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBuku holder, int position) {
        holder.tvId.setText(arId.get(position).toString());
        holder.tvJudul.setText(arJudul.get(position).toString());
        holder.tvIsbn.setText(arIsbn.get(position).toString());
        holder.tvPenulis.setText(arPenulis.get(position).toString());
        holder.tvDeskripsi.setText(arDeskripsi.get(position).toString());
        holder.tvTahun.setText(arTahun.get(position).toString());
        holder.cvBuku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder jendelaPesan = new AlertDialog.Builder(ctx);
                jendelaPesan.setMessage("Pilih Perintah Yang Diinginkan !");
                jendelaPesan.setTitle("Perhatian");
                jendelaPesan.setCancelable(true);

                jendelaPesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                        long eksekusi = myDB.hapusBuku(arId.get(position).toString());

                        if (eksekusi == -1) {
                            Toast.makeText(ctx, "Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ctx, "Data Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            if (position==0){
                                MainActivity.posisiData = 0;
                            }else{
                                MainActivity.posisiData = position - 1;
                            }
                            dialog.dismiss();
                            ((MainActivity)ctx).onResume();
                        }
                    }
                });
                jendelaPesan.setPositiveButton("ubah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent pindah = new Intent(ctx, UbahActivity.class);
                        pindah.putExtra("varID", arId.get(position).toString());
                        pindah.putExtra("varJudul", arJudul.get(position).toString());
                        pindah.putExtra("varIsbn" , arIsbn.get(position).toString());
                        pindah.putExtra("varPenulis", arPenulis.get(position).toString());
                        pindah.putExtra("varDeskripsi", arDeskripsi.get(position).toString());
                        pindah.putExtra("varTahun", arTahun.get(position).toString());
                        pindah.putExtra("varPosisi", position);
                        ctx.startActivity(pindah);
                    }
                });
                jendelaPesan.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() { // Anggota Arraylist
        return arJudul.size();
    }


    //Tekan Lampu Merah Matching Super Methods
    public class ViewHolderBuku extends RecyclerView.ViewHolder {
    // View Holder Per Items
        TextView tvId, tvJudul, tvIsbn, tvPenulis, tvTahun, tvDeskripsi;
        CardView cvBuku; // card items
        public ViewHolderBuku(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvIsbn = itemView.findViewById(R.id.tv_isbn);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);
            tvTahun = itemView.findViewById(R.id.tv_tahun);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
            cvBuku = itemView.findViewById(R.id.cv_buku);
        }
    }
}
