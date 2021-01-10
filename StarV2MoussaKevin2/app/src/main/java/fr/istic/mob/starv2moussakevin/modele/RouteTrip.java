package fr.istic.mob.starv2moussakevin.modele;

import android.database.Cursor;
public class RouteTrip {

    private String stop_name;
    private String stop_id;
    private String trip_h;
    private String direction_id;

    public RouteTrip(String stop_name, String stop_id, String trip_h, String direction_id) {
        this.stop_name = stop_name;
        this.stop_id = stop_id;
        this.trip_h = trip_h;
        this.direction_id = direction_id;
    }


    public static RouteTrip populate(Cursor cursor) {
        RouteTrip routeTrip = null;
        try {
            String stop_name = cursor.getString(cursor.getColumnIndex(StarContract.Stops.StopColumns.NAME));
            String stop_id = cursor.getString(cursor.getColumnIndex(StarContract.Stops.StopColumns._ID));
            String trip_headsign = cursor.getString(cursor.getColumnIndex(StarContract.Trips.TripColumns.HEADSIGN));
            String direction_id = cursor.getString(cursor.getColumnIndex(StarContract.Trips.TripColumns.DIRECTION_ID));
            routeTrip = new RouteTrip(stop_name, stop_id, trip_headsign, direction_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routeTrip;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public String getTrip_h() {
        return trip_h;
    }

    public void setTrip_h(String trip_h) {
        this.trip_h = trip_h;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    @Override
    public String toString() {
        return stop_name;
    }
}

