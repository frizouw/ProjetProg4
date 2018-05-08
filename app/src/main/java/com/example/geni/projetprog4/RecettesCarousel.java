package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.goodiebag.carouselpicker.CarouselPicker;

public class RecettesCarousel extends Fragment
{
    View v;
    CarouselPicker carouselPicker1, carouselPicker2, carouselPicker3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.accueil, container, false);
        carouselPicker1 = (CarouselPicker)v.findViewById(R.id.carousel1);

        //SOURCE: https://github.com/GoodieBag/CarouselPicker
        //Carousel 1 with images
        List<CarouselPicker.PickerItem> itemsImages1 = new ArrayList<>();
        //ajouter les images dans la liste
        itemsImages1.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        itemsImages1.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher_round));
        itemsImages1.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(getActivity(),itemsImages1,0);
        carouselPicker1.setAdapter(imageAdapter);

        return v;
    }

}
