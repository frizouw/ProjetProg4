package com.example.geni.projetprog4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

//Classe qui telecharge l'image du url de la DB
public class DownloadImage extends AsyncTask<String, Void, Bitmap>
{
    //Proprietes
    private Object obj;

    //Constructeur
    public DownloadImage(Object obj)
    {
        this.obj = obj;
    }

    //Methode doInBackground du Asyntask
    protected Bitmap doInBackground(String... urls)
    {
        String urldisplay = urls[0];
        Bitmap bitmap = null;
        try
        {
            InputStream in = new URL(urldisplay).openStream();
            bitmap = BitmapFactory.decodeStream(in);
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap)
    {
        if(obj instanceof Users)
            ((Users)obj).setImage(bitmap);

        if(obj instanceof Recettes)
            ((Recettes)obj).setImage(bitmap);

        if(obj instanceof ImageView)
            ((ImageView)obj).setImageBitmap(bitmap);
    }
}