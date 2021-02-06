package com.example.a_ws_gps;

public class GPSTable {
        public static final String TABLE_NAME = "GPS";
        public final static String GpsId = "GPSId";
        public final static String LON = "LON";
        public final static String LAT = "LAT";
        public final static String Date = "Date";
        public final static String Time = "Time";
        public static final String[] ALL_COLUMNS = new String[]{GpsId, " AS _id", LON, LAT, Date, Time};
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;
        public static  final String SQL_CREATE =
                "CREATE TABLE " + TABLE_NAME +
                        "(" +
                        GpsId + " INTEGER PRIMARY KEY," +
                        LON + " TEXT NOT NULL, " +
                        LAT + " TEXT NOT NULL, " +
                        Date + " TEXT NOT NULL, " +
                        Time + " TEXT NOT NULL" + ")";
        public static final String STMT_INSERT = "INSERT INTO " + TABLE_NAME +
                "(" + LON + "," + LAT + "," + Date + "," + Time + ") " + "VALUES (?,?,?,?)";

    }

