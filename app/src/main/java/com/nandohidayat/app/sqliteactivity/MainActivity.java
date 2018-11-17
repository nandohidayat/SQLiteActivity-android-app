package com.nandohidayat.app.sqliteactivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.List;

public class MainActivity extends ListActivity {
    /** Called when the activity is first created. */
    String dataSiswa[] = null;
    String dS[] = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        DatabaseHandler db = new DatabaseHandler(this);
        // Tambah Siswa
        db.addSiswa(new Siswa("001", "Ghiyatsi Miftahur Rahmat"));
        // Membaca Semua Siswa
        List<Siswa> siswa = db.getSemuaSiswa();
        dataSiswa = new String[siswa.size()];
        dS = new String[siswa.size()];
        int i=0;
        for(Siswa s : siswa) {
            dataSiswa[i] = s.getNis() + " - "+ s.getNama();
            dS[i] = s.getNis();
            i++;
        }

        setListAdapter(new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, dataSiswa));
        registerForContextMenu(getListView());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // TODOAuto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Action");
        menu.add(0,0,0,"Tambah");
        menu.add(0,1,1,"Hapus");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODOAuto-generated method stub
        try {
            switch(item.getItemId()){
                case 0:{
                    Class c = Class.forName("com.nandohidayat.app.sqliteactivity.TambahActivity");
                    Intent i =  new Intent(MainActivity.this, c);
                    startActivity(i);
                    break;
                }
                case 1:{
                    DatabaseHandler db = new DatabaseHandler(this);
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    //String[] args = {String.valueOf(info.id) };
                    db.deleteRow(dS[info.position]);
                    Class c = Class.forName("com.nandohidayat.app.sqliteactivity.MainActivity");
                    Intent i = new Intent(MainActivity.this, c);
                    startActivity(i);
                    break;
                }
            }
        } catch(ClassNotFoundException e) {
        // TODOAuto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
}
