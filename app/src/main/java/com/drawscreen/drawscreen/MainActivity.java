package com.drawscreen.drawscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView lvDrwas;
    private FileDraw fileDraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDrwas = (ListView)findViewById(R.id.lvDraws);
        fileDraw = new FileDraw();
        loadDraw();
        lvDrwas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                runDraw(String.valueOf(lvDrwas.getItemAtPosition(position)));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.IdNew:
                runDrawNew();
                return true;
            case R.id.IdAbout:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void runDrawNew(){
        Intent intent = new Intent(this,DrawActivity.class);
        startActivity(intent);
    }
    private void showAbout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage( getString(R.string.app_application_about));
        builder.setPositiveButton(getString(R.string.app_button_positive), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });
        builder.show();
    }
    private void runDraw(String draw){
        Intent intent = new Intent(this,DrawActivity.class);
        intent.putExtra("draw",draw);
        startActivity(intent);
    }
    private void loadDraw(){
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,fileDraw.getDraws());
        lvDrwas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDraw();
    }
}
