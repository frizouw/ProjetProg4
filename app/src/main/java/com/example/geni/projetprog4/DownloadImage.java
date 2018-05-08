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
    ImageView bmImage;

    public DownloadImage(ImageView bmImage)
    {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls)
    {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try
        {
            InputStream in = new URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result)
    {
        bmImage.setImageBitmap(result);
    }

    public static class DownloadImageBitmap extends AsyncTask<String, Void, Bitmap>
    {
        private Recettes recette;
        public DownloadImageBitmap(Recettes recette)
        {
            this.recette = recette;
        }

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
            recette.setImage(bitmap);
        }
    }
}