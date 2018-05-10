package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class ListeEpicerie extends Fragment {

    View v;
    ListView listeEpicerie;
    CheckBox check;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout de la liste a la classe
        v = inflater.inflate(R.layout.liste_epicerie,container,false);
        listeEpicerie = (ListView)v.findViewById(R.id.listeEpicerie);
        check = (CheckBox)v.findViewById(R.id.list_view_item_checkbox);
        //MODE
        listeEpicerie.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //listeEpicerie.setChoiceMode(ListView.CHOICE_MODE_NONE);

        listeEpicerie.setAdapter( new ListeEpicerieAdapter(getActivity(), Utils.LISTE_EPICERIE));

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
