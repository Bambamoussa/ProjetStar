package fr.istic.mob.starv2moussakevin.modele;

import android.database.Cursor;

public class Calendar {

    private String trip_id;
    private String trip_h;
    private String stop_id;
    private String arret_nom;
    private String temps_arrive;

    public Calendar(String trip_id, String trip_h, String arret_nom, String temps_arrive) {
        this.trip_id = trip_id;
        this.trip_h = trip_h;
        this.arret_nom = arret_nom;
        this.temps_arrive = temps_arrive;
    }


    public static Calendar populate(Cursor cursor, String stop) {
        Calendar calendar = null;
        try {
            String trip_id = cursor.getString(cursor.getColumnIndex(StarContract.Trips.TripColumns._ID));
            String trip_headsign = cursor.getString(cursor.getColumnIndex(StarContract.Trips.TripColumns.HEADSIGN));
            String stop_name = stop;
            String arrival_time = cursor.getString(cursor.getColumnIndex(StarContract.StopTimes.StopTimeColumns.ARRIVAL_TIME));
            calendar = new Calendar(trip_id, trip_headsign, stop_name, arrival_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public String getTrip_h() {
        return trip_h;
    }

    public void setTrip_h(String trip_h) {
        this.trip_h = trip_h;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getTemps_arrive() {
        return temps_arrive;
    }

    public void setTemps_arrive(String temps_arrive) {
        this.temps_arrive = temps_arrive;
    }

    public String getArret_nom() {
        return arret_nom;
    }

    public void setArret_nom(String arret_nom) {
        this.arret_nom = arret_nom;
    }

    @Override
    public String toString() {
        return "Ligne : " + trip_id + "stop : " + arret_nom + " : arrive à" + temps_arrive;
    }
}


