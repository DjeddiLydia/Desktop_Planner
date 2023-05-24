package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Planification.Planning;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Tache  {
    private String nom ;
    private Duration durée ;
    private LocalDate dateLimite  ;
    private LocalTime heurelimite ;
    private Priorité priorité ;

    public Tache(String n , LocalDate d , LocalTime t , Priorité p){
        nom = n ;
        dateLimite = d ;
        heurelimite = t ;
        priorité = p ;
    }

    public Tache(String n ,Duration dr ,  LocalDate d , LocalTime t , Priorité p  ){
        nom = n ;
        dateLimite = d ;
        heurelimite = t ;
        priorité = p ;
        durée = dr ;
    }

    public Priorité getPriorité() {
        return priorité;
    }

    public LocalDate getDateLimite() {
        return dateLimite;
    }

    public LocalTime getHeurelimite() {
        return heurelimite;
    }

    public Duration getDurée() {
        return durée;
    }

    public abstract boolean planifier(Planning plan);
    public void setDurée(Duration durée) {
        this.durée = durée;
    }


    public void setDateLimite(LocalDate of) {
        dateLimite = of ;
    }

    public void setHeurelimite(LocalTime of) {
        heurelimite= of ;
    }

    public void setPriorité(Priorité m) {
        priorité = m ;
    }

    public void setNom(String s) {
        nom = s ;
    }

    public String getNom() {
        return nom ;
    }
}
