package com.example.servicesandasynctasks.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.NinePatch;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.servicesandasynctasks.asynctask.ImageAsyncTask;
import com.example.servicesandasynctasks.calculator.ImageCalculator;
import com.example.servicesandasynctasks.calculator.ImageCallback;

/**
 * Created by elsabe.ros on 2014/08/16.
 */
public class ImageService extends Service implements ImageCallback {

    public static final String IMAGE_READY = "imageReady";
    private Bitmap bitmap;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int w = intent.getIntExtra("width", 0);
        int h = intent.getIntExtra("height", 0);
        ImageCalculator calculator = (ImageCalculator) intent.getSerializableExtra("calc");

        Log.d("SERVICE", "Calculating " + w+ " " + h + " using " + calculator);

        if (bitmap == null) {
            ImageAsyncTask asyncTask = new ImageAsyncTask(w, h, this);
            asyncTask.execute(calculator);
        }else {
            drawImage(bitmap);
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(this);
    }

    @Override
    public void drawImage(Bitmap bitmap) {
        Log.d("SERVICE drawImage", "Broadcasting " + bitmap);
        this.bitmap = bitmap;
        Intent intent = new Intent(IMAGE_READY);
        sendBroadcast(intent);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public class MyBinder extends Binder {
        private final ImageService service;

        public MyBinder(ImageService imageService) {
            this.service = imageService;
        }

        public ImageService getService() {
            return service;
        }
    }
}
