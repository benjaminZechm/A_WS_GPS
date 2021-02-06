package com.example.a_ws_gps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LocationManager lm;
    LocationListener ls;
    private static final int RQ_ACCESS_FINE_LOCATION = 123;
    private boolean isGPsAllowed = false;
    TextView gpsData;
    TextView latData;
    TextView DateTV;
    TextView TimeTV;
    Button anzButton;
    DemoDbHelper dbHelper;
    SQLiteDatabase db;
    Location loc = null;
    boolean isFirst = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerSystemService();
        checkPermissionGPS();
        gpsData = findViewById(R.id.gpsTextView);
        latData = findViewById(R.id.latTextView);
        DateTV = findViewById(R.id.dateTextView);
        TimeTV = findViewById(R.id.timeTextView);
        dbHelper = new DemoDbHelper(this);
        db = dbHelper.getReadableDatabase();
        anzButton = findViewById(R.id.anzeigenButton);
        anzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAct();
            }
        });
    }

    private void changeAct() {
        Intent in = new Intent(this, MainActivity2.class);
        startActivity(in);
    }

    private void registerSystemService() {
        lm = (LocationManager) getSystemService(LocationManager.class);
    }

    private void checkPermissionGPS(){
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, RQ_ACCESS_FINE_LOCATION);
            isGPsAllowed = false;

        }else{
            gpsGranted();
            isGPsAllowed = true;
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode != RQ_ACCESS_FINE_LOCATION) return;
        if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
            Toast t = Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_LONG);
            t.show();
        }else{
            gpsGranted();
        }
    }

    private void gpsGranted() {
        Toast t = Toast.makeText(getApplicationContext(), "Permission allowed", Toast.LENGTH_LONG);
        t.show();
        isGPsAllowed = true;


        //showAvailableProviders();
        ls = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
            displayLocation(location);


            }
        };
    }

    private void displayLocation(Location location) {
        double lat = location==null ? -1 : location.getLatitude();
        double lon = location==null ? -1 : location.getLongitude();
        gpsData.setText(String.format("%.4f", lon));
        latData.setText(String.format("%.4f", lat));
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        DateTV.setText(ld.toString());
        TimeTV.setText(lt.getHour() + ":"+ lt.getMinute());
//        if(isFirst) {
//            if (location.distanceTo(loc) > 5) {
//                int l = (int) location.distanceTo(loc);
                db.execSQL(GPSTable.STMT_INSERT, new String[]{String.valueOf(lat), String.valueOf(lon), ld.toString(), lt.toString()});
//                loc = location;
//            }
//        }else{
//            loc = location;
//            db.execSQL(GPSTable.STMT_INSERT, new String[]{String.valueOf(lat), String.valueOf(lon), ld.toString(), lt.toString()});
//            isFirst = true;
//        }

    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if(isGPsAllowed) {
            lm.requestLocationUpdates(lm.GPS_PROVIDER, 3000, 5, ls);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isGPsAllowed) lm.removeUpdates(ls);
    }


}