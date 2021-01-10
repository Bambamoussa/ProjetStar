package fr.istic.mob.starv1moussakevin;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Decompres extends Worker {

    
    ProgressManager progressManager;
    private String pathToZipFile;
    private String targetLocation;
    private Context context;

    public Decompres(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
        this.pathToZipFile = context.getExternalFilesDir(null) + "/starTimetables";
        this.targetLocation = context.getExternalFilesDir(null) + "/starTimetables";
    }

    @NonNull
    @Override
    public Result doWork() {

        unzip(pathToZipFile, targetLocation);

        return Result.success();
    }


    private void unzip(String pathToZipFile, String targetLocation) {
        int zipSize;
        int unzipProgress = 0;

        createDir(targetLocation);

        try {
            ZipFile zipFile = new ZipFile(pathToZipFile);
            zipSize = zipFile.size();
            progressManager = new ProgressManager(context, context.getString(R.string.timetables_unzip_status_message), zipSize, false);
            progressManager.getNotifiationManager().notify(1, progressManager.getBuilder().build());
            FileInputStream fin = new FileInputStream(pathToZipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {
                if (ze.isDirectory()) {
                    createDir(ze.getName());
                } else {
                    unzipProgress++;
                    showProgress(zipSize, unzipProgress);
                    FileOutputStream fout = new FileOutputStream(targetLocation + ze.getName());
                    streamCopy(zin, fout);

                    zin.closeEntry();
                    fout.close();

                }
            }
            zin.close();
            progressManager.getNotifiationManager().cancel(1);
            startDbLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startDbLoading() {
        OneTimeWorkRequest saveRequest =
                new OneTimeWorkRequest.Builder(DataBase.class)
                        .build();
        WorkManager.getInstance(getApplicationContext())
                .enqueue(saveRequest);
    }


    private void streamCopy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[32 * 1024]; // play with sizes..
        int readCount;
        while ((readCount = in.read(buffer)) != -1) {
            out.write(buffer, 0, readCount);
        }
    }


    private void createDir(String targetLoacation) {
        File file = new File(targetLoacation);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    private void showProgress(int zipSize, int unzipProgress) {
        progressManager.getBuilder().setProgress(zipSize, unzipProgress, false);
        progressManager.getNotifiationManager().notify(1, progressManager.getBuilder().build());
    }
}
