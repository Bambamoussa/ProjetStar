package fr.istic.mob.starv1moussakevin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {

        SharedPreferences prefs;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            prefs = getApplicationContext().getSharedPreferences("fr.istic.mob.starv1moussakevin", Context.MODE_PRIVATE);
            setContentView(R.layout.activity_main);
            //if internet is available
            if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();
                PeriodicWorkRequest saveRequest =
                        new PeriodicWorkRequest.Builder(Updates.class, 15, TimeUnit.MINUTES)
                                .setConstraints(constraints)
                                .build();
                WorkManager.getInstance(getApplicationContext())
                        .enqueue(saveRequest);
            }
        }

        @Override
        protected void onRestart() {
            super.onRestart();
        }

        @Override
        protected void onNewIntent(Intent intent) {
            super.onNewIntent(intent);
            if (intent.getExtras() != null) {
                String uriToZip = intent.getExtras().getString("uriToZip");
                if (uriToZip != null && !uriToZip.isEmpty()) {
                    Constraints constraints = new Constraints.Builder()
                            .setRequiredNetworkType(NetworkType.CONNECTED)
                            .build();
                    Data.Builder data = new Data.Builder();
                    data.putString("uri", uriToZip);
                    OneTimeWorkRequest saveRequest =
                            new OneTimeWorkRequest.Builder(Download.class)
                                    .setInputData(data.build())
                                    .setConstraints(constraints)
                                    .build();
                    WorkManager.getInstance(getApplicationContext())
                            .enqueue(saveRequest);
                }
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
