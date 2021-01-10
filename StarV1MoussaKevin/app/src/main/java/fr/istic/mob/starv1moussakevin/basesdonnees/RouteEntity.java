package fr.istic.mob.starv1moussakevin.basesdonnees;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.StarContract;

@Entity(tableName = StarContract.BusRoutes.CONTENT_PATH)
public class RouteEntity {

    @NonNull
    @PrimaryKey
    private String _id;

    @ColumnInfo(name = StarContract.BusRoutes.BusRouteColumns.SHORT_NAME)
    private String route_short_name;

    @ColumnInfo(name = StarContract.BusRoutes.BusRouteColumns.LONG_NAME)
    private String route_long_name;

    @ColumnInfo(name = StarContract.BusRoutes.BusRouteColumns.DESCRIPTION)
    private String route_desc;

    @ColumnInfo(name = StarContract.BusRoutes.BusRouteColumns.TYPE)
    private String route_type;

    @ColumnInfo(name = StarContract.BusRoutes.BusRouteColumns.COLOR)
    private String route_color;

    @ColumnInfo(name = StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR)
    private String route_text_color;

    public RouteEntity(@NonNull String _id, String route_short_name, String route_long_name, String route_desc, String route_type, String route_color, String route_text_color) {
        this._id = _id;
        this.route_short_name = route_short_name;
        this.route_long_name = route_long_name;
        this.route_desc = route_desc;
        this.route_type = route_type;
        this.route_color = route_color;
        this.route_text_color = route_text_color;
    }

    @NonNull
    public String get_id() {
        return _id;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
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

    public String getRoute_desc() {
        return route_desc;
    }

    public void setRoute_desc(String route_desc) {
        this.route_desc = route_desc;
    }

    public String getRoute_type() {
        return route_type;
    }

    public void setRoute_type(String route_type) {
        this.route_type = route_type;
    }

    public String getRoute_color() {
        return route_color;
    }

    public void setRoute_color(String route_color) {
        this.route_color = route_color;
    }

    public String getRoute_text_color() {
        return route_text_color;
    }

    public void setRoute_text_color(String route_text_color) {
        this.route_text_color = route_text_color;
    }
}
