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
            sql.execSQL("CREATE TABLE IF NOT EXISTS Utilisateurs(Identifiant VARCHAR(50) PRIMARY KEY NOT NULL, MotDePasse VARCHAR(50) NOT NULL, Pays VARCHAR(50), Courriel VARCHAR(50) NOT NULL, Pointage INTEGER)");
            //Table des recettes
            sql.execSQL("CREATE TABLE IF NOT EXISTS Recettes(NumeroRecette INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, NomRecette VARCHAR(50), PaysRecette VARCHAR(50), NiveauRecette INTEGER)");
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
}
