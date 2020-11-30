package com.nurckye.sleeptracker.broadcastreceivers;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.nurckye.sleeptracker.R;

public class BedtimeNotificationReceiver extends BroadcastReceiver {
    private final String ANDROID_CHANNEL_NAME = "Bedtime channel";
    private final String ANDROID_CHANNEL_ID = "BEDTIME_CHANNEL";
    private Context context;


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel androidChannel = new NotificationChannel(ANDROID_CHANNEL_ID,
                    ANDROID_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            androidChannel.enableLights(true);
            androidChannel.enableVibration(true);
            androidChannel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(androidChannel);
        }

    }


    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            createNotificationChannel();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ANDROID_CHANNEL_ID)
                    .setContentTitle("It's bedtime!")
                    .setContentText("You should probably go to sleep now")
                    .setSmallIcon(R.drawable.name_initials)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

            notificationManager.notify(0, builder.build());


        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
