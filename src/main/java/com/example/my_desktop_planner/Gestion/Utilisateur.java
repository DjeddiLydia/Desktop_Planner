package com.example.my_desktop_planner.Gestion;

import com.example.my_desktop_planner.Planification.Calendrier;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;

import java.time.LocalDate;
import java.time.LocalTime;

public class Utilisateur {

    private String pseudo ;

    private Calendrier calendrier ;

    private Planning planning ;

    private Journée selectedDay ; //La journée sélectionnée par l'utilisateur

    public Utilisateur(String pseu )
    { this.pseudo=pseu ;
 }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void fixerPeriode_Planning(LocalDate d , LocalTime f){
    }

    public void validerPlanning (){
        calendrier.ajouterPlanning(planning);
    }

    public void Planif_Man(){

    }
}
