package com.if5b.penulisbuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    // Kalau Error tinggal Pencet Lampu Merah dan Pilih Implement Method dan pilih 2 2nya
    // Mashi Error langsung create constructor Matching Super
    private Context ctx; //context ini digunakan untuk mewakilli class non activity
    private static final String DATABASE_NAME = "dbBukus1";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "tblBuku";
    private static final String FIELD_ID = "id";
    private static final String FIELD_ISBN = "isbn";
    private static final String FIELD_JUDUL = "judul";
    private static final String FIELD_PENULIS = "penulis";
    private static final String FIELD_DESKRIPSI = "deskripsi";
    private static final String FIELD_TAHUN = "tahun";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }
    //onCreate ini akan dieksekusi ketika dibuat ada perubahan
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_ISBN + " TEXT, " +
                FIELD_JUDUL + " TEXT, " +
                FIELD_PENULIS + " TEXT, " +
                FIELD_DESKRIPSI + " TEXT, " +
                FIELD_TAHUN + " INTEGER ); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long tambahBuku(String judul, String isbn, String penulis, String deskripsi, int tahun) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIELD_JUDUL, judul);
        cv.put(FIELD_ISBN, isbn);
        cv.put(FIELD_PENULIS, penulis);
        cv.put(FIELD_DESKRIPSI, deskripsi);
        cv.put(FIELD_TAHUN, tahun);
        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }
    public long ubahBuku(String id, String judul, String isbn, String penulis, String deskripsi, int tahun){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_JUDUL, judul);
        cv.put(FIELD_ISBN, isbn);
        cv.put(FIELD_PENULIS, penulis);
        cv.put(FIELD_DESKRIPSI,deskripsi);
        cv.put(FIELD_TAHUN, tahun);


        long eksekusi = db.update(TABLE_NAME, cv, "id = ?", new String[]{id});
        return eksekusi;
    }

    public long hapusBuku(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long eksekusi = db.delete(TABLE_NAME, "id = ?", new String[]{id});
        return eksekusi;
    }

    // membuat query
    // Cusor mengambil data 1 1 dalam database
    public Cursor bacaSemuaData(){ //ini akan diambil dimain activity
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
