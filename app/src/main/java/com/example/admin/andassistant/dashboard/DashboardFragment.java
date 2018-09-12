package com.example.admin.andassistant.dashboard;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.databinding.FragmentDashboardBinding;
import com.example.admin.andassistant.infrastructure.BaseFragment;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;

import androidx.navigation.Navigation;

public class DashboardFragment extends BaseFragment {
    @LayoutRes
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected ShowcaseDialog getShowcaseInstance() {
        return DashboardShowcase.newInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);

        binding.navNotes.setOnClickListener(v -> navigateTo(R.id.action_navDashboard_to_notesFragment));
    }

    private void navigateTo(@IdRes int navActionResId) {
        Navigation.findNavController(requireActivity(), R.id.navHostFragment).navigate(navActionResId);
    }
}
