package com.nurckye.sleeptracker.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.databinding.*;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.nurckye.sleeptracker.R;
import com.nurckye.sleeptracker.databinding.SummaryScreenBinding;
import com.nurckye.sleeptracker.viewmodels.SummaryViewModel;
import com.nurckye.sleeptracker.workers.BedtimeWorker;

public class SummaryFragment extends Fragment {
    SummaryScreenBinding binding;
    SummaryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.summary_screen,
                container,
                false
        );

        viewModel = new SummaryViewModel(WorkManager.getInstance(getContext()));
        binding.bedtimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = binding.timePicker.getHour();
                int minute = binding.timePicker.getMinute();
                String am_pm;

                viewModel.handleSetBedtime(hour, minute);
            }
        });
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }
}
