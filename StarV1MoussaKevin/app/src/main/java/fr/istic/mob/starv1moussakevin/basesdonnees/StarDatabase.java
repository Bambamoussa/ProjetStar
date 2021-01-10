package fr.istic.mob.starv1moussakevin.basesdonnees;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.CalendarDao;
import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.RouteDao;
import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.StopDao;
import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.StopTimeDao;
import fr.istic.mob.starv1moussakevin.basesdonnees.DAO.TripDao;

@Database(entities = {RouteEntity.class, TripEntity.class, StopEntity.class, StopTimeEntity.class, CalendarEntity.class}, version = 2, exportSchema = false)
public abstract class StarDatabase extends RoomDatabase {

    public static final String DB_NAME = "star";
    private static StarDatabase instance;

    public static synchronized StarDatabase getInstance(Context theContext) {
        // singleton pattern
        if (instance == null) {
            instance = Room.databaseBuilder(theContext.getApplicationContext(), StarDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public static void clear() {
        instance = null;
    }

    public abstract RouteDao routeDao();

    public abstract TripDao tripDao();

    public abstract StopDao stopDao();

    public abstract StopTimeDao stopTimeDao();

    public abstract CalendarDao calendarDao();

    @Override
    public void clearAllTables() {
    }
}