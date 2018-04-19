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

public class RecettesCarousel extends Fragment {

    View v;
    CarouselPicker carouselPicker1, carouselPicker2, carouselPicker3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.recettes_menu, container, false);
        carouselPicker1 = (CarouselPicker)v.findViewById(R.id.carousel1);
        carouselPicker2 = (CarouselPicker)v.findViewById(R.id.carousel2);
        carouselPicker3 = (CarouselPicker)v.findViewById(R.id.carousel3);

        //SOURCE: https://github.com/GoodieBag/CarouselPicker
        //Carousel 1 with images
        List<CarouselPicker.PickerItem> itemsImages1 = new ArrayList<>();
        //ajouter les images dans la liste
        itemsImages1.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        itemsImages1.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher_round));
        itemsImages1.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        CarouselPicker.CarouselViewAdapter imageAdapter = new CarouselPicker.CarouselViewAdapter(this,itemsImages1,0);
        carouselPicker1.setAdapter(imageAdapter);

        //Carousel 2 with text
        List<CarouselPicker.PickerItem>itemsText = new ArrayList<>();
        //ajouter le text dans la liste
        itemsText.add(new CarouselPicker.TextItem("One",20));
        itemsText.add(new CarouselPicker.TextItem("Two",20));
        itemsText.add(new CarouselPicker.TextItem("Three",20));
        CarouselPicker.CarouselViewAdapter textAdapter = new CarouselPicker.CarouselViewAdapter(this,itemsText,0);
        carouselPicker2.setAdapter(textAdapter);

        //Carousel 3 with mix
        List<CarouselPicker.PickerItem>mixItems = new ArrayList<>();
        //ajouter les dans la liste
        mixItems.add(new CarouselPicker.TextItem("One",20));
        mixItems.add(new CarouselPicker.DrawableItem(R.mipmap.ic_launcher));
        mixItems.add(new CarouselPicker.TextItem("Three",20));
        CarouselPicker.CarouselViewAdapter mixAdapter = new CarouselPicker.CarouselViewAdapter(this,mixItems,0);
        carouselPicker3.setAdapter(mixAdapter);
        return v;
    }

}
