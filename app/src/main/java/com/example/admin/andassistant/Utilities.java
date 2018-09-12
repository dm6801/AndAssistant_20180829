package com.example.admin.andassistant;

import android.graphics.Color;

public class Utilities {
    public static int colorWithAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }
}
