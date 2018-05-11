package com.example.geni.projetprog4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecetteIngredientFragment extends Fragment {

    //Proprietes
    private Recettes recette;
    private Button btnAjoute;
    private String[] ingredients;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.recette_ingredient_fragment_layout, container, false);
        btnAjoute = view.findViewById(R.id.btnAjouterListeEpicerie);
        listView = view.findViewById(R.id.listIngredient);

        if(getArguments() != null && getArguments().containsKey("recette"))
        {
            recette = Utils.MES_RECETTES.get(getArguments().getInt("recette"));
            ingredients = recette.getIngredients().split(";");
            ArrayList<ItemEpicerie> listeIngredients = new ArrayList<>();
            for(String s : ingredients)
            {
                listeIngredients.add(new ItemEpicerie(s, false));
            }

            //set avec le custom adapter
            listView.setAdapter(new ListeEpicerieAdapter(getContext(), listeIngredients));
        }

        btnAjoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ingredients = "";
                //prendre les items qui sont coches
                //mettre dans une arraylist
                for(int i = 0; i < listView.getCount(); i++)
                {
                    ItemEpicerie item = (ItemEpicerie)listView.getItemAtPosition(i);
                    if(item.isChecked)
                    {
                        ingredients += item.nom.trim() + "$$";
                    }
                }
                //demande d'envoie du cote serveur
                new ThreadClient.ThreadEnvoi(String.format("addIngredient::userID=%s;ingredient=%s", Utils.CURRENT_USER.getUsername(), ingredients.substring(0,ingredients.length() - 2))).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        return view;
    }
}
