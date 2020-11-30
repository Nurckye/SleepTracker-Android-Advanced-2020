package com.nurckye.sleeptracker.workers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.nurckye.sleeptracker.broadcastreceivers.BedtimeNotificationReceiver;

public class BedtimeWorker extends Worker {
    Context context;

    public BedtimeWorker(@NonNull Context context,
                       @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        this.context = context;
    }


    @NonNull
    @Override
    public Result doWork() {
        Data data = getInputData();
        Log.d("WORK_STARTED", "");

        int hour = data.getInt("hour", 0);
        int minute = data.getInt("minute",0);
        int startMillis = (hour * 3600 + minute * 60) * 1000;


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, BedtimeNotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,  intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // TODO: Remove when hour changed
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startMillis,86400 * 1000, pendingIntent); // ever day at the same hour

        return null;
    }
}