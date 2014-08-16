package com.example.servicesandasynctasks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                Log.d("DIMENS", image.getWidthValue() + " " + image.getHeightValue());
                calculator.calculateImage(image.getWidth(), image.getHeight(), MainActivity.this);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void drawImage(Bitmap bitmap) {
        image.setImage(bitmap);
    }
}
