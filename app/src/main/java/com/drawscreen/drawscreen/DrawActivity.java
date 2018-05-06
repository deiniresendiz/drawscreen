package com.drawscreen.drawscreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class DrawActivity extends AppCompatActivity {
    private static MyCanva myCanvas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ConstraintLayout myLayout= (ConstraintLayout)findViewById(R.id.myLayout);
        myCanvas = new MyCanva(this);
        myLayout.addView(myCanvas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_draw,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.IdBrush:
                myCanvas.brushReset();
                return true;
            case R.id.IdSave:
                myCanvas.saveDraw();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onColorItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.IdColorBlack:
                myCanvas.colors(0);
                break;
            case R.id.IdColorGreen:
                myCanvas.colors(1);
                break;
            case R.id.IdColorBlue:
                myCanvas.colors(2);
                break;
            case R.id.IdColorRed:
                myCanvas.colors(3);
                break;
            case R.id.IdColorWhite:
                myCanvas.colors(4);
                break;
        }
        item.setChecked(true);
    }
    public void onBrushItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.IdBrushOne:
                myCanvas.sizeBrush(1);
                break;
            case R.id.IdBrushTwo:
                myCanvas.sizeBrush(2);
                break;
            case R.id.IdBrushFour:
                myCanvas.sizeBrush(3);
                break;
            case R.id.IdBrushSix:
                myCanvas.sizeBrush(4);
                break;
            case R.id.IdBrushEight:
                myCanvas.sizeBrush(5);
                break;
            case R.id.IdBrushTen:
                myCanvas.sizeBrush(6);
                break;
        }
        item.setChecked(true);
    }
    public void onDraftItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.IdDraftOne:
                myCanvas.draft(1);
                break;
            case R.id.IdDraftTwo:
                myCanvas.draft(2);
                break;
            case R.id.IdDraftFour:
                myCanvas.draft(3);
                break;
            case R.id.IdDrafthSix:
                myCanvas.draft(4);
                break;
            case R.id.IdDraftEight:
                myCanvas.draft(5);
                break;
            case R.id.IdDraftTen:
                myCanvas.draft(6);
                break;
        }
        item.setChecked(true);
    }
    public class MyCanva extends View {
        public Paint brush = new Paint();
        public Path path = new Path();
        private String Accion = "accion";
        private int sizeBrush, colorBrush;
        public MyCanva(Context context) {
            super(context);
            sizeBrush(1);
            colors(0);
            brush.setStyle(Paint.Style.STROKE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawRGB(255,255,255);
            canvas.drawPath(path,brush);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(x,y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(x,y);
                    break;
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
        public void saveDraw(){
            try {
                getDrawingCache().compress(Bitmap.CompressFormat.PNG,100, new FileOutputStream(new File(Environment.DIRECTORY_MUSIC).getPath()+"/gfjfj.png"));
                System.out.print("si");
            }catch (Exception e){
                System.out.print("sknk");
            }
        }
    }
}
