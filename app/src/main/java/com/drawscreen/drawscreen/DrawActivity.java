package com.drawscreen.drawscreen;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

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
                saveDraw();
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
    private void saveDraw(){
        File path = Environment.getExternalStorageDirectory();
        File folder = new File(path.getPath() + Environment.DIRECTORY_MUSIC);
        File file = new File(folder,"img.png");
        FileOutputStream utputStream=null;
        Toast.makeText(this,"1",Toast.LENGTH_SHORT).show();
        try {
            Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
            utputStream = new FileOutputStream(file);
            Toast.makeText(this,"3",Toast.LENGTH_SHORT).show();
            Bitmap bitmap = myCanvas.getBitmap();
            Toast.makeText(this,"4",Toast.LENGTH_SHORT).show();
            Bitmap save = Bitmap.createBitmap(320,480, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);

            now.drawRect(new Rect(0,0,320,480),paint);
            now.drawBitmap(bitmap,new Rect(0,0,bitmap.getWidth(),bitmap.getHeight()),new Rect(0,0,320,480),null);
            save.compress(Bitmap.CompressFormat.PNG,100,utputStream);
            Toast.makeText(this,String.valueOf(file.getPath()),Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }
    }
}
