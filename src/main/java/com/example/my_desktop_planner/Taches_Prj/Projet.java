package com.example.my_desktop_planner.Taches_Prj;

import java.util.ArrayList;

import java.io.Serializable;

public class Projet implements Serializable {

    private String nom ;
    private String description ;
    private ArrayList<Tache > taches = new ArrayList<>();

}
