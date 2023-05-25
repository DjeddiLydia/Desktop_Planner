package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Planification.Journée;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catégorie implements Comparable <Catégorie> , Serializable {
    private String name ;
    private String couleur ;
    private ArrayList<Tache> taches  = new ArrayList<>() ;


    public Catégorie(String n , Color c , ArrayList<Tache> t ){
        name = n ;
        couleur = c.toString() ;
        taches = t ;
    }

    public Catégorie(String n , Color c ){
        name = n ;
        couleur = c.toString() ;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return Color.valueOf(this.couleur) ;
    }
    public String getCouleur() {
        return this.couleur ;
    }

    public ArrayList<Tache> getTasks() {
        return taches;
    }

    public void addTask(Tache tache) {
        taches.add(tache);
    }


    @Override
    public int compareTo(Catégorie o) {

        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o ){
        if (this  ==o ) return true ;
        if (o== null || o.getClass()!= getClass()) return false ;

        return this.name == ((Catégorie) o).getName() ;
    }
}
