package com.example.a_ws_gps.model;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.animation.PathInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a_ws_gps.DemoDbHelper;
import com.example.a_ws_gps.R;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    DemoDbHelper dbHelper;
    SQLiteDatabase db;
    ListView lv;
    ArrayAdapter<Patient> myAdapter;
    List<Patient> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHelper = new DemoDbHelper(this);
        db= dbHelper.getReadableDatabase();
        lv = findViewById(R.id.lv);

        bindAdapterToListView(lv);

        Cursor rows = db.rawQuery("select * from GPS where GPSId > ?",new String[]{"0"});
        while(rows.moveToNext()){
            Patient p = new Patient(rows.getString(2),rows.getString(1),rows.getString(3),rows.getString(4));
            items.add(p);
        }
        myAdapter.notifyDataSetChanged();
    }

    private void bindAdapterToListView(ListView lv) {
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        lv.setAdapter(myAdapter);

    }


}