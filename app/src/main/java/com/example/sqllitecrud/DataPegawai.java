package com.example.sqllitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class DataPegawai extends AppCompatActivity {
    DBHelper myDb;
    EditText edID, edNama, edJabatan, edAlamat;
    Button btnTambahData;
    Button btnTampilData;
    Button btnEditData;
    Button btnHapusData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pegawai);
        myDb = new DBHelper(this);
        edID = (EditText) findViewById(R.id.editID);
        edNama = (EditText) findViewById(R.id.editNama);
        edJabatan = (EditText) findViewById(R.id.editJabatan);
        edAlamat = (EditText) findViewById(R.id.editAlamat);
        btnTambahData = (Button) findViewById(R.id.btnSimpan);
        btnEditData = (Button) findViewById(R.id.btnEdit);
        btnHapusData = (Button) findViewById(R.id.btnHapus);
        btnTampilData = (Button) findViewById(R.id.btnTampil);
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    //Simpan data
    public void addData() {
        btnTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(edID.getText().toString(),
                        edNama.getText().toString(),
                        edJabatan.getText().toString(),
                        edAlamat.getText().toString());
                if (isInserted == true)
                    Toast.makeText(DataPegawai.this, "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DataPegawai.this, "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Edit data
    //Edit Data
    public void updateData() {
        btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(edID.getText().toString(),
                        edNama.getText().toString(),
                        edJabatan.getText().toString(),
                        edAlamat.getText().toString());

                if (isUpdate == true)
                    Toast.makeText(DataPegawai.this, "Data Berhasil Diperbaharui", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DataPegawai.this, "Data Gagal Diperbaharui", Toast.LENGTH_LONG).show();
            }

        });
    }

    //Hapus Data
    public void deleteData() {
        btnHapusData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(edID.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(DataPegawai.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(DataPegawai.this, "Data Gagal Dihapus", Toast.LENGTH_LONG).show();
            }
        });
    }

    //Tampil Seluruh Data
    public void viewAll() {
        btnTampilData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0) {
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("NAMA : " + res.getString(1) + "\n");
                    buffer.append("JABATAN : " + res.getString(2) + "\n");
                    buffer.append("ALAMAT : " + res.getString(3) + "\n");
                }

                showMessage("Data", buffer.toString());
            }

        });
    }

    //Alert Dialog
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
}
}