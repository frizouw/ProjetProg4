package com.example.geni.projetprog4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class RecetteIngredientFragment extends Fragment {


    private Recettes recette;
    private Button btnAjoute;
    private String[] ingredients;

    public RecetteIngredientFragment()
    {
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recette_ingredient_fragment_layout, container, false);
        btnAjoute = view.findViewById(R.id.btnAjouterListeEpicerie);

        if(getArguments() != null && getArguments().containsKey("recette"))
        {
            recette = Utils.LIST_RECETTES.get(getArguments().getInt("recette"));
            ingredients = recette.getIngredients().split(";");

            //ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.liste_custom, ingredients);
            ((ListView)view.findViewById(R.id.listIngredient)).setAdapter(new ListeEpicerieAdapter(getContext(), ingredients));
        }

        btnAjoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prendre les items qui sont coches

                //mettre dans une arraylist

                //passer dans une item
                //dans un bundle
                //this, listeEpicerie.class

            }
        });
        return view;
    }
}
