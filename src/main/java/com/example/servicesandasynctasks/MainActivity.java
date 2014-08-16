package com.example.servicesandasynctasks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.servicesandasynctasks.asynctask.ImageAsyncTask;
import com.example.servicesandasynctasks.calculator.ImageCalculator;
import com.example.servicesandasynctasks.calculator.ImageCallback;
import com.example.servicesandasynctasks.calculator.RandomImageCalculator;


public class MainActivity extends Activity implements ImageCallback {

    ComplicatedImage image;
    ImageCalculator calculator = new RandomImageCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ComplicatedImage) findViewById(R.id.image);
        Button start = (Button) findViewById(R.id.draw);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Log.d("DIMENS", image.getWidthValue() + " " + image.getHeightValue());
                drawImage(calculator.calculateImage(image.getWidthValue(), image.getHeightValue()));*/
                ImageAsyncTask asyncTask = new ImageAsyncTask(image.getWidthValue(), image.getHeightValue(), MainActivity.this);
                asyncTask.execute(calculator);
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
}
