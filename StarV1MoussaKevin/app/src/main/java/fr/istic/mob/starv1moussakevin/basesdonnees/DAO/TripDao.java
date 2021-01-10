package fr.istic.mob.starv1moussakevin.basesdonnees.DAO;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.ArrayList;
import java.util.List;
import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.StarContract;
import fr.istic.mob.starv1moussakevin.basesdonnees.TripEntity;
@Dao
public interface TripDao {

    @Query("Select * from " + StarContract.Trips.CONTENT_PATH)
    List<TripEntity> getTripsList();

    @Query("SELECT DISTINCT "
            + StarContract.Trips.TripColumns.HEADSIGN + ", "
            + StarContract.Trips.TripColumns.DIRECTION_ID + ", "
            + StarContract.Trips.TripColumns.ROUTE_ID +
            " FROM " + StarContract.Trips.CONTENT_PATH +
            " WHERE " + StarContract.Trips.TripColumns.ROUTE_ID + " = :route_id")
    Cursor getTripsListCursor(String route_id);

    @Insert
    void insertAll(ArrayList<TripEntity> tripEntities);

    @Query("DELETE FROM " + StarContract.Trips.CONTENT_PATH)
    void deleteAll();


}