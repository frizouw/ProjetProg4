package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;

public class Calendrier extends Fragment{

    CalendarView calendrier;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //associer le layout du fragment a la classe
        v = inflater.inflate(R.layout.calendrier, container,false);
        calendrier=v.findViewById(R.id.calendarView);
        return  v;
    }
}
