package com.example.geni.projetprog4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Titre de l'activité
        setTitle("Inscription");
        //Activation du bouton précédent dans la barre d'action : la direction est programmé dans AndroidManifest.xml
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
