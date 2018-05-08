package com.example.geni.projetprog4;

/**
 * Created by info1 on 2018-05-08.
 */

import android.graphics.Bitmap;
import java.io.Serializable;
public class SerializableBitmap implements Serializable
{
    private final int [] pixels;
    private final int width , height;

    public SerializableBitmap(Bitmap bitmap)
    {
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        pixels = new int [width*height];
        bitmap.getPixels(pixels,0,width,0,0,width,height);
    }

    public Bitmap getBitmap()
    {
        return Bitmap.createBitmap(pixels, width, height, Bitmap.Config.ARGB_8888);
    }
}