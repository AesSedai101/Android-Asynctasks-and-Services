package com.example.servicesandasynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by elsabe on 2014/08/15.
 */
public class ComplicatedImage extends View {
    private Bitmap bmp;
    private int width;
    private int height;

    public ComplicatedImage(Context context) {
        super(context);
    }

    public ComplicatedImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ComplicatedImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void init() {
        if (bmp == null){
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        Log.d("MEASURE", width + " " + height);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        init();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bmp != null) {
            canvas.drawColor(Color.WHITE);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawBitmap(bmp, 0, 0, paint);
        }
    }


    public int getWidthValue() {
        return width;
    }

    public int getHeightValue() {
        return height;
    }

    public void setImage(Bitmap bitmap) {
        bmp = bitmap;
        refreshDrawableState();
    }
}
