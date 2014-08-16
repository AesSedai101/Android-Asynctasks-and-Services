package com.example.servicesandasynctasks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by elsabe on 2014/08/15.
 */
public class ComplicatedImage extends SurfaceView implements SurfaceHolder.Callback {
    private static final int SIZE = 100;
    private final SurfaceHolder holder;
    Bitmap bmp;

    public ComplicatedImage(Context context) {
        super(context);
        bmp = Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.ARGB_8888);
        holder = getHolder();
        holder.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Canvas c = holder.lockCanvas();
        draw(c);
        holder.unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, SIZE, SIZE, null);
    }

    public void setPixelColour(int x, int y, int color) {
        bmp.setPixel(x, y, color);
        Canvas c = holder.lockCanvas();
        draw(c);
        holder.unlockCanvasAndPost(c);
    }

}
