package com.example.servicesandasynctasks.calculator;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.servicesandasynctasks.MainActivity;

/**
 * Created by elsabe.ros on 2014/08/16.
 */
public abstract class ImageCalculator {
    public abstract int calculateColour(int x, int y);

    public Bitmap calculateImage(int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Log.d("CALCULATE", "(" + i + ", " + j + ")");
                bitmap.setPixel(i,j,calculateColour(i,j));
            }
        }
        return bitmap;
    }
}
