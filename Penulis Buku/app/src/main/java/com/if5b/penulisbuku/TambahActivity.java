package com.if5b.penulisbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    private EditText etJudul, etIsbn, etPenulis, etTahun ,etDeskripsi;
    private Button btnSimpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etJudul = findViewById(R.id.et_judul);
        etIsbn = findViewById(R.id.et_isbn);
        etPenulis = findViewById(R.id.et_penulis);
        etTahun = findViewById(R.id.et_tahun);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getJudul = etJudul.getText().toString();
                String getIsbn = etIsbn.getText().toString();
                String getPenulis = etPenulis.getText().toString();
                String getTahun = etTahun.getText().toString();
                String getDeskripsi = etDeskripsi.getText().toString();
                if (getJudul.trim().equals("")){
                    etJudul.setError("Judul Tidak Boleh Kosong");
                }else if (getIsbn.trim().equals("")){
                    etIsbn.setError("Isbn Tidak Boleh Kosong");
                }else if (getPenulis.trim().equals("")){
                    etPenulis.setError("Penulis Tidak Boleh Kosong");
                }else if (getTahun.trim().equals("")){
                    etTahun.setError("Tahun Tidak Boleh Kosong");
                }else if (getDeskripsi.trim().equals("")){
                    etDeskripsi.setText("Deskripsi Tidak Boleh Kosong");
                }else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);
                    long eksekusi = myDB.tambahBuku(getJudul, getIsbn, getPenulis, getDeskripsi, Integer.valueOf(getTahun));

                    if (eksekusi == -1){
                        Toast.makeText(TambahActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        etJudul.requestFocus();
                    }
                    else {
                        Toast.makeText(TambahActivity.this, "Tambah Data Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(TambahActivity.this, MainActivity.class));
                        finish();
                    }
                }
            }
        });

    }
}