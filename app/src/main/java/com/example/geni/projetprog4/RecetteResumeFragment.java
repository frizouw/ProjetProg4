package com.example.geni.projetprog4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecetteResumeFragment extends Fragment {

    //SOURCE : https://youtu.be/7zaKUc2zfpI?t=1136
    private View view;
    private Recettes recette;
    public RecetteResumeFragment()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recette_resume_fragment_layout, container, false);
        if(getArguments() != null && getArguments().containsKey("recette"))
        {
            recette = Utils.LIST_RECETTES.get(getArguments().getInt("recette"));

            ((TextView)view.findViewById(R.id.txtNomRecette)).setText(recette.getNom());
            ((ImageView)view.findViewById(R.id.imgRecette)).setImageBitmap(recette.getImage());
        }
        return view;
    }
}
