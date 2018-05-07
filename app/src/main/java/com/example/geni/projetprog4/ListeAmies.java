package com.example.geni.projetprog4;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

public class ListeAmies extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout du fragment a la classe
        v = inflater.inflate(R.layout.liste_amies, container,false);
        return  v;
    }
}
