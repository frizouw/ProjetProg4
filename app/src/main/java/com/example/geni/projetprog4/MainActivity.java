package com.example.geni.projetprog4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //PROPRIÉTÉS
    private Button btnConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnexion = (Button) findViewById(R.id.btnConnecter);
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentConnexion = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intentConnexion);
            }
        });

    }
}
