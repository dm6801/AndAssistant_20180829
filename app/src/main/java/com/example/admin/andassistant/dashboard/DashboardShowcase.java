package com.example.admin.andassistant.dashboard;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;

public class DashboardShowcase extends ShowcaseDialog {

    public static ShowcaseDialog newInstance() {
        return new DashboardShowcase();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.showcase_dashboard;
    }

}

