package com.example.admin.andassistant.home;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;

public class HomeShowcase extends ShowcaseDialog {

    public static ShowcaseDialog newInstance() {
        return new HomeShowcase();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.showcase_home;
    }

}
