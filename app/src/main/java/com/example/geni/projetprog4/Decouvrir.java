package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class Decouvrir extends Fragment {

    //Le fragment du profile lorsqu'on choisi profile dans le drawer
    private View vue;
    private Spinner spinnerRecette;
    private Spinner spinnerPays;
    private Spinner spinnerDifficulter;

    private Button btnRecherche;
    private Button btnAleatoire;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vue = inflater.inflate(R.layout.decouvrir, container, false);

        spinnerRecette = (Spinner) vue.findViewById(R.id.spinnerType);
        spinnerPays= (Spinner) vue.findViewById(R.id.spinnerPays);
        spinnerDifficulter = (Spinner) vue.findViewById(R.id.spinnerDifficulte);
        btnRecherche = (Button) vue.findViewById(R.id.btnRechercher);
        btnAleatoire = (Button) vue.findViewById(R.id.btnAleatoire);

        //Remplir  le spinner de type de recette
        String[] typeRecette = {"Déjeuner", "Dîner", "Souper", "Dessert", "Entrée"};
        ArrayAdapter<String> adapterRecette = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, typeRecette);
        adapterRecette.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerRecette.setAdapter(adapterRecette);

        //Remplir le spinner de pays
        String[] pays = {"Canada", "Japon", "Angleterre", "France", "Chine"};
        ArrayAdapter<String> adapterPays = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, pays);
        adapterPays.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerPays.setAdapter(adapterPays);

        //Remplir le spinner de niveau de difficulté
        Integer[] niveauDifficulter = {1, 2, 3};
        ArrayAdapter<Integer> adapterDifficulter = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, niveauDifficulter);
        adapterDifficulter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDifficulter.setAdapter(adapterDifficulter);

        btnRecherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnAleatoire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return vue;
    }
}
