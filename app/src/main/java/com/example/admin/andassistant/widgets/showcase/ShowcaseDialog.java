package com.example.admin.andassistant.widgets.showcase;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.infrastructure.BaseApplication;

import javax.inject.Inject;

public abstract class ShowcaseDialog extends DialogFragment {

    @LayoutRes
    public abstract int getLayoutResId();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Activity activity = requireActivity();
        View root = LayoutInflater.from(activity).inflate(getLayoutResId(), null);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return new AlertDialog.Builder(activity, R.style.ShowcaseDialog)
                .setView(root)
                .setCancelable(true)
                .create();
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        if (window != null) {
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        }
    }

    public boolean isApplicable(ShowcasePreferences prefs) {
        return prefs.isApplicable(this.getClass().getSimpleName());
    }

    public void markAsWatched(ShowcasePreferences prefs) {
        prefs.markAsWatched(this.getClass().getSimpleName());
    }
}
