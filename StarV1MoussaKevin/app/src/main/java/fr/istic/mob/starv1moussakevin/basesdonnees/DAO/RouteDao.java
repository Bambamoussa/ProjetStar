package fr.istic.mob.starv1moussakevin.basesdonnees.DAO;

import android.database.Cursor;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.ArrayList;
import java.util.List;
import fr.istic.mob.starv1moussakevin.basesdonnees.RouteEntity;
@Dao
public interface RouteDao {

    @Query("Select * from " + StarContract.BusRoutes.CONTENT_PATH)
    List<RouteEntity> getRouteList();

    @Query("Select distinct " +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.SHORT_NAME + ", " +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns._ID + ", " +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.COLOR + ", " +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR + ", " +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.LONG_NAME +
            " from " + StarContract.BusRoutes.CONTENT_PATH +
            " order by " + StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns._ID)
    Cursor getRouteListCursor();

    @Query("SELECT DISTINCT " +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns._ID + "," +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.SHORT_NAME + "," +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.COLOR + "," +
            StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR +
            " FROM " + StarContract.BusRoutes.CONTENT_PATH + "," + StarContract.Trips.CONTENT_PATH + "," + StarContract.Stops.CONTENT_PATH + "," + StarContract.StopTimes.CONTENT_PATH +
            " WHERE " + StarContract.Stops.CONTENT_PATH + "." + StarContract.Stops.StopColumns.NAME + "= :stop_name" +
            " AND " + StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns._ID + "= " + StarContract.Trips.CONTENT_PATH + "." + StarContract.Trips.TripColumns.ROUTE_ID +
            " AND " + StarContract.Trips.CONTENT_PATH + "." + StarContract.Trips.TripColumns._ID + "= " + StarContract.StopTimes.CONTENT_PATH + "." + StarContract.StopTimes.StopTimeColumns.TRIP_ID +
            " AND " + StarContract.StopTimes.CONTENT_PATH + "." + StarContract.StopTimes.StopTimeColumns.STOP_ID + "= " + StarContract.Stops.CONTENT_PATH + "." + StarContract.Stops.StopColumns._ID +
            " ORDER BY " + StarContract.BusRoutes.CONTENT_PATH + "." + StarContract.BusRoutes.BusRouteColumns._ID)
    Cursor getRoutesForStop(String stop_name);

    @Insert
    void insertAll(ArrayList<RouteEntity> dataEntities);

    @Query("DELETE FROM " + StarContract.BusRoutes.CONTENT_PATH)
    void deleteAll();
}