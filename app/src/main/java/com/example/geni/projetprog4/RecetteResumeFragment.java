package com.example.geni.projetprog4;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecetteResumeFragment extends Fragment {

    //SOURCE : https://youtu.be/7zaKUc2zfpI?t=1136
    private View view;
    public RecetteResumeFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recette_resume_fragment_layout, container, false);
        return view;
    }
}
