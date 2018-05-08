package com.example.geni.projetprog4;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class Recettes
{
    private String nom, pays, dureePrep, dureeCuisson, tempsAttente, ingredients, type, preparation, date, urlImage;
    private int niveau, calories;
    private Bitmap image;

    public Recettes()
    {

    }

    public Recettes(String nom, String pays, String dureePrep, String dureeCuisson, String tempsAttente, String ingredients, String type, String preparation, String date, String urlImage, int niveau, int calories)
    {
        this.nom = nom;
        this.pays = pays;
        this.dureePrep = dureePrep;
        this.dureeCuisson = dureeCuisson;
        this.tempsAttente = tempsAttente;
        this.ingredients = ingredients;
        this.type = type;
        this.preparation = preparation;
        this.date = date;
        this.urlImage = urlImage;
        this.niveau = niveau;
        this.calories = calories;
        new DownloadImage.DownloadImageBitmap(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urlImage);
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPays()
    {
        return pays;
    }

    public void setPays(String pays)
    {
        this.pays = pays;
    }

    public String getDureePrep()
    {
        return dureePrep;
    }

    public void setDureePrep(String dureePrep)
    {
        this.dureePrep = dureePrep;
    }

    public String getDureeCuisson()
    {
        return dureeCuisson;
    }

    public void setDureeCuisson(String dureeCuisson)
    {
        this.dureeCuisson = dureeCuisson;
    }

    public String getTempsAttente()
    {
        return tempsAttente;
    }

    public void setTempsAttente(String tempsAttente)
    {
        this.tempsAttente = tempsAttente;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getPreparation()
    {
        return preparation;
    }

    public void setPreparation(String preparation)
    {
        this.preparation = preparation;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public int getNiveau()
    {
        return niveau;
    }

    public void setNiveau(int niveau)
    {
        this.niveau = niveau;
    }

    public int getCalories()
    {
        return calories;
    }

    public void setCalories(int calories)
    {
        this.calories = calories;
    }

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
