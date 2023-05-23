package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Exceptions.ExceptionAjoutTache;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;

import java.time.LocalDate;
import java.time.LocalTime;

public class TacheSimple extends Tache {

    public TacheSimple(String n , LocalDate d , LocalTime t , Priorité p){
        super(n,d,t,p);
    }

    private int periodicité = 1;

    public void ajouterTache(Creneau creneau) {
        if (this.getDurée().compareTo(creneau.getDuree()) > 0) { }
    }

    @Override
    public boolean planifier(Planning plan) {
        for (Creneau c : plan.getCreneauxLibres() ){
           if ( !getDateLimite().isAfter(c.getDatejournée())) {
               if (c.ajoutTache(this)) {
                   c.decomposer(plan);
                   c.rendreoccupé();
                   plan.getCreneauxLibres().remove(c);
                   return true;
               }
           }
        }
        return false ;
    }


}


