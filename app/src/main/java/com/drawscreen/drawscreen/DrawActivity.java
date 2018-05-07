package com.drawscreen.drawscreen;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DrawActivity extends AppCompatActivity {
    private MyCanva myCanvas ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        myCanvas = (MyCanva)findViewById(R.id.IdDraw);
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
                //reseteo del pincel a los valores anteriores
                myCanvas.brushReset();
                return true;
            case R.id.IdSave:
                //metodo que no funciona
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //ya funciona el menu para cambiar los colores
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
    //menu del groso del pincel
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
    //el borrador funciona a medias se necesita arreglar
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
}
