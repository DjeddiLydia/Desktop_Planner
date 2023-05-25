package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Planification.Planning;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Tache implements Serializable {
    private String nom ;
    private Duration durée ;
    private LocalDate dateLimite  ;
    private LocalTime heurelimite ;
    private Priorité priorité ;
    private String couleur ;

    private String categorie ;

    private EtatRealisation etat ;


    public Tache(String n , LocalDate d , LocalTime t , Priorité p){
        nom = n ;
        dateLimite = d ;
        heurelimite = t ;
        priorité = p ;
    }

    public Tache(String n ,Duration dr ,  LocalDate d , LocalTime t , Priorité p , EtatRealisation e){
        nom = n ;
        dateLimite = d ;
        heurelimite = t ;
        priorité = p ;
        durée = dr ;
        etat = e ;
    }


    public Tache(String n ,Duration dr ,  LocalDate d , LocalTime t , Priorité p ){
        nom = n ;
        dateLimite = d ;
        heurelimite = t ;
        priorité = p ;
        durée = dr ;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public EtatRealisation getEtat() {
        return etat;
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

    public void setEtat(EtatRealisation e){
        etat=e ;
    }



    public static EtatRealisation StringToEtat(String str){

        if (str == "NotRealized"){
            return EtatRealisation.NotRealized ;
        }
        if (str == "Completed"){
            return EtatRealisation.Completed ;
        }
        if (str == "InProgress"){
            return EtatRealisation.InProgress ;
        }
        if (str == "Cancelled"){
            return EtatRealisation.Cancelled ;
        }
        if (str == "Delayed"){
            return EtatRealisation.Delayed ;
        }
        return null ;
    }


    public static  Priorité StringToPropriete(String str){
        if (str == "High"){
            return Priorité.High ;
        }
        else if (str == "Medium"){
            return Priorité.Medium ;
        } else if (str == "Low") {
            return Priorité.Low ;
        }
        else return null ;
    }

    public String PrioriteToString(){
        if (priorité.equals(Priorité.High)){
            return  "High" ;
        }
        if (priorité.equals(Priorité.Medium)){
            return  "Medium" ;
        }
        if (priorité.equals(Priorité.Low)){
            return  "Low" ;
        }
        return null ;
    }


    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}
