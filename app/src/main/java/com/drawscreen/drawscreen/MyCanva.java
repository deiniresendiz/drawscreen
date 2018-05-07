package com.drawscreen.drawscreen;

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
    public Paint brush;
    public Paint mbrush;
    public Path path;
    private Bitmap bitmap;
    private int sizeBrush, colorBrush;
    private Canvas canvas;
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
    //no tengo idea de como hacer que funcione este pincel demonios
    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawRGB(255,255,255);
        canvas.drawBitmap(bitmap,0,0,mbrush);
        canvas.drawPath(path,brush);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                brush.reset();
                path.moveTo(x,y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y);
                break;
        }
        invalidate();
        return true;
    }
    //cambios de colores
    public void colors(int color){
        colorBrush = color;
        switch (color){
            case 0:
                brush.setColor(Color.BLACK);
                break;
            case 1:
                brush.setColor(Color.GREEN);
                break;
            case 2:
                brush.setColor(Color.BLUE);
                break;
            case 3:
                brush.setColor(Color.RED);
                break;
            case 4:
                brush.setColor(Color.WHITE);
                break;
            default:
                brush.setColor(Color.BLACK);
        }
    }
    public void sizeBrush(int size){
        sizeBrush = size;
        brush.setStrokeWidth(size*2);
    }
    public void draft(int size){
        brush.setColor(Color.WHITE);
        brush.setStrokeWidth(size*2);
    }
    public void brushReset(){
        sizeBrush(sizeBrush);
        colors(colorBrush);
    }
}
