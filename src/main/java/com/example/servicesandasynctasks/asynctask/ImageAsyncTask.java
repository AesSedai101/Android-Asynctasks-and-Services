package com.example.servicesandasynctasks.asynctask;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.example.servicesandasynctasks.calculator.ImageCalculator;
import com.example.servicesandasynctasks.calculator.ImageCallback;

/**
 * Created by elsabe.ros on 2014/08/16.
 */
public class ImageAsyncTask extends AsyncTask<ImageCalculator, Void, Bitmap> {

    private final int width;
    private final int height;
    private final ImageCallback callback;

    public ImageAsyncTask(int w, int h, ImageCallback callback) {
        this.width = w;
        this.height = h;
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(ImageCalculator... imageCalculators) {
       return imageCalculators[0].calculateImage(width, height);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        callback.drawImage(bitmap);
    }
}
