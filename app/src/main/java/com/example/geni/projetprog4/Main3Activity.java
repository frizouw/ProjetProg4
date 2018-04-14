package com.example.geni.projetprog4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {

    //Propriétés
    private ImageView avatar;                   //Représente l'ImageView de l'avatar
    private Button btnChoisirImage;             //Représente le bouton qui aide l'utilisateur à choisir l'image
    private boolean changementImage = false;    //Pour savoir si l'image a changée

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Titre de l'activité
        setTitle("Inscription");
        //Activation du bouton précédent dans la barre d'action : la direction est programmé dans AndroidManifest.xml
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialisations des propriétés
        avatar = (ImageView) findViewById(R.id.imgAvatar);
        btnChoisirImage = (Button) findViewById(R.id.btnChoisirImage);

    }
}
