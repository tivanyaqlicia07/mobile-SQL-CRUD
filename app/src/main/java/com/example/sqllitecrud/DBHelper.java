package com.example.sqllitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "biodata.db";
    public static final String TABLE_NAME = "tabel_pegawai";
    public static final int DATABASE_VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAMA";
    public static final String COL_3 = "JABATAN";
    public static final String COL_4 = "ALAMAT";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE tabel_pegawai(id VARCHAR(8) PRIMARY KEY, "
                + "nama TEXT NULL, "
                + "jabatan TEXT NULL, "
                + "alamat TEXT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    //Simpan Data
    public boolean insertData(String id, String nama, String

            jabatan, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, jabatan);
        contentValues.put(COL_4, alamat);
        long result = db.insert(TABLE_NAME, null,
                contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    //Ambil Data
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM tabel_pegawai",
                null);
        return res;
    }
    //Ubah Data
    public boolean updateData(String id, String nama, String jabatan, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, jabatan);
        contentValues.put(COL_4, alamat);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }
    //Hapus Data
    public int deleteData(String nim){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{nim});
    }
}
