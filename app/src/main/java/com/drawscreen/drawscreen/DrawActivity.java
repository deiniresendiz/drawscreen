package com.drawscreen.drawscreen;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DrawActivity extends AppCompatActivity {
    private static MyCanvas myCanvas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        ConstraintLayout myLayout= (ConstraintLayout)findViewById(R.id.myLayout);
        myCanvas = new MyCanvas(this);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onColorItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.IdColorBlack:
                item.setChecked(true);
                myCanvas.colors(0);
                break;
            case R.id.IdColorGreen:
                item.setChecked(true);
                myCanvas.colors(1);
                break;
            case R.id.IdColorBlue:
                item.setChecked(true);
                myCanvas.colors(2);
                break;
            case R.id.IdColorRed:
                item.setChecked(true);
                myCanvas.colors(3);
                break;
            case R.id.IdColorWhite:
                item.setChecked(true);
                myCanvas.colors(4);
                break;
        }
    }
    public void onBrushtemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.IdColorBlack:
                item.setChecked(true);
                myCanvas.colors(0);
                break;
            case R.id.IdColorGreen:
                item.setChecked(true);
                myCanvas.colors(1);
                break;
            case R.id.IdColorBlue:
                item.setChecked(true);
                myCanvas.colors(2);
                break;
            case R.id.IdColorRed:
                item.setChecked(true);
                myCanvas.colors(3);
                break;
            case R.id.IdColorWhite:
                item.setChecked(true);
                myCanvas.colors(4);
                break;
        }
    }

}
