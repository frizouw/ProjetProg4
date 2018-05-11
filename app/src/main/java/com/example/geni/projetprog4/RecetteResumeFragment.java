package com.example.geni.projetprog4;

import android.os.AsyncTask;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recette_resume_fragment_layout, container, false);
        if(getArguments() != null && getArguments().containsKey("recette"))
        {
            recette = Utils.MES_RECETTES.get(getArguments().getInt("recette"));

            ((TextView)view.findViewById(R.id.txtNomRecette)).setText(recette.getNom());
            ((ImageView)view.findViewById(R.id.imgRecette)).setImageBitmap(recette.getImage());
            ((TextView)view.findViewById(R.id.txtCalories)).setText(String.valueOf(recette.getCalories()));
            ((TextView)view.findViewById(R.id.txtCuisson)).setText(recette.getDureeCuisson());
            ((TextView)view.findViewById(R.id.txtPrep)).setText(recette.getDureePrep());
            ((TextView)view.findViewById(R.id.txtPlat)).setText(recette.getType());
            ((TextView)view.findViewById(R.id.txtNiveau)).setText(String.valueOf(recette.getNiveau()));
        }

        view.findViewById(R.id.btnFavorite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ThreadClient.ThreadEnvoi(String.format("addRecetteUser::username=%s;recette=%s", Utils.CURRENT_USER.getUsername(), recette.getNom())).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
        return view;
    }
}
