package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


public class Decouvrir extends Fragment {

    //Le fragment du profile lorsqu'on choisi profile dans le drawer
    View vue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vue = inflater.inflate(R.layout.decouvrir, container, false);
        return vue;
    }
}
