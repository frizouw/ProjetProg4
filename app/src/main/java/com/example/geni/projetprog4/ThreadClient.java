package com.example.geni.projetprog4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;


public class ThreadClient extends AsyncTask<String,String,Void>
{
    //le cote thread client
    //demande de la connexion au server
    //SOURCE: https://developer.android.com/reference/android/os/AsyncTask.html

    //proprietes
    private int portLocal, portDistant;
    private String ipLocal, ipDistant;
    private Context act;
    private BufferedReader in;
    private static PrintWriter out;
    private Socket socket;


    //Constructeur
    public ThreadClient(int portLocal, String ipLocal, int portDistant, String ipDistant, Context act)
    {
        this.portLocal = portLocal;
        this.ipLocal = ipLocal;
        this.portDistant = portDistant;
        this.ipDistant = ipDistant;
        this.act = act.getApplicationContext();
    }

    //thread dans le background pour la connexion
    @Override
    protected Void doInBackground(String... params)
    {
        try
        {
            socket = new Socket();
            //reutilise le port
            socket.setSoLinger(true, 0);
            //utiliser le port
            socket.setReuseAddress(true);
            //lier le socket
            socket.bind(new InetSocketAddress(this.ipLocal,3010));
            //la connexion au serveur
            socket.connect(new InetSocketAddress(this.ipDistant, 3011));
            Log.i("main", "Vous êtes connectés au serveur.");

            // Create PrintWriter object for sending messages to server.
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            //Create BufferedReader object for receiving messages from server.
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;
            while (true)
            {
                msg = in.readLine();
                if (msg != null)
                {
                    publishProgress(msg);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        String[] splits = values[0].split("::");
        switch(splits[0])
        {
            case "connect" :
                if(splits[1].equals("true"))
                {
                    new ThreadEnvoi("allRecettes").executeOnExecutor(THREAD_POOL_EXECUTOR);
                    //si la connexion est approuvee, commencer la nouvelle activite
                    //partir l'activite pour faire la demande
                    Intent i = new Intent(act, Main2Activity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("data", splits[2]);
                    act.startActivity(i);
                }
                else
                {
                    Toast.makeText(act, "Le compte n'existe pas", Toast.LENGTH_SHORT).show();
                }
                break;
            case "inscription" :
                if(splits[1].equals("true"))
                {
                    Toast.makeText(act, "Inscription réussi!", Toast.LENGTH_SHORT).show();
                    Intent intentRetourMenu = new Intent(act, MainActivity.class);
                    act.startActivity(intentRetourMenu);
                }
                else if(splits[1].equals("false"))
                {
                    Toast.makeText(act, "Le compte existe déjà", Toast.LENGTH_SHORT).show();
                }
                break;
            case "allRecettes":
                ArrayList<Recettes> server = new GsonBuilder().create().fromJson(splits[1], new TypeToken<ArrayList<Recettes>>(){}.getType());
                ArrayList<Recettes> temps = new ArrayList<>();

                for(Recettes r : server)
                    temps.add(new Recettes(r.getNom(), r.getPays(), r.getDureePrep(), r.getDureeCuisson(), r.getTempsAttente(), r.getIngredients(), r.getType(), r.getPreparation(), r.getDate(), r.getUrlImage(), r.getNiveau(), r.getCalories()));

                Utils.LIST_RECETTES = temps;
                break;
        }
    }

    public static class ThreadEnvoi extends AsyncTask<Void, Void, Void>
    {

        public String data;

        public ThreadEnvoi(String data)
        {
            this.data = data;
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            envoyer(data);
            Log.i("ThreadClient", "data " + data);
            return null;
        }

        //methode qui envoit les messages au serveur
        public void envoyer(String data)
        {
            try
            {
                if (out != null)
                {
                    out.println(data);
                    out.flush();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    //https://github.com/codepath/android_guides/wiki/Sending-and-Receiving-Data-with-Sockets
}
