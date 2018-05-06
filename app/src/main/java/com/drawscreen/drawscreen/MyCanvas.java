package com.drawscreen.drawscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvas extends View {
    public Paint brush = new Paint();
    public Path path = new Path();
    private float x = 10;
    private float y = 10;
    private String Accion = "accion";
    private int sizeBrush, colorBrush;
    public MyCanvas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        brush.setStyle(Paint.Style.STROKE);
        sizeBrush(1);
        colors(0);
        if(Accion.equals("Down")){
            path.moveTo(x,y);
        }
        if(Accion.equals("Move")){
            path.lineTo(x,y);
        }
        canvas.drawPath(path,brush);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            Accion = "Down";
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE){
            Accion = "Move";
        }
        invalidate();
        return true;
    }
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
