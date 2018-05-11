package com.example.geni.projetprog4;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Page a l'ouverture de l'application, page de login
    //XML: activity_main

    //PROPRIÉTÉS
    private Button btnConnexion;
    private TextView txtInscription;
    private EditText txtIdentifiant, txtMotDePasse;
    private CheckBox checkSouvenir;
    private SharedPreferences pref;
    private static final String NOM_PREF = "sharedPrefs";
    private ThreadClient client;
    private static int PORT_LOCAL = 3010;
    private static int PORT_DISTANT = 3011;
    private static String IPLocal;
    //Adresse ip du serveur
    private static String IP_DISTANT = "192.168.0.161";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cooking jar");
        createNotificationChannel();

        //INITIALISATION DES PROPRIÉTÉS/OBJETS
        btnConnexion = (Button) findViewById(R.id.btnConnecter);
        txtInscription = (TextView) findViewById(R.id.txtInscription);
        txtIdentifiant = (EditText) findViewById(R.id.txtIdentifiantConnexion);
        txtMotDePasse = (EditText) findViewById(R.id.txtMotDePasseConnexion);
        checkSouvenir = (CheckBox) findViewById(R.id.checkBoxMemoriser);
        chargement();

        //Associer la variable avec ip address du device
        IPLocal = ipDevice();
        //connexion au serveur
        client = new ThreadClient(PORT_LOCAL, IPLocal,PORT_DISTANT,IP_DISTANT, this);
        client.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        //LE BOUTON POUR SE CONNECTER
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
                new ThreadClient.ThreadEnvoi(String.format("connect::username=%s;password=%s", txtIdentifiant.getText().toString(), txtMotDePasse.getText().toString())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });

        //LE BOUTON POUR S'INSCRIRE
        txtInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intentInscription = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(intentInscription);
            }
        });
    }

    //Methode privee qui prends le ip de l'appareil
    //SOURCE: https://www.viralandroid.com/2016/01/how-to-get-ip-address-of-android-device-programmatically.html
    private String ipDevice()
    {
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Log.i("main","adresse IP: " + ip);
        return ip;
    }

    //LA MÉTHODE POUR SE CONNECTER
    public void savePreferences()
    {
        pref = getSharedPreferences(NOM_PREF, MODE_PRIVATE);
        pref.edit().putString("Identifiant",(txtIdentifiant.getText().toString().toLowerCase()))
                .putString("MotDePasse",(txtMotDePasse.getText().toString()))
                .putBoolean("Memoriser",(checkSouvenir.isChecked())).commit();
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

    // Créer le channel pour les notifications
    // https://developer.android.com/training/notify-user/build-notification
    private void createNotificationChannel()
    {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "cookieJarNotif";
            String description = "cookieJarNotif";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(Utils.CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
