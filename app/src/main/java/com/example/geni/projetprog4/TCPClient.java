package com.example.geni.projetprog4;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient
{
    private String ipLocal, ipDistant, incomingMessage;
    private MessageCallback listener;
    private BufferedReader in;
    private PrintWriter out;
    private Socket socket;

    public TCPClient(String ipLocal, String ipDistant, MessageCallback listener)
    {
        this.listener = listener;
        this.ipLocal = ipLocal;
        this.ipDistant = ipDistant;
    }

    public void sendMessage(String message)
    {
        if (out != null && !out.checkError())
        {
            out.println(message);
            out.flush();
        }
    }

    public void run()
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

            while (true)
            {
                incomingMessage = in.readLine();
                if (incomingMessage != null && listener != null)
                {
                    /**
                     * Incoming message is passed to MessageCallback object.
                     * Next it is retrieved by AsyncTask and passed to onPublishProgress method.
                     *
                     */
                    listener.callbackMessageReceiver(incomingMessage);
                }
                incomingMessage = null;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Callback Interface for sending received messages to 'onPublishProgress' method in AsyncTask.
     *
     */
    public interface MessageCallback
    {
        /**
         * Method overriden in AsyncTask 'doInBackground' method while creating the TCPClient object.
         * @param message Received message from server app.
         */
        void callbackMessageReceiver(String message);
    }
}
