package com.example.geni.projetprog4;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ThreadClient extends AsyncTask<String,String,TCPClient> {
    //le cote thread client
    //demande de la connexion au server
    //SOURCE: https://developer.android.com/reference/android/os/AsyncTask.html

    //proprietes
    private int portLocal, portDistant;
    private String ipLocal, ipDistant;
    private static TCPClient client;


    //Constructeur
    public ThreadClient(int portLocal, String ipLocal, int portDistant, String ipDistant) {
        this.portLocal = portLocal;
        this.ipLocal = ipLocal;
        this.portDistant = portDistant;
        this.ipDistant = ipDistant;
    }

    //methode d'access
    public int getPortLocal() {
        return portLocal;
    }

    public void setPortLocal(int portLocal) {
        this.portLocal = portLocal;
    }

    public int getPortDistant() {
        return portDistant;
    }

    public void setPortDistant(int portDistant) {
        this.portDistant = portDistant;
    }

    public String getIpLocal() {
        return ipLocal;
    }

    public void setIpLocal(String ipLocal) {
        this.ipLocal = ipLocal;
    }

    public String getIpDistant() {
        return ipDistant;
    }

    public void setIpDistant(String ipDistant) {
        this.ipDistant = ipDistant;
    }



    //thread dans le background pour la connexion
    @Override
    protected TCPClient doInBackground(String... params) {
        try
        {
            client = new TCPClient(ipLocal, ipDistant, new TCPClient.MessageCallback() {
                @Override
                public void callbackMessageReceiver(String message) {
                    publishProgress(message);
                }
            });
            client.run();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        String[] splits = values[0].split(":");
        switch(splits[0])
        {
            case "connect" :
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
                client.sendMessage(data);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    //https://github.com/codepath/android_guides/wiki/Sending-and-Receiving-Data-with-Sockets
}
