package com.example.geni.projetprog4;

public class Recettes {
    //la classe pour les recettes

    //Proprietes
    private String IdRecette, nomRecette, dureePreparation, dureeCuisson, tempsAttente, photoRecette;

    //Constructeur par defaut
    public Recettes(){}

    public Recettes(String nomRecette, String photoRecette){
        this.nomRecette=nomRecette;
        this.photoRecette = photoRecette;
    }

    //Constructeur par parametre
    public Recettes(String idRecette, String nomRecette, String dureePreparation, String dureeCuisson, String tempsAttente, String photoRecette) {
        IdRecette = idRecette;
        this.nomRecette = nomRecette;
        this.dureePreparation = dureePreparation;
        this.dureeCuisson = dureeCuisson;
        this.tempsAttente = tempsAttente;
        this.photoRecette = photoRecette;
    }

    //Methode d'access

    public String getIdRecette() {
        return IdRecette;
    }

    public void setIdRecette(String idRecette) {
        IdRecette = idRecette;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public void setNomRecette(String nomRecette) {
        this.nomRecette = nomRecette;
    }

    public String getDureePreparation() {
        return dureePreparation;
    }

    public void setDureePreparation(String dureePreparation) {
        this.dureePreparation = dureePreparation;
    }

    public String getDureeCuisson() {
        return dureeCuisson;
    }

    public void setDureeCuisson(String dureeCuisson) {
        this.dureeCuisson = dureeCuisson;
    }

    public String getTempsAttente() {
        return tempsAttente;
    }

    public void setTempsAttente(String tempsAttente) {
        this.tempsAttente = tempsAttente;
    }

    public String getPhotoRecette() {
        return photoRecette;
    }

    public void setPhotoRecette(String photoRecette) {
        this.photoRecette = photoRecette;
    }


    //Methode toString
}
