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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    //Activite page du login quand un nouvel utilisateur veut se connecter
    //XML: activity_main3.xml

    //PROPRIÉTÉS
    private ImageView avatar;                   //Représente l'ImageView de l'avatar
    private EditText idUtilisateur;             //Représente le edittext de l'identifiant de l'utilisateur
    private EditText motPasse;                  //Représente le editext du mot de passe de l'identifiant
    private EditText courriel;                  //Représente le courriel de l'utilisateur
    private Spinner pays;                       //Représente le spinner pour la selection du pays
    private Button btnChoisirImage;             //Représente le bouton qui aide l'utilisateur à choisir l'image
    private Button btnInscrire;                 //Représente le bouton pour s'inscrire
    private BD bd;                              //Représente la base de données
    final int REQUEST_CODE_GALLERY = 999;       //Représente le code pour accèder à la gallerie

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
        idUtilisateur = (EditText)findViewById(R.id.txtIdentifiantInscription);
        pays = (Spinner)findViewById(R.id.spinnerPaysInscription);
        motPasse = (EditText)findViewById(R.id.txtMotDePasseInscription);
        courriel = (EditText) findViewById(R.id.txtCourrielInscription);

        //Création de la BD
        bd = new BD(openOrCreateDatabase("CookingJarBD", MODE_PRIVATE, null));
        //Création des tables
        bd.createTable();

        //Remplir le spinner de pays
        List<String> spinnerPays = new ArrayList<String>();
        spinnerPays.add("Canada");
        spinnerPays.add("États-unis");
        spinnerPays.add("France");
        spinnerPays.add("Japon");
        spinnerPays.add("Chine");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerPays);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinnerPaysItems = (Spinner) findViewById(R.id.spinnerPaysInscription);
        spinnerPaysItems.setAdapter(adapter);

        /*ÉVÉNEMENTS
        SOURCE : https://www.youtube.com/watch?v=4bU9cZsJRLI&t=548s
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
                //Vérifie avant si l'utilisateur existe déja
                if (bd.utilisateurExiste(idUtilisateur.getText().toString()) == true)
                {
                    Toast.makeText(Main3Activity.this, "Ce compte existe déja", Toast.LENGTH_SHORT).show();
                }
                else if (bd.utilisateurExiste(idUtilisateur.getText().toString()) == false)
                {
                    //insert dans la table des utilisateurs
                    bd.insererUtilisateurs(idUtilisateur.getText().toString(), motPasse.getText().toString(), pays.getSelectedItem().toString(), courriel.getText().toString(), imageViewToByte(avatar));
                    Toast.makeText(Main3Activity.this, "Inscription réussi!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Main3Activity.this, "Il manque quelque chose!", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(Main3Activity.this, "Vous n'avez pas la permission d'accèder à vos albums photos", Toast.LENGTH_SHORT).show();
            }

            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //Méthode pour insérer l'image
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

    //Méthode pour convertir l'image en byte afin de l'insérer dans la base de donnée
    private byte[] imageViewToByte (ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] tableauBytes = stream.toByteArray();
        return tableauBytes;
    }
}
