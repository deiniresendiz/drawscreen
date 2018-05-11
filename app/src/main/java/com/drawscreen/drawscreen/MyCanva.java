package com.drawscreen.drawscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyCanva extends View {
    private Paint brush, mbrush;
    private Path path;
    private Bitmap bitmap;
    private int sizeBrush, colorBrush;
    private Canvas canvas;
    public int y,x;
    public MyCanva(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        path = new Path();
        brush = new Paint();
        mbrush = new Paint(Paint.DITHER_FLAG);
        brush.setAntiAlias(true);
        brush.setDither(true);
        colors(0);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeCap(Paint.Cap.ROUND);
        sizeBrush(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }
    //no tengo idea de como hacer que funcione este pincel
    @Override
    protected void onDraw(Canvas canvas) {
        x = canvas.getWidth();
        y=canvas.getHeight();
        canvas.drawRGB(255,255,255);
        canvas.drawBitmap(bitmap,0,0,mbrush);
        canvas.drawPath(path,brush);
    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float px = event.getX();
        float py = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //brush.reset();
                path.moveTo(px,py);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(px,py);
                break;
            case MotionEvent.ACTION_UP:
                path.lineTo(px, py);
                canvas.drawPath(path, brush);
                path.reset();
                break;
        }
        invalidate();
        return true;
    }
    //cambio de colore
    public void colors(int color){
        colorBrush = color;
        brush.setColor(color);
    }
    public void sizeBrush(int size){
        sizeBrush = size;
        brush.setStrokeWidth(size*2);
    }
    public void draft(int size){
        brush.setColor(Color.WHITE);
        brush.setStrokeWidth(size*4);
    }
    public void brushReset(){
        sizeBrush(sizeBrush);
        colors(colorBrush);
    }
    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bmp;
    }

}
