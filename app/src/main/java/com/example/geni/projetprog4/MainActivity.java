package com.example.geni.projetprog4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //PROPRIÉTÉS
    private Button btnConnexion;
    private TextView txtInscription;
    private EditText txtIdentifiant, txtMotDePasse;
    private CheckBox checkSouvenir;
    private SharedPreferences pref;
    private static final String NOM_PREF = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cooking jar");

        //INITIALISATION DES PROPRIÉTÉS/OBJETS
        btnConnexion = (Button) findViewById(R.id.btnConnecter);
        txtInscription = (TextView) findViewById(R.id.txtInscription);
        txtIdentifiant = (EditText) findViewById(R.id.txtIdentifiantConnexion);
        txtMotDePasse = (EditText) findViewById(R.id.txtMotDePasseConnexion);
        checkSouvenir = (CheckBox) findViewById(R.id.checkBoxMemoriser);
        chargement();

        //LE BOUTON POUR SE CONNECTER
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seConnecter();
            }

        });

        //LE BOUTON POUR S'INSCRIRE
        txtInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentInscription = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intentInscription);
            }
        });

    }

    //LA MÉTHODE POUR SE CONNECTER
    public void seConnecter()
    {
        Intent intentConnexion = new Intent(MainActivity.this, Main2Activity.class).putExtra("Identifiant", txtIdentifiant.getText().toString());
        pref = getSharedPreferences(NOM_PREF, MODE_PRIVATE);
        pref.edit().putString("Identifiant",(txtIdentifiant.getText().toString()))
                .putString("MotDePasse",(txtMotDePasse.getText().toString()))
                .putBoolean("Memoriser",(checkSouvenir.isChecked())).commit();
        startActivity(intentConnexion);
    }

    //LA MÉTHODE DU CHARGEMENT DE L'ACTIVITÉ POUR LE SHAREDPREFERENCE
    private void chargement()
    {
        pref = getSharedPreferences(NOM_PREF, MODE_PRIVATE);
        if (pref.contains("Memoriser") && pref.getBoolean("Memoriser", false))
        {
            if(pref.contains("Identifiant"))
                ((EditText)findViewById(R.id.txtIdentifiantConnexion)).setText(pref.getString("Identifiant", "Default"));
            if(pref.contains("MotDePasse"))
                ((EditText)findViewById(R.id.txtMotDePasseConnexion)).setText(String.valueOf(pref.getString("MotDePasse", "Default")));
            ((CheckBox)findViewById(R.id.checkBoxMemoriser)).setChecked(true);
        }
    }
}
