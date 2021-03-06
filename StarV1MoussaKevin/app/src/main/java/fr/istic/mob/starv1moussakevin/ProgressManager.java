package fr.istic.mob.starv1moussakevin;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;

import androidx.core.app.NotificationCompat;

public class ProgressManager {

    NotificationCompat.Builder builder;
    private Context context;
    private Handler handler;
    private NotificationManager manager;
    private String title;
    private String desc;

    public ProgressManager(Context context, String title, int maxProgress, boolean vibration) {
        createNotif(context, title, maxProgress, vibration);
        this.title = title;
    }

    public void createNotif(final Context context, String title, int maxProgress, boolean vibration) {
        manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final String channelId = "task_channel";
        String channelName = "task_name";
        builder = new NotificationCompat.Builder(context, channelId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }

        builder.setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOnlyAlertOnce(true)
                .setProgress(maxProgress, 0, false)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        manager.notify(1, builder.build());

    }

    public NotificationCompat.Builder getBuilder() {
        return this.builder;
    }

    public NotificationManager getNotifiationManager() {
        return this.manager;
    }
}