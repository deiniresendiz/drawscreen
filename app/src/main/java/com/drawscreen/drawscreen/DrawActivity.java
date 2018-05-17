package com.drawscreen.drawscreen;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;


public class DrawActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private MyCanva myCanvas ;
    private FileDraw fileDraw;
    private boolean ban = false;
    private String nameDraw = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        Bundle bundle = getIntent().getExtras();
        fileDraw = new FileDraw();
        myCanvas = (MyCanva)findViewById(R.id.IdCanva);
        if(bundle != null){
            nameDraw = bundle.getString("draw");
            loadDraw();
            ban = true;
        }
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
            case R.id.IdColors:
                //reseteo del pincel a los valores anteriores
                myCanvas.brushReset();
                return true;
            case R.id.IdSave:
                //metodo que no funciona
                checkPermission();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //ya funciona el menu para cambiar los colores
    public void onColorItemClick(MenuItem item){
        switch (item.getItemId()){
            case R.id.IdColorBlack:
                myCanvas.colors(Color.BLACK);
                break;
            case R.id.IdColorGreen:
                myCanvas.colors(Color.GREEN);
                break;
            case R.id.IdColorBlue:
                myCanvas.colors(Color.BLUE);
                break;
            case R.id.IdColorRed:
                myCanvas.colors(Color.RED);
                break;
            case R.id.IdColorWhite:
                myCanvas.colors(Color.WHITE);
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

    private void saveDraw(String draw) {
        String minusculas = "[a-z]";
        String letrasNumGuin = "^[a-z0-9[_]]*$";
        if (Pattern.matches(minusculas,""+draw.toCharArray()[0])){
            if(Pattern.matches(letrasNumGuin,draw)){
                if(!fileDraw.exisDraw(draw)){
                    saveDrawFile(draw);
                }else
                    Toast.makeText(this,"Ya existe un archivo con el mismo nombre",Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this,"No se Admiten caracteres especiales y mayusculas",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this,"Se debe iniciar con una letra minuscula",Toast.LENGTH_SHORT).show();
        save();

    }
    private void saveDrawFile(String draw){
        if (fileDraw.saveDraw(myCanvas.getBitmap(),draw,(int)myCanvas.x,(int)myCanvas.y,ban))
            Toast.makeText(this,"Se guardo Correctamente",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"No se guardo",Toast.LENGTH_LONG).show();
        finish();
    }
    public void loadDraw(){
        Bitmap img = BitmapFactory.decodeFile(fileDraw.folder+"/"+nameDraw);
        myCanvas.setBitmap(img);
    }
    private void save(){
        if (ban){
            saveDrawFile(nameDraw);
        }else{
            final EditText editText = new EditText(DrawActivity.this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(getString(R.string.app_name_draw));
            builder.setView(editText);
            builder.setPositiveButton(getString(R.string.app_button_positive), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    saveDraw(String.valueOf(editText.getText()));
                }
            });
            builder.setCancelable(true);
            builder.show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(REQUEST_CODE_ASK_PERMISSIONS == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "OK Permissions granted ! :-) " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
                save();
            } else {
                Toast.makeText(this, "Permissions are not granted ! :-( " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void checkPermission() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

            Toast.makeText(this, "This version is not Android 6 or later " + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();

        } else {

            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSIONS);

                Toast.makeText(this, "Requesting permissions", Toast.LENGTH_LONG).show();

            }else if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "The permissions are already granted ", Toast.LENGTH_LONG).show();
                save();

            }

        }

        return;
    }

}
