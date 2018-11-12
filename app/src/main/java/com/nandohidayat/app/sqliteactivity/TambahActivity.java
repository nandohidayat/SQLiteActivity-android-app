package com.nandohidayat.app.sqliteactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TambahActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah);
        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText editNis = (EditText) findViewById(R.id.editNis);
        final EditText editNama = (EditText) findViewById(R.id.editNama);
        Button btnTambah = (Button) findViewById(R.id.btnTambah);
        Button btnBatal = (Button) findViewById(R.id.btnBatal);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nis = editNis.getText().toString();
                String nama = editNama.getText().toString();
                db.addSiswa(new Siswa(nis, nama));
                editNis.setText("");
                editNama.setText("");
                try{
                    Class c = Class.forName("com.nandohidayat.app.sqliteactivity.MainActivity");
                    Intent i = new Intent(TambahActivity.this, c);
                    startActivity(i);
                } catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto - generated method stub
                try {
                    Class c = Class.forName("com.nandohidayat.app.sqliteactivity.MainActivity");
                    Intent i = new Intent(TambahActivity.this,c);
                    startActivity(i);
                } catch (ClassNotFoundException e) {
                    // TODO Auto - generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}