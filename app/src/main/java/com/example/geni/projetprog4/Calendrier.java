package com.example.geni.projetprog4;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;

import static android.content.Intent.getIntent;

public class Calendrier extends Fragment{

    //Variables
    private CalendarView calendrier;
    private View v;
    private String date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout du fragment a la classe
        v = inflater.inflate(R.layout.calendrier, container,false);
        calendrier=v.findViewById(R.id.calendarView);

        //https://stackoverflow.com/questions/10339808/how-can-i-extract-date-from-calendarview-and-display-selected-date
        calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            //prendre la date selectionnee dans la calendarView
             String dateChoisie = dayOfMonth +" / " + (month+1) + " / " + year;

            //envoie une demande pour prendre les recettes selon le username et la date selectionnee
            new ThreadClient.ThreadEnvoi(String.format("askCalendrier::username=%s;dateChoisie=%s",Utils.CURRENT_USER.getUsername(),dateChoisie)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //recevoir les recettes selon la date selectionnee
            //la cle du Intent
                if(getArguments() != null && getArguments().containsKey("askCalendrier"))
                {
                    date=getArguments().getString("askCalendrier");
                    //((ListView)view.findViewById(R.id.listCalendrier)).setAdapter(date.toString());
                    Log.i("Calendrier", "values du serveur: " + date);
                }
            }
        });

        return  v;
    }
}
