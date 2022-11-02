package com.if5b.penulisbuku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etJudul, etIsbn, etPenulis,etDeskripsi, etTahun;
    private Button btnUbah;
    String getId, getJudul, getIsbn,getPenulis, getTahun, getDeskripsi;
    int getPosisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etJudul = findViewById(R.id.et_judul);
        etIsbn = findViewById(R.id.et_isbn);
        etPenulis = findViewById(R.id.et_penulis);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etTahun = findViewById(R.id.et_tahun);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent terima = getIntent();
        getId = terima.getStringExtra("varID");
        getJudul = terima.getStringExtra("varJudul");
        getIsbn = terima.getStringExtra("varIsbn");
        getPenulis = terima.getStringExtra("varPenulis");
        getDeskripsi = terima.getStringExtra("varDeskrispi");
        getTahun = terima.getStringExtra("varTahun");
        getPosisi = terima.getIntExtra("varPosisi", -1);

        etJudul.setText(getJudul);
        etIsbn.setText(getIsbn);
        etPenulis.setText(getPenulis);
        etDeskripsi.setText(getDeskripsi);
        etTahun.setText(getTahun);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txJudul = etJudul.getText().toString();
                String txIsbn = etIsbn.getText().toString();
                String txPenulis = etPenulis.getText().toString();
                String txDeskripsi = etDeskripsi.getText().toString();
                String txTahun = etTahun.getText().toString();

                if (txJudul.trim().equals("")){
                    etJudul.setError("Judul Tidak Boleh Kosong");
                }else if (txIsbn.trim().equals("")){
                    etPenulis.setError("ISBN Tidak Boleh Kosong");
                }else if (txPenulis.trim().equals("")){
                    etPenulis.setError("Penulis Tidak Boleh Kosong");
                }else if (txTahun.trim().equals("")){
                    etTahun.setError("Tahun Tidak Boleh Kosong");
                }else if (txDeskripsi.trim().equals("")) {
                    etDeskripsi.setError("Deskripsi Tidak Boleh Kosong");
                }else {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);
                    long eksekusi = myDB.ubahBuku(getId, txJudul, txIsbn, txPenulis, txDeskripsi, Integer.valueOf(txTahun));

                    if (eksekusi == -1) {
                        Toast.makeText(UbahActivity.this, "Ubah Data Gagal", Toast.LENGTH_SHORT).show();
                        etIsbn.requestFocus();
                    } else {
                        Toast.makeText(UbahActivity.this, "Ubah Data Berhasil", Toast.LENGTH_SHORT).show();
                        MainActivity.posisiData = getPosisi;
                        finish();
                    }
                }
            }
        });
    }
}