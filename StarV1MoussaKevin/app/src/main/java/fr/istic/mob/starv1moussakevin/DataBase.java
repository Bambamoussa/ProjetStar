package fr.istic.mob.starv1moussakevin;

import android.app.NotificationManager;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.ArrayList;

import fr.istic.mob.starv1moussakevin.basesdonnees.CalendarEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.RouteEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.StarDatabase;
import fr.istic.mob.starv1moussakevin.basesdonnees.StopEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.StopTimeEntity;
import fr.istic.mob.starv1moussakevin.basesdonnees.TripEntity;

public class DataBase extends Worker {

    private Context context;
    private ProgressManager progressManager;

    public DataBase(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        progressManager = new ProgressManager(context, context.getString(R.string.db_loading_status_message), 100, false);
    }

    @NonNull
    @Override
    public Result doWork() {
        progressManager.getBuilder().setProgress(100, 100, true);
        progressManager.getNotifiationManager().notify(1, progressManager.getBuilder().build());
        clearDatabase();
        fillDatabase();
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(1);

        return Result.success();
    }

    private void clearDatabase() {
        StarDatabase.getInstance(context).routeDao().deleteAll();
    }


    private void fillDatabase() {

        Fichiers fichiers = new Fichiers(context.getExternalFilesDir(null).toString());

        ArrayList<RouteEntity> routeEntities = (ArrayList<RouteEntity>) fichiers.readEntitiesFromFile(Constants.ROUTES_FILE, RouteEntity.class);
        StarDatabase.getInstance(context).routeDao().insertAll(routeEntities);

        ArrayList<TripEntity> tripEntities = (ArrayList<TripEntity>) fichiers.readEntitiesFromFile(Constants.TRIPS_FILE, TripEntity.class);
        StarDatabase.getInstance(context).tripDao().insertAll(tripEntities);

        ArrayList<CalendarEntity> calendarEntities = (ArrayList<CalendarEntity>) fichiers.readEntitiesFromFile(Constants.CALENDAR_FILE, CalendarEntity.class);
        StarDatabase.getInstance(context).calendarDao().insertAll(calendarEntities);

        ArrayList<StopEntity> stopEntities = (ArrayList<StopEntity>) fichiers.readEntitiesFromFile(Constants.STOPS_FILE, StopEntity.class);
        StarDatabase.getInstance(context).stopDao().insertAll(stopEntities);

        ArrayList<StopTimeEntity> stopTimeEntities = (ArrayList<StopTimeEntity>) fichiers.readEntitiesFromFile(Constants.STOP_TIME_FILE, StopTimeEntity.class);
        StarDatabase.getInstance(context).stopTimeDao().insertAll(stopTimeEntities);
    }

}