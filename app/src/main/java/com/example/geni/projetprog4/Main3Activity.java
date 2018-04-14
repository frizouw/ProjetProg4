package com.example.geni.projetprog4;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Main3Activity extends AppCompatActivity {

    //PROPRIÉTÉS
    private ImageView avatar;                   //Représente l'ImageView de l'avatar
    private Button btnChoisirImage;             //Représente le bouton qui aide l'utilisateur à choisir l'image
    private Button btnInscrire;                 //Représente le bouton pour s'inscrire

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Titre de l'activité
        setTitle("Inscription");
        //Activation du bouton précédent dans la barre d'action : la direction est programmée dans AndroidManifest.xml
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //INITIALISATIONS DES PROPRIÉTÉS
        avatar = (ImageView) findViewById(R.id.imgAvatar);
        btnChoisirImage = (Button) findViewById(R.id.btnChoisirImage);
        btnInscrire = (Button) findViewById(R.id.btnInscription);

        //ÉVÉNEMENTS
        btnChoisirImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

    }

}
