package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListeEpicerie extends Fragment {

    View v;
    ListView listeEpicerie;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout de la liste a la classe
        v = inflater.inflate(R.layout.liste_epicerie,container,false);
        listeEpicerie = (ListView)v.findViewById(R.id.listeEpicerie);
        listeEpicerie.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //TEST
        String[] values = new String[] { "Pomme","patate", "Boeuf", };

        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        ArrayAdapter<String>adapter = new ArrayAdapter<>( getActivity(),R.layout.liste_epicerie,list);
        listeEpicerie.setAdapter(adapter);

        //lors de la selection
        listeEpicerie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return  v;
    }

    //prendre les ingredients de l'utilisateur, afficher dans le arrraylist les items dans la listeView via le arrayadapter
}
