package com.example.geni.projetprog4;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
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
import android.widget.CalendarView;

import java.util.Calendar;

public class Calendrier extends Fragment{

    CalendarView calendrier;
    View v;
    private static int REQUESTCODE =0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout du fragment a la classe
        v = inflater.inflate(R.layout.calendrier, container,false);
        calendrier=v.findViewById(R.id.calendarView);

        https://stackoverflow.com/questions/10339808/how-can-i-extract-date-from-calendarview-and-display-selected-date
        calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
             String dateChoisie = dayOfMonth +" / " + (month+1) + " / " + year;
            //appelle de la methode pour la notification
            Notification();

            //envoie une demande pour prendre les recettes selon le username et la date selectionnee
            String username = MainActivity.username;
            new ThreadClient.ThreadEnvoi(String.format("askCalendrier::username=%s;dateChoisie=%s",username,dateChoisie)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            //recevoir les recettes selon la date selectionnee

            }
        });

        return  v;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void Notification(){
        Intent i = new Intent(getContext(), Main2Activity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), REQUESTCODE,i,PendingIntent.FLAG_UPDATE_CURRENT);
        //creer la notification
        Notification.Builder builder = new Notification.Builder(getContext());
        NotificationCompat.Builder builderNotif = new NotificationCompat.Builder(getContext());
        builderNotif.setContentTitle(getString(R.string.noticationtitle))
                .setContentText(getString(R.string.notificationtexte))
                .setSmallIcon(R.mipmap.ic_launcher);
        //construire la notification
        Notification notif = builderNotif.build();
        //creer objet notifManag
        NotificationManager notifManag = ( NotificationManager ) getActivity().getSystemService( getActivity().NOTIFICATION_SERVICE );
        //lancer l'application
        notifManag.notify(0,notif);
    }
}
