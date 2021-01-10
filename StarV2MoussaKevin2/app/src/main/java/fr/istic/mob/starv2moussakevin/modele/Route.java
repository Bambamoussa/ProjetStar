package fr.istic.mob.starv2moussakevin.modele;
import android.database.Cursor;

import androidx.annotation.NonNull;

public class Route {
    private String route_id;
    private String route_short_name;
    private String route_long_name;
    private String route_text_color;
    private String route_color;
    private String direction_id;

    private Route(String route_id, String route_short_name, String route_long_name, String route_text_color, String route_color) {
        this.route_id = route_id;
        this.route_short_name = route_short_name;
        this.route_long_name = route_long_name;
        this.route_text_color = route_text_color;
        this.route_color = route_color;
        this.direction_id = "1";
    }

    public static Route populateRoute(Cursor cursor) {
        Route route = null;
        try {
            String route_id = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns._ID));
            String route_short_name = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.SHORT_NAME));
            String route_long_name = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.LONG_NAME));
            String route_text_color = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR));
            String route_color = cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.COLOR));
            route = new Route(route_id, route_short_name, route_long_name, route_text_color, route_color);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public String getRoute_text_color() {
        return route_text_color;
    }

    public void setRoute_text_color(String route_text_color) {
        this.route_text_color = route_text_color;
    }

    public String getRoute_color() {
        return route_color;
    }

    public void setRoute_color(String route_color) {
        this.route_color = route_color;
    }

    public String getDirection_id() {
        return direction_id;
    }

    public void setDirection_id(String direction_id) {
        this.direction_id = direction_id;
    }

    @NonNull
    @Override
    public String toString() {
        return route_short_name;
    }
}
