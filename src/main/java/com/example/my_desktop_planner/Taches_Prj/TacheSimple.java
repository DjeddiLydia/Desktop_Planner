package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Exceptions.ExceptionAjoutTache;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class TacheSimple extends Tache {

    private int periodicité = 0;

    public TacheSimple(String n , LocalDate d , LocalTime t , Priorité p){
        super(n,d,t,p);
    }

    public TacheSimple(String n , Duration dr ,LocalDate d , LocalTime t , Priorité p ,EtatRealisation e){
        super(n,dr,d,t,p,e);
    }

    public TacheSimple(String n , Duration dr ,LocalDate d , LocalTime t , Priorité p  , EtatRealisation e, int prd){
        super(n,dr,d,t,p,e);
        periodicité = prd ;
    }

    public void setPeriodicité(int periodicité) {
        this.periodicité = periodicité;
    }

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
                   if (periodicité != 0) {
                       LocalDate date = c.getDatejournée().plusDays(periodicité );
                       while (!date.isAfter(plan.getFin())) {
                              if (plan.Rechjournee(date)!=null){
                                  for (Creneau cr : plan.Rechjournee(date).listCreneauxLibres() ){
                                      if (cr.ajoutTache(this)) {
                                          cr.decomposer(plan);
                                          cr.rendreoccupé();
                                          plan.getCreneauxLibres().remove(c);
                                      }
                                  }
                              }
                           date = date.plusDays(periodicité) ;
                       }
                   }
                   return true;
               }
           }
        }
        return false ;
    }

}


