package com.example.admin.andassistant.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.infrastructure.BaseActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends BaseActivity {

    public static final int NAV_HOST_FRAGMENT = R.id.navHostFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initNavigation();
    }

    private void initNavigation() {
        NavController navController = Navigation.findNavController(this, NAV_HOST_FRAGMENT);

        NavigationUI.setupActionBarWithNavController(this, navController);
        NavigationUI.setupWithNavController(this.<BottomNavigationView>findViewById(R.id.bottomNavigation), navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, NAV_HOST_FRAGMENT).navigateUp();
    }

}
