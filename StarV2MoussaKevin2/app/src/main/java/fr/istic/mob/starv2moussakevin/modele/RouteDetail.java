package fr.istic.mob.starv2moussakevin.modele;

import android.database.Cursor;

public class RouteDetail {

    private String stop_name;
    private String tps_arrive;

    public RouteDetail(String stop_name, String tps_arrive) {
        this.stop_name = stop_name;
        this.tps_arrive = tps_arrive;
    }

    public static RouteDetail populateRouteDetail(Cursor cursor) {
        RouteDetail routeDetail = null;
        try {
            String stop_name = cursor.getString(cursor.getColumnIndex(StarContract.Stops.StopColumns.NAME));
            String arrival_time = cursor.getString(cursor.getColumnIndex(StarContract.StopTimes.StopTimeColumns.ARRIVAL_TIME));
            routeDetail = new RouteDetail(stop_name, arrival_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routeDetail;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public String getTps_arrive() {
        return tps_arrive;
    }

    public void setTps_arrive(String tps_arrive) {
        this.tps_arrive = tps_arrive;
    }

    @Override
    public String toString() {
        return stop_name + " : " + tps_arrive;
    }


}

