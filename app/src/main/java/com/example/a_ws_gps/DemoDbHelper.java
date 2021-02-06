package com.example.a_ws_gps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DemoDbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "gps.db";
    private final static int DB_VERSION = 3;
    public DemoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GPSTable.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(GPSTable.SQL_DROP);
        onCreate(db);
    }
}
