package com.drawscreen.drawscreen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDraw {
    public static File folderUrl;
    public String folder;
    public FileDraw() {
        File card = Environment.getExternalStorageDirectory();
        folder = card.getAbsolutePath()+"/"+Environment.DIRECTORY_PICTURES+"/myDraw";
        folderUrl = new File(folder);
    }

    public boolean saveDraw(Bitmap well,String draw,int x,int y,boolean ban) {
        boolean success = false;
        if (!folderUrl.exists()) {
            success = folderUrl.mkdirs();
        }

        File file = new File(folderUrl, draw);
        if (ban){
            file.delete();
        }

        if (!file.exists()) {
            try {
                success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(file);

            Bitmap save = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);
            now.drawRect(new Rect(0, 0, x, y), paint);
            now.drawBitmap(well, new Rect(0, 0, well.getWidth(), well.getHeight()), new Rect(0, 0, x, y), null);

            if (save == null) {
            }
            save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return success;
    }
    public String[] getDraws(){
        return folderUrl.list();
    }
    public String exisDraw(String draw){
        File d = new File(folderUrl,draw);
        return  ""+d.exists();
    }
}
