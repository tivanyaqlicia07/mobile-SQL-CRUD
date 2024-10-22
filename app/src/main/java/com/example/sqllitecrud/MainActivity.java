package com.example.sqllitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button tmblLogin, tmblLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        tmblLogin = (Button) findViewById(R.id.btnLogin);
        tmblLogout = (Button) findViewById(R.id.btnLogout);
        tmblLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String nama = username.getText().toString();
                String pass = password.getText().toString();
                if (nama.isEmpty() || pass.isEmpty()){
                    showErrorLogin();
                } else {
                    if (nama.equals("admin") && pass.equals("admin")){
                        successLogin();
                    } else {
                        errorLogin();
                    }
                }
            }
        });
        tmblLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
    public void showErrorLogin(){
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show();
    }
    public void successLogin(){
        Intent intent = new Intent(MainActivity.this, DataPegawai.class);
        startActivity(intent);
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
    }
    public void errorLogin(){
        Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show();
    }
}
