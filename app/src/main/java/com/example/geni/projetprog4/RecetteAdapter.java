package com.example.geni.projetprog4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

// Anthony Whelan, Genevieve Rollin, Claire Bun
public class RecetteAdapter extends RecyclerView.Adapter<RecetteAdapter.MyViewHolder> {

    //Proprietes
    private Context mContext ;
    private List<Recettes> mData ;

    //constructeur par parametres
    public RecetteAdapter(Context mContext, List<Recettes> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.favorite_item, parent,false);
        return new MyViewHolder(view);
    }

    //Quand on appuie sur le cardview pour en savoir davantage sur les recettes
    @Override
    public void onBindViewHolder(@NonNull RecetteAdapter.MyViewHolder holder, final int position) {
        holder.txt_nom_recette.setText(mData.get(position).getNom());
        holder.recette_img_id.setImageBitmap(mData.get(position).getImage());
        holder.cardView_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new  Intent(mContext, PageRecette.class);
                i.putExtra("recette", position);
                i.putExtra("mesrecettes", true);
                Log.i("pos", String.valueOf(position));
                mContext.startActivity(i);

                Log.i("RecetteAdapter","La recette a ete click");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    //classe affichage
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nom_recette;
        ImageView recette_img_id;
        CardView cardView_recette;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_nom_recette = (TextView) itemView.findViewById(R.id.nom_recette_id) ;
            recette_img_id = (ImageView) itemView.findViewById(R.id.recette_img_id);
            cardView_recette = (CardView)itemView.findViewById(R.id.cardview_grid);

        }
    }

}
