package com.nurckye.sleeptracker.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.databinding.*;

import com.nurckye.sleeptracker.R;
import com.nurckye.sleeptracker.databinding.SummaryScreenBinding;
import com.nurckye.sleeptracker.viewmodels.SummaryViewModel;

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

        viewModel = new SummaryViewModel();
        binding.setViewmodel(viewModel);
        return binding.getRoot();
    }
}
