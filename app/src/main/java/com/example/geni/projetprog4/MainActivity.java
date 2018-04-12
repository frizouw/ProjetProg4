package com.example.geni.projetprog4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //PROPRIÉTÉS
    private Button btnConnexion;
    private TextView txtInscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //LE BOUTON POUR SE CONNECTER
        btnConnexion = (Button) findViewById(R.id.btnConnecter);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentConnexion = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intentConnexion);
            }

        });

        //LE BOUTON POUR S'INSCRIRE
        txtInscription = (TextView) findViewById(R.id.txtInscription);
        txtInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentInscription = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intentInscription);
            }
        });
    }
}
