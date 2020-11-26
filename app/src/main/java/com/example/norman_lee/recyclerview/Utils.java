package com.example.norman_lee.recyclerview;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    static String LOGCAT = "Pokemon";

    /**
     * Code adapted from https://stackoverflow.com/questions/15662258/how-to-save-a-bitmap-on-internal-storage
     * @param bitmapImage
     * @param name
     * @param context
     * @return
     */

    static String saveToInternalStorage(Bitmap bitmapImage, String name, Context context){
        ContextWrapper cw = new ContextWrapper(context.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,name);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }

    static Bitmap loadImageFromStorage(String path, String name)
    {

        Bitmap b = null;
        try {
            File f=new File(path, name);
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return b;

    }

    /**
     * Load images from res/drawables to a DataSource object
     * Quick fix solution?
     * @param context
     * @param drawablesId arrayList containing drawables ID
     * @return DataSource object
     */

    /**Context is the super-super class of MainActivity*/
    static DataSource firstLoadImages(Context context,  ArrayList<Integer> drawablesId){

        DataSource dataSource = new DataSource();

        for(Integer rid: drawablesId){
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), rid);
            String imageName = context.getResources().getResourceEntryName(rid);
            Log.i(LOGCAT,"" + imageName);
            dataSource.addData(imageName, bitmap, context);
        }

        return dataSource;


    }

}
