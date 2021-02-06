package com.example.a_ws_gps.model;

public class Patient {
private String lat;
private String lon;
private String dateDat;
private String timeDat;

    public Patient(String lat, String lon, String dateDat, String timeDat) {
        this.lat = lat;
        this.lon = lon;
        this.dateDat = dateDat;
        this.timeDat = timeDat;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getDateDat() {
        return dateDat;
    }

    public void setDateDat(String dateDat) {
        this.dateDat = dateDat;
    }

    public String getTimeDat() {
        return timeDat;
    }

    public void setTimeDat(String timeDat) {
        this.timeDat = timeDat;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", dateDat='" + dateDat + '\'' +
                ", timeDat='" + timeDat + '\'' +
                '}';
    }
}
