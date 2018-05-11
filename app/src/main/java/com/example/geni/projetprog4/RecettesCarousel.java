package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class RecettesCarousel extends Fragment
{
    //Proprietes
    private View v;
    private CarouselView carouselPicker1;

    int[] images = { R.drawable.recette1, R.drawable.dejeuners };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.accueil, container, false);
        carouselPicker1 = (CarouselView) v.findViewById(R.id.carouselView);

        carouselPicker1.setPageCount(2);
        carouselPicker1.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });

        return v;
    }

}
