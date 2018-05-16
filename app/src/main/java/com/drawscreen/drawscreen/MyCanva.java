package com.drawscreen.drawscreen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class MyCanva extends View {
    private Paint brush, mbrush;
    private Path path;
    private Bitmap bitmap,img;
    private int sizeBrush, colorBrush;
    private Canvas canvas;
    public int x,y;
    public MyCanva(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        brush = new Paint();
        mbrush = new Paint(Paint.DITHER_FLAG);
        brush.setAntiAlias(true);
        brush.setDither(true);
        colors(Color.BLACK);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeCap(Paint.Cap.ROUND);
        sizeBrush(1);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(0, 0, 0, 0);
        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }
    //no tengo idea de como hacer que funcione este pincel
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x=canvas.getWidth();
        y=canvas.getHeight();
        if(img != null) {
            canvas.drawBitmap(img,0,0,mbrush);
        }
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
    public void setBitmap(Bitmap img){
        this.img = img;
    }
}
