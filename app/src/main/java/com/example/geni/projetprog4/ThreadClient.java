package com.example.geni.projetprog4;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;


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
            //la connexion
            case "connect" :
                if(splits[1].equals("true"))
                {
                    new ThreadEnvoi("allRecettes").executeOnExecutor(THREAD_POOL_EXECUTOR);
                    Utils.AMIS = new GsonBuilder().create().fromJson(splits[3], new TypeToken<ArrayList<Users>>(){}.getType());
                    ArrayList<String> epiceries = new GsonBuilder().create().fromJson(splits[4], new TypeToken<ArrayList<String>>(){}.getType());
                    if(epiceries != null && epiceries.size() > 0)
                    {
                        ArrayList<ItemEpicerie> tempItems = new ArrayList<>();
                        for(String s : epiceries)
                        {
                            tempItems.add(new ItemEpicerie(s, false));
                        }
                        Utils.LISTE_EPICERIE = tempItems;
                    }

                    Log.i("test", splits[5]);
                    if(!splits[5].equals("[]"))
                    {
                        ArrayList<Recettes> serverMesRecettes = new GsonBuilder().create().fromJson(splits[5], new TypeToken<ArrayList<Recettes>>(){}.getType());
                        ArrayList<Recettes> tempsMesRecettes = new ArrayList<>();

                        for(Recettes r : serverMesRecettes)
                            tempsMesRecettes.add(new Recettes(r.getNom(), r.getPays(), r.getDureePrep(), r.getDureeCuisson(), r.getTempsAttente(), r.getIngredients().trim().replaceAll("/(\\r\\n)+|\\r+|\\n+|\\t+/i", ""), r.getType(), r.getPreparation(), r.getDate(), r.getUrlImage(), r.getNiveau(), r.getCalories()));

                        Utils.MES_RECETTES = tempsMesRecettes;
                    }
                    else
                    {
                        Utils.MES_RECETTES.clear();
                    }

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
                //insert une inscription
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
                //prendre toutes les recettes
            case "allRecettes":
                ArrayList<Recettes> server = new GsonBuilder().create().fromJson(splits[1], new TypeToken<ArrayList<Recettes>>(){}.getType());
                ArrayList<Recettes> temps = new ArrayList<>();

                for(Recettes r : server)
                    temps.add(new Recettes(r.getNom(), r.getPays(), r.getDureePrep(), r.getDureeCuisson(), r.getTempsAttente(), r.getIngredients().trim().replaceAll("/(\\r\\n)+|\\r+|\\n+|\\t+/i", ""), r.getType(), r.getPreparation(), r.getDate(), r.getUrlImage(), r.getNiveau(), r.getCalories()));

                Utils.LIST_RECETTES = temps;
                break;
                //ajout de la date au calendrier
            case "askCalendrier":
                String received = splits[1];
                ((Main2Activity)getCurrentActivity()).updateUICalendrier(received);
                break;
                //ajout d'amis
            case "addAmies":
                if(splits[1].equals("true"))
                {
                    Utils.AMIS = new GsonBuilder().create().fromJson(splits[2], new TypeToken<ArrayList<Users>>(){}.getType());
                    ((Main2Activity)getCurrentActivity()).updateUIAmies();
                }
                else
                {
                    Toast.makeText(act, "Cette personne n'existe pas!", Toast.LENGTH_SHORT).show();
                }
                break;
                //ajout ingredient
            case "addIngredient" :
                if(splits[1].equals("true"))
                {
                    ArrayList<String> epiceries = new GsonBuilder().create().fromJson(splits[2], new TypeToken<ArrayList<String>>(){}.getType());
                    if(epiceries != null && epiceries.size() > 0)
                    {
                        ArrayList<ItemEpicerie> tempItems = new ArrayList<>();
                        for(String s : epiceries)
                        {
                            tempItems.add(new ItemEpicerie(s, false));
                        }
                        Utils.LISTE_EPICERIE = tempItems;
                    }
                }
                break;
                //supprimer de la liste
            case "removeIngredient":
                if(splits[1].equals("true"))
                {
                    ArrayList<String> epiceries = new GsonBuilder().create().fromJson(splits[2], new TypeToken<ArrayList<String>>(){}.getType());
                    if(epiceries != null && epiceries.size() > 0)
                    {
                        ArrayList<ItemEpicerie> tempItems = new ArrayList<>();
                        for(String s : epiceries)
                        {
                            tempItems.add(new ItemEpicerie(s, false));
                        }
                        Utils.LISTE_EPICERIE = tempItems;
                    }
                    else
                    {
                        Utils.LISTE_EPICERIE.clear();
                    }
                    ((Main2Activity)getCurrentActivity()).updateUIEpicerie(Utils.LISTE_EPICERIE);
                }
                else
                {
                    Toast.makeText(act, "L'ingrédient ne peut pas être supprimer", Toast.LENGTH_SHORT).show();
                }
                break;
                //ajout de recette selon le user
            case "addRecetteUser":
                if(splits[1].equals("true"))
                {
                    Utils.MES_RECETTES.clear();
                    ArrayList<String> mes_recettes = new GsonBuilder().create().fromJson(splits[2], new TypeToken<ArrayList<String>>(){}.getType());
                    for(int i = 0; i < mes_recettes.size(); i++)
                    {
                        for(Recettes r : Utils.LIST_RECETTES)
                        {
                            if(r.getNom().equals(mes_recettes.get(i)))
                                Utils.MES_RECETTES.add(r);
                        }
                    }
                }
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
    // https://stackoverflow.com/questions/11411395/how-to-get-current-foreground-activity-context-in-android/28423385#28423385
    public static Activity getCurrentActivity()
    {
        try
        {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);

            Map<Object, Object> activities = (Map<Object, Object>) activitiesField.get(activityThread);
            if (activities == null)
                return null;

            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        }
        catch(Exception e)
        {
            Log.e("Error", e.getMessage());
        }

        return null;
    }

    //https://github.com/codepath/android_guides/wiki/Sending-and-Receiving-Data-with-Sockets
}
