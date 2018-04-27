package com.example.geni.projetprog4;

public class Recettes {
    //la classe pour les recettes

    //Proprietes
    private String IdRecette, nomRecette, dureePreparation, dureeCuisson, tempsAttente, photoRecette;

    //Constructeur par defaut
    public Recettes(){}

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

    //Methode toString
}
