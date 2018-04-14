package com.example.geni.projetprog4;

import android.database.sqlite.SQLiteDatabase;

//LA BASE DE DONNÉES POUR LES UTILISATEURS DE L'APPLICATION
public class BD {

    //PROPRIÉTÉS
    SQLiteDatabase sql;

    //CONSTRUCTEUR
    public BD (SQLiteDatabase sql)
    {
        this.sql = sql;
    }

    /*MÉTHODES UTILITAIRES
    Créer la table si elle n'existe pas*/
    public void createTable()
    {
        try
        {
            //Table des utilisateurs
            sql.execSQL("CREATE TABLE IF NOT EXISTS Utilisateurs(IdUtilisateur VARCHAR(50) PRIMARY KEY NOT NULL, MotDePasse VARCHAR(50) NOT NULL, Pays VARCHAR(50), Courriel VARCHAR(100) NOT NULL, Pointage INTEGER)");
            //Table des recettes
            sql.execSQL("CREATE TABLE IF NOT EXISTS Recettes(NumeroRecette INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NomRecette VARCHAR(50), PaysRecette VARCHAR(50), NiveauRecette INTEGER, DureePrep INTEGER, DureeCuisson INTEGER, Ingredients VARCHAR(200), TypePlat VARCHAR(50), Calories INTEGER, Preparation VARCHAR(150))");
            //Table des commentaires
            sql.execSQL("CREATE TABLE IF NOT EXISTS Commentaire(NumeroCommentaire INTEGER PRIMARY KEY NOT NULL, Note INTEGER NOT NULL, FOREIGN KEY(IdUtilisateur VARCHAR(50))) REFERENCES Utilisateurs(IdUtilisateur), FOREIGN KEY(NumeroRecette INTEGER) REFERENCES Recettes(NumeroRecette) ");
            //Table UtilisateurRecette
            sql.execSQL("CREATE TABLE IF NOT EXISTS UtilisateurRecette()");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    //Insérer des utilisateurs dans la base de données de l'application
    public void insererUtilisateurs(String Identifiant, String MotDePasse, String Pays, String Courriel)
    {
        try
        {
            sql.execSQL(String.format("INSERT INTO Utilisateurs(Identifiant, MotDePasse, Pays, Courriel) VALUES ('%s', '%s', '%s', '%s', )", Identifiant, MotDePasse, Pays, Courriel));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    //Insérer des recettes dans la base de données
    public void insertRecette(String nomRecette, String paysRecette, Integer NiveauRecette, Integer dureePrep, Integer dureeCuisson, String Ingredients, String typePlat, Integer Calories, String Preparations){
        try{
            sql.execSQL(String.format("INSERT INTO Recette(NomRecette, PaysRecette, NiveauRecette, DureePrep, DureeCuisson, Ingredients, TypePlat, Calories, Preparation) VALUES ('%s', '%s', '%s', '%s','%s','%s','%s','%s','%s' )",nomRecette ,paysRecette , NiveauRecette, dureePrep,dureeCuisson, Ingredients,typePlat,Calories,Preparations));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Insert des commentaires dans la table des commentaires
    public void insertCommentaire()
    {
        try{

        }
        catch (Exception e){

        }
    }
}
