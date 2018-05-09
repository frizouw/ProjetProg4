package com.example.geni.projetprog4;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import java.util.ArrayList;

public class ListeAmies extends Fragment {

    private View v;
    private FloatingActionButton btnAjouterAmis;
    private EditText entrer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout du fragment a la classe
        v = inflater.inflate(R.layout.liste_amies, container,false);


        btnAjouterAmis = (FloatingActionButton)v.findViewById(R.id.btnAjouterAmi);
        btnAjouterAmis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)

            {   entrer = new EditText(getActivity());
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
                            //QUAND ON APPUIS SUR AJOUTER

                           /* for (Utilisateurs utilisateurs : Utils.LIST_USERS)
                            {
                                if (utilisateurs.getUsername.equals(entrer))
                                {

                                }
                                else
                            }*/
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

        return  v;
    }
}
