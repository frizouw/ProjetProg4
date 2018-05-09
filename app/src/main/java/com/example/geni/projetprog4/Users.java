package com.example.geni.projetprog4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by info1 on 2018-05-09.
 */

public class Users
{
    private String username, pays, courriel, urlImage;
    private int points;
    private Bitmap image;

    public Users(String username, String pays, String courriel, String urlImage, int points, ImageView img)
    {
        this.username = username;
        this.pays = pays;
        this.courriel = courriel;
        this.urlImage = urlImage;
        if(urlImage.startsWith("http"))
        {
            new DownloadImage(img).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urlImage);
        }
        else
        {
            image = BitmapFactory.decodeFile(urlImage);
        }
        this.points = points;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
