package com.nurckye.sleeptracker.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.nurckye.sleeptracker.workers.BedtimeWorker;


public class SummaryViewModel extends ViewModel {
    public MutableLiveData<String> name;
    private final WorkManager workManager;

    public SummaryViewModel(WorkManager workManager) {
        super();
        name = new MutableLiveData<String>();
        name.setValue("Radu");
        this.workManager = workManager;
    }


    public String getInitials() {
        String[] result = this.name.getValue().split(" ");
        if (result.length == 1) return String.valueOf(result[0].charAt(0));
        return String.valueOf(result[0].charAt(0)) + String.valueOf(result[1].charAt(0));
    }

    public void handleSetBedtime(int hour, int minute) {
        Log.i("BEDTIME_HOUR_PICKED", hour + ":" + minute);

        Data inputData = new Data.Builder()
                .putInt("hour", hour)
                .putInt("minute", minute)
                .build();

        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(BedtimeWorker.class)
                .setInputData(inputData)
                .build();

        workManager.beginUniqueWork(
                "bedtime-notification",
                ExistingWorkPolicy.REPLACE,
                request)
                .enqueue();
    }
}
