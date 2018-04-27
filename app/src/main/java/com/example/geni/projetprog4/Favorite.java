package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends Fragment {

    //Proprietes
    View v;
    RecyclerView recyclerView;
    List<Recettes> list_recette;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout a la classe
        v = inflater.inflate(R.layout.favorite, container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerview_id);

        //initialiser la liste
        list_recette = new ArrayList<>();
        list_recette.add(new Recettes());

        RecetteAdapter adapter = new RecetteAdapter(getActivity(),list_recette);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setAdapter(adapter);

        return v;
    }


}
