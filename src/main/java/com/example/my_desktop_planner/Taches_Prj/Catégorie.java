package com.example.my_desktop_planner.Taches_Prj;

import javafx.scene.paint.Color;

import java.util.List;

public class Catégorie {
    private String name ;
    private Color couleur ;
    private List<TacheSimple> taches ;

    public String getName() {
        return name;
    }

    public Color getColor() {
        return couleur;
    }

    public List<TacheSimple> getTasks() {
        return taches;
    }

    public void addTask(TacheSimple tache) {
        taches.add(tache);
    }



}
