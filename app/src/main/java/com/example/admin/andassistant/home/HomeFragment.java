package com.example.admin.andassistant.home;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.databinding.FragmentHomeBinding;
import com.example.admin.andassistant.infrastructure.BaseFragment;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;

public class HomeFragment extends BaseFragment {
    FragmentHomeBinding binding;

    @LayoutRes
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected ShowcaseDialog getShowcaseInstance() {
        return HomeShowcase.newInstance();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);

        greetUser();
    }

    private void greetUser() {
        binding.homeGreeting.setText(getString(R.string.home_greeting, userApi.getCurrentUserName()));
    }

}
