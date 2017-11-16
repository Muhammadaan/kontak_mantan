package com.example.ade.kontak_mantan.liibraries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ade on 15/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        //Database yang akan dibuat bernama kontak.db dengan versi = 1
        //File Tersebut berada di: /data/data/packagename/databases/
        super(context,"kontak.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //siapkan SQL-nya
        String sql = "CREATE TABLE kontak(id INTEGER PRIMARY KEY, nama VARCHAR(225),nomor VARCHAR(225))";

        //Eksekusi SQL=-nya
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //method ini dipanggil ketika versi database meningkat
        // hapus dulu tabel2 yang lama
        String sql = "DROP TABLE IF EXISTS kontak";

        db.execSQL(sql);

        //Create lagi tabel yang baru
        this.onCreate(db);
    }

    public Cursor executeRead(String sql)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql,null);

        return cursor;
    }

    public void executeWrite(String sql)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(sql);
    }
}
