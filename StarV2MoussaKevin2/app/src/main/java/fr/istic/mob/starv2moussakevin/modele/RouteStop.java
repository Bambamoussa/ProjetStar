package fr.istic.mob.starv2moussakevin.modele;

import android.database.Cursor;

public class RouteStop {

    private String route_name;
    private String stop_id;
    private String route_id;
    private String trip_id;
    private String route_color;
    private String route_txt_color;

    public RouteStop(String route_name, String stop_id, String trip_id, String route_id, String route_color, String route_txt_color) {
        this.route_name = route_name;
        this.stop_id = stop_id;
        this.trip_id = trip_id;
        this.route_id = route_id;
        this.route_color = route_color;
        this.route_txt_color = route_txt_color;
    }

    public static RouteStop populate(Cursor cursor) {
        RouteStop routeStop = null;
        try {
            String route_name = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.SHORT_NAME));
            String stop_id = cursor.getString(cursor.getColumnIndex(StarContract.Stops.StopColumns._ID));
            String trip_id = cursor.getString(cursor.getColumnIndex(StarContract.Trips.TripColumns._ID));
            String route_id = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns._ID));
            String route_bkg_color = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.COLOR));
            String route_txt_color = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR));
            routeStop = new RouteStop(route_name, stop_id, trip_id, route_id, route_bkg_color, route_txt_color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routeStop;
    }

    public String getRoute_name() {
        return this.route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getRoute_color() {
        return this.route_color;
    }

    public void setRoute_color(String route_color) {
        this.route_color = route_color;
    }

    public String getRoute_txt_color() {
        return this.route_txt_color;
    }

    public void setRoute_txt_color(String route_txt_color) {
        this.route_txt_color = route_txt_color;
    }
}
