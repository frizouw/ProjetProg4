package com.example.geni.projetprog4;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main3Activity extends AppCompatActivity {

    //PROPRIÉTÉS
    private ImageView avatar;                   //Représente l'ImageView de l'avatar
    private Button btnChoisirImage;             //Représente le bouton qui aide l'utilisateur à choisir l'image
    private Button btnInscrire;                 //Représente le bouton pour s'inscrire
    public static BD bd;                        //Représente la base de données
    final int REQUEST_CODE_GALLERY = 999;             //Représente le code pour accèder à la gallerie

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Titre de l'activité
        setTitle("Inscription");
        //Activation du bouton précédent dans la barre d'action : la direction est programmée dans AndroidManifest.xml
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //INITIALISATIONS DES PROPRIÉTÉS/OBJETS
        avatar = (ImageView) findViewById(R.id.imgAvatar);
        btnChoisirImage = (Button) findViewById(R.id.btnChoisirImage);
        btnInscrire = (Button) findViewById(R.id.btnInscription);

        //Création de la BD
        bd = new BD(openOrCreateDatabase("CookingJarBD", MODE_PRIVATE, null));
        //Création des tables
        bd.createTable();

        /*ÉVÉNEMENTS
        Choisir une image dans son téléphone*/
        btnChoisirImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityCompat.requestPermissions(Main3Activity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_GALLERY);
            }
        });

        //Terminer/Valider l'inscription
        btnInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

    }

    //Connaître si la demande a été accepté ou non, pour accèder à la gallerie d'image pour l'avatar
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(Main3Activity.this, "Vous n'avez pas la permission d'accèder à vos photos", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null)
        {
            Uri uri = data.getData();
            try
            {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                avatar.setImageBitmap(bitmap);

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
