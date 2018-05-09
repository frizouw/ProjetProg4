package com.example.geni.projetprog4;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;

public class Decouvrir extends Fragment {

    //Le fragment du profile lorsqu'on choisi profile dans le drawer
    private View vue;
    private Spinner spinnerRecette;
    private Spinner spinnerPays;
    private Spinner spinnerDifficulter;
    private ListView listResultat;
    private Button btnRecherche;
    private Button btnAleatoire;
    private ArrayList<String> recetteRechercher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        vue = inflater.inflate(R.layout.decouvrir, container, false);

        spinnerRecette = (Spinner) vue.findViewById(R.id.spinnerType);
        spinnerPays= (Spinner) vue.findViewById(R.id.spinnerPays);
        spinnerDifficulter = (Spinner) vue.findViewById(R.id.spinnerDifficulte);
        btnRecherche = (Button) vue.findViewById(R.id.btnRechercher);
        btnAleatoire = (Button) vue.findViewById(R.id.btnAleatoire);
        listResultat = (ListView) vue.findViewById(R.id.listRecette);

        //Remplir  le spinner de type de recette
        String[] typeRecette = {"Déjeuner", "Dîner", "Souper", "Dessert", "Entrée"};
        ArrayAdapter<String> adapterRecette = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, typeRecette);
        adapterRecette.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerRecette.setAdapter(adapterRecette);

        //Remplir le spinner de pays
        String[] pays = {"Canada", "Japon", "Angleterre", "France", "Chine", "USA", "Italie", "Thailand", "Inde", "Coreen", "Espagne", "Russie", "Liban"};
        ArrayAdapter<String> adapterPays = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, pays);
        adapterPays.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerPays.setAdapter(adapterPays);

        //Remplir le spinner de niveau de difficulté
        Integer[] niveauDifficulter = {1, 2, 3};
        ArrayAdapter<Integer> adapterDifficulter = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, niveauDifficulter);
        adapterDifficulter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerDifficulter.setAdapter(adapterDifficulter);

        //Quand on appuie sur bouton Rechercher du mode découvrir
        btnRecherche.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                recetteRechercher = new ArrayList<>();

                for (Recettes recette : Utils.LIST_RECETTES)
                {
                    if (recette.getType().equals(spinnerRecette.getSelectedItem().toString()) && recette.getPays().equals(spinnerPays.getSelectedItem().toString()) && recette.getNiveau() == Integer.parseInt(spinnerDifficulter.getSelectedItem().toString()))
                    {
                        recetteRechercher.add(recette.getNom());
                    }
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.liste_custom, recetteRechercher);
                listResultat.setAdapter(adapter);
            }
        });

        //Quand on appuie sur le bouton Aléatoire du mode découvrir (ce qui permet d'obtenir une recette aléatoire)
        btnAleatoire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                recetteRechercher = new ArrayList<>();
                Random aleatoire = new Random();
                int recettePosition = aleatoire.nextInt(Utils.LIST_RECETTES.size());
                recetteRechercher.add(Utils.LIST_RECETTES.get(recettePosition).getNom());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.liste_custom, recetteRechercher);
                listResultat.setAdapter(adapter);
            }
        });

        //Quand on clique sur la recette dans la liste
        listResultat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int recettePos = 0;
                for (Recettes recette : Utils.LIST_RECETTES)
                {
                    if (recette.getNom().equals(parent.getItemAtPosition(position).toString()))
                    {
                        recettePos = Utils.LIST_RECETTES.indexOf(recette);
                        break;
                    }
                }
                Intent i = new  Intent(getActivity(), PageRecette.class);
                i.putExtra("recette", recettePos);
                Log.i("pos", String.valueOf(position));
                startActivity(i);
            }
        });
        return vue;
    }
}
