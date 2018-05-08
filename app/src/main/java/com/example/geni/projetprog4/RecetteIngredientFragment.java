package com.example.geni.projetprog4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecetteIngredientFragment extends Fragment {

    View view;
    private Recettes recette;
    public RecetteIngredientFragment()
    {
        if(getArguments() != null && getArguments().containsKey("recette"))
            recette = (Recettes) getArguments().get("recette");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recette_ingredient_fragment_layout, container, false);
        return view;
    }
}
