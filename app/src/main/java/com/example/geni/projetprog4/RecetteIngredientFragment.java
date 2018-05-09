package com.example.geni.projetprog4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RecetteIngredientFragment extends Fragment {

    View view;
    private Recettes recette;
    public RecetteIngredientFragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recette_ingredient_fragment_layout, container, false);

        if(getArguments() != null && getArguments().containsKey("recette"))
        {
            recette = Utils.LIST_RECETTES.get(getArguments().getInt("recette"));
            String[] ingredients = recette.getIngredients().split(";");

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.liste_custom, ingredients);
            ((ListView)view.findViewById(R.id.listIngredient)).setAdapter(adapter);
        }
        return view;
    }
}
