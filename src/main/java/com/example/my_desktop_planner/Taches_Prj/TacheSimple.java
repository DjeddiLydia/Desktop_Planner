package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Exceptions.ExceptionAjoutTache;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

public class TacheSimple extends Tache implements Serializable {


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

    public TacheSimple(String n , Duration dr ,LocalDate d , LocalTime t , Priorité p , int prd){
        super(n,dr,d,t,p);
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
        Iterator<Creneau> iterator = plan.getCreneauxLibres().iterator() ;
        if (plan.getCreneauxLibres().isEmpty()) System.out.println("Liste de créneaux vide");
        Creneau c ;
        while (iterator.hasNext()){
            c= iterator.next() ;
            System.out.println(c.getDatejournée());
           if ( !getDateLimite().isBefore(c.getDatejournée())) {
               if (c.ajoutTache(this)) {
                   c.decomposer(plan);
                   c.rendreoccupé();
                   iterator.remove();
                   if (periodicité != 0) {
                       LocalDate date = c.getDatejournée().plusDays(periodicité );
                       while (!date.isAfter(plan.getFin())) {
                           System.out.println("Date periodicité : "+date);
                              if (plan.Rechjournee(date)!=null){
                                  Iterator<Creneau> iterator1 = plan.Rechjournee(date).listCreneauxLibres().iterator() ;
                                  Creneau cr ;
                                  while (iterator1.hasNext() ){
                                      cr = iterator1.next() ;
                                      System.out.println("Date Créneau: "+cr.getDatejournée());
                                      if (cr.ajoutTache(this)) {
                                          cr.decomposer(plan);
                                          cr.rendreoccupé();
                                          iterator1.remove();
                                          plan.getCreneauxLibres().remove(cr);
                                          iterator = plan.getCreneauxLibres().iterator() ;
                                      }
                                  }
                              }
                              else System.out.println("Journée inexsistante");
                           date = date.plusDays(periodicité) ;
                       }
                   }
                   return true;
               }
               else {
                   System.out.println(("Prochain créneau "));
               }
           }
           else {
               System.out.println("Deadline dépassé");
               return false ;
           }
        }
        /*for (Creneau c : plan.getCreneauxLibres() ){
           if ( !getDateLimite().isBefore(c.getDatejournée())) {
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
               else {
                   System.out.println(("Prochain créneau "));
               }
           }
           else {
               System.out.println("Deadline dépassé");
               return false ;
           }
        }*/
        return false ;
    }

}


