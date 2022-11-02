package com.if5b.penulisbuku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    FloatingActionButton fabTambah;
    private RecyclerView rvBuku;
    MyDatabaseHelper myDB;
    AdapterBuku adapterBuku;
    ArrayList<String> arId, arJudul, arIsbn, arPenulis, arTahun, arDeskripsi;
    public static int posisiData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new MyDatabaseHelper(MainActivity.this);
        arId = new ArrayList<>();
        arJudul = new ArrayList<>();
        arIsbn = new ArrayList<>();
        arPenulis = new ArrayList<>();
        arTahun = new ArrayList<>();
        arDeskripsi = new ArrayList<>();
        SQliteToArrayList();
        // pindah ke recycle views
        rvBuku = findViewById(R.id.rv_buku);
        adapterBuku = new AdapterBuku(MainActivity.this,arId,arJudul,arIsbn,arPenulis,arTahun, arDeskripsi);// view udh siap
        rvBuku.setAdapter(adapterBuku);//ditugaskan
        // Melaksanakan
        rvBuku.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    public void bukaActivityTambah(View view) {
        startActivity(new Intent(MainActivity.this, TambahActivity.class));
    }
    //data ambil array kemudian masuk ke 1 1
    // Table ke Arry List
    private void SQliteToArrayList(){
        Cursor cursor = myDB.bacaSemuaData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                arId.add(cursor.getString(0));
                arJudul.add(cursor.getString(1));
                arIsbn.add(cursor.getString(2));
                arPenulis.add(cursor.getString(3));
                arTahun.add(cursor.getString(4));
                arDeskripsi.add(cursor.getString(5));
            }
        }
    }
    protected void onResume() {
        super.onResume();

        arId = new ArrayList<>();
        arJudul = new ArrayList<>();
        arIsbn = new ArrayList<>();
        arPenulis = new ArrayList<>();
        arTahun = new ArrayList<>();
        arDeskripsi = new ArrayList<>();
        SQliteToArrayList();
        // pindah ke recycle views
        rvBuku = findViewById(R.id.rv_buku);
        adapterBuku = new AdapterBuku(MainActivity.this,arId,arJudul,arIsbn,arPenulis,arTahun,arDeskripsi);// view udh siap
        rvBuku.setAdapter(adapterBuku);//ditugaskan
        // Melaksanakan
        rvBuku.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvBuku.smoothScrollToPosition(posisiData);
    }
}