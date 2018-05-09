package com.example.geni.projetprog4;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class RecetteEtapeFragment extends Fragment {

    View view;
    private Recettes recette;
    private Button btnAjoutCalendrier;
    private Button btnSave, btnCancel;
    private CalendarView calendarView;
    private static  String selectedDate;

    public RecetteEtapeFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recette_etape_fragment_layout, container, false);
        btnAjoutCalendrier = (Button) view.findViewById(R.id.btnAjoutCalendrier);

        if(getArguments() != null && getArguments().containsKey("recette"))
        {
            recette = Utils.LIST_RECETTES.get(getArguments().getInt("recette"));
            ((TextView)view.findViewById(R.id.txtEtapesRecette)).setText(recette.getPreparation());
        }



        btnAjoutCalendrier.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                //ouvrir la boite de dialog pour ajouter la recette a la date
                //Methode qui ouvre la boite de dialog pour sauvegarder la recette a une date desiree par l'utilisateur
                final Dialog dialog = new Dialog(getContext()); // Context, this, etc.
                dialog.setContentView(R.layout.dialog_box_ajout_calendrier);
                btnSave = dialog.findViewById(R.id.btnSave);
                btnCancel = dialog.findViewById(R.id.btnCancel);
                calendarView = dialog.findViewById(R.id.calendar_dialog);
                dialog.show();

                //prendre la date selectionnee dans le dialog du calendarView
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        selectedDate = dayOfMonth +" / " + (month+1) + " / " + year;
                        Log.i("RecetteEtapeFragment", "Selected date: " + dayOfMonth + " " + month + " " + year);
                    }
                });

                //btnSave envoie les donnees au serveur
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Variables qui prennent les donnees
                        String username = MainActivity.username;
                        String nomRecette = recette.getNom();
                        String dateChoisie = selectedDate;
                        //thread qui envoie les donnees au serveur et qui les insert dans la BD
                        new ThreadClient.ThreadEnvoi(String.format("envoieCalendrier::username=%s;nomRecette=%s;dateChoisie=%s",username, nomRecette,dateChoisie)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        Log.i("RecetteEnregistrement", "values: " + username + " " + nomRecette + " " + dateChoisie);
                    }
                });

                //btnCancel
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
