package com.nurckye.sleeptracker.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;

public class SummaryViewModel extends ViewModel {
    public MutableLiveData<String> name;

    public SummaryViewModel() {
        super();
        name = new MutableLiveData<String>();
        name.setValue("Radu");
    }

    public String getInitials() {
        String[] result = this.name.getValue().split(" ");
        if (result.length == 1) return String.valueOf(result[0].charAt(0));
        return String.valueOf(result[0].charAt(0)) + String.valueOf(result[1].charAt(0));
    }
}
