package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ListeEpicerie extends Fragment {

    //Variables
    private View v;
    private ListView listeEpicerie;
    private Button btnRetirer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout de la liste a la classe
        v = inflater.inflate(R.layout.liste_epicerie,container,false);
        listeEpicerie = (ListView)v.findViewById(R.id.listeEpicerie);
        btnRetirer = (Button) v.findViewById(R.id.btnRetirer);

        //prendre les items selectionnes des recettes
        listeEpicerie.setAdapter( new ListeEpicerieAdapter(getActivity(), Utils.LISTE_EPICERIE));

        //action pour le bouton de retirer l'element de la liste
        btnRetirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retire les elements selectionnees dans la liste
                String ingredients = "";
                for(int i = 0; i < listeEpicerie.getCount(); i++)
                {
                    ItemEpicerie item = (ItemEpicerie)listeEpicerie.getItemAtPosition(i);
                    if(item.isChecked)
                    {
                        ingredients += item.nom.trim() + "$$";
                    }
                }
                new ThreadClient.ThreadEnvoi(String.format("removeIngredient::userID=%s;ingredient=%s", Utils.CURRENT_USER.getUsername(), ingredients.substring(0,ingredients.length() - 2))).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        return  v;
    }
}
