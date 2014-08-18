package com.example.servicesandasynctasks;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servicesandasynctasks.asynctask.ImageAsyncTask;
import com.example.servicesandasynctasks.calculator.ImageCalculator;
import com.example.servicesandasynctasks.calculator.ImageCallback;
import com.example.servicesandasynctasks.calculator.RandomImageCalculator;
import com.example.servicesandasynctasks.service.ImageService;


public class MainActivity extends Activity implements ImageCallback {

    ComplicatedImage image;
    ImageCalculator calculator = new RandomImageCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ComplicatedImage) findViewById(R.id.image);

        Button drawMain = (Button) findViewById(R.id.drawMain);
        drawMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DIMENS", image.getWidthValue() + " " + image.getHeightValue());
                drawImage(calculator.calculateImage(image.getWidthValue(), image.getHeightValue()));
            }
        });

        Button drawAsync = (Button) findViewById(R.id.drawAsync);
        drawAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageAsyncTask asyncTask = new ImageAsyncTask(image.getWidthValue(), image.getHeightValue(), MainActivity.this);
                asyncTask.execute(calculator);
            }
        });

        registerReceiver(new Receiver(), new IntentFilter(ImageService.IMAGE_READY));
        Button drawService = (Button) findViewById(R.id.drawService);
        drawService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:
                Intent intent = new Intent(MainActivity.this, ImageService.class);
                intent.putExtra("width", image.getWidthValue());
                intent.putExtra("height", image.getHeightValue());
                intent.putExtra("calc", calculator);
                startService(intent);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void drawImage(Bitmap bitmap) {
        Log.d("SETTING", "setting " + bitmap);
        image.setImage(bitmap);
    }

    class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("MAIN", "Received " + intent.getAction());
            if (intent.getAction().equals(ImageService.IMAGE_READY)) {
                bindAndGetImage();
            }
        }
    }

    private void bindAndGetImage() {
        Log.d("MAIN", "Binding");
        Intent intent = new Intent(this, ImageService.class);
        //Don't do this here in a real app
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("BINDER", "onServiceConnected");
            ImageService.MyBinder binder = (ImageService.MyBinder) iBinder;
            Bitmap bmp = binder.getService().getBitmap();
            drawImage(bmp);
            unbindService(connection);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
