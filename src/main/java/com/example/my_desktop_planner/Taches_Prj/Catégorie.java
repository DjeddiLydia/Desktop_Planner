package com.example.my_desktop_planner.Taches_Prj;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Catégorie {
    private String name ;
    private Color couleur ;
    private ArrayList<Tache> taches  = new ArrayList<>() ;


    public Catégorie(String n , Color c , ArrayList<Tache> t ){
        name = n ;
        couleur = c ;
        taches = t ;
    }

    public Catégorie(String n , Color c ){
        name = n ;
        couleur = c ;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return couleur;
    }

    public ArrayList<Tache> getTasks() {
        return taches;
    }

    public void addTask(Tache tache) {
        taches.add(tache);
    }





}
