package com.example.geni.projetprog4;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


//Classe pour la liste d'amis
public class ListeAmies extends Fragment
{
    //PROPRIÉTÉS
    private View v;
    private FloatingActionButton btnAjouterAmis;
    private EditText entrer;
    private ListView listeAmies;
    private ArrayAdapter<String> adapter;
    private Main2Activity parent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout du fragment a la classe
        v = inflater.inflate(R.layout.liste_amies, container,false);

        //ASSOCIER LES PROPRIÉTÉS AUX CONTRÔLES
        btnAjouterAmis = (FloatingActionButton)v.findViewById(R.id.btnAjouterAmi);
        listeAmies = (ListView) v.findViewById(R.id.listAmies);

        //LORSQU'ON APPUIS SUR LE "+" POUR AJOUTER UN AMI
        btnAjouterAmis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                entrer = new EditText(getActivity());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Ajouter un(e) ami(e)");
                builder.setMessage("Veuillez tapper l'identifiant de votre ami(e):")
                    .setView(entrer)
                    .setCancelable(false)
                    .setPositiveButton("Ajouter", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            new ThreadClient.ThreadEnvoi(String.format("addAmies::userID=%s;friendID=%s",Utils.CURRENT_USER.getUsername(),entrer.getText())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            //QUAND ON APPUIE SUR CANCEL
                            dialog.cancel();
                        }
                    });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        ArrayList<String> amis = new ArrayList<>();

        for(Users user : Utils.AMIS)
            amis.add(String.format("%s | %s points", user.getUsername(), user.getPoints()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.liste_custom, amis);
        listeAmies.setAdapter(adapter);

        return  v;
    }
}
