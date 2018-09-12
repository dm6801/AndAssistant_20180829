package com.example.admin.andassistant.infrastructure;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.andassistant.usersapi.UserApi;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;
import com.example.admin.andassistant.widgets.showcase.ShowcasePreferences;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {
    @Inject
    public UserApi userApi;

    @Inject
    public ShowcasePreferences showcasePrefs;

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract ShowcaseDialog getShowcaseInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(getLayoutResId(), container, false);
        inject();
        initShowcase();
        return root;
    }

    @SuppressWarnings("ConstantConditions")
    private void inject() {
        ((BaseApplication) getContext().getApplicationContext()).getComponent().inject(this);
    }

    private void initShowcase() {
        ShowcaseDialog showcase = getShowcaseInstance();

        if (showcasePrefs.isEnabled() && showcase != null && showcase.isApplicable(showcasePrefs)) {
            displayShowcase(showcase);
        }
    }

    private void displayShowcase(ShowcaseDialog showcase) {
        showcase.setTargetFragment(this, 0);
        showcase.show(requireFragmentManager(), getClass().getSimpleName());
        showcase.markAsWatched(showcasePrefs);
    }
}
