package com.example.servicesandasynctasks.calculator;

import android.graphics.Color;

/**
 * Created by elsabe.ros on 2014/08/16.
 */
public class RandomImageCalculator extends ImageCalculator {

    @Override
    public int calculateColour(int x, int y) {
        int r = (int) Math.floor(Math.random()*255.0);
        int b = (int) Math.floor(Math.random()*255.0);
        int g = (int) Math.floor(Math.random()*255.0);
        return Color.rgb(r, g, b);
    }

}
