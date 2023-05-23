package com.example.my_desktop_planner.Gestion;

import com.example.my_desktop_planner.Planification.Calendrier;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import com.example.my_desktop_planner.Taches_Prj.TacheSimple;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Iterator;

public class Utilisateur {

    private String pseudo ;

    private Calendrier calendrier  ;

    private Planning planning  ;

    private Tache[] taches_user ;

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

    public Planning getPlanning(){
        return planning ;
    }


    public void setCalendrier(Calendrier c) {
        this.calendrier = c;
    }


    public Calendrier getCalendrier(){
        return calendrier ;
    }

    public void fixerPeriode_Planning(LocalDate d , LocalTime f){
    }

    public void validerPlanning (){
        calendrier.ajouterPlanning(planning);
    }

    public void Planif_Man(Tache t , LocalDate d , Creneau c){
          Journée jr = planning.Rechjournee(d) ;
          if (jr != null ) {
              if (c.crenLibre())  {
                  //On va manipuler que les taches simples
                  if (c.ajoutTache(t)) {
                      Creneau c1 = c.decomposer_cr();
                      if (c1 != null) {
                          //Rajouter le créneau à la journée trouvée dans la recherche
                          jr.AjouterCreneau(c1);
                      }
                  }
              }
          }
    }


    public void PlanifAuto(LocalDate debut , LocalDate fin , Tache[] taches ){
          for (Tache t : taches ){
              /**Rechercher d'abord un créneau libre convenable à la tache*/

              if (t instanceof TacheSimple){

              }
          }
    }

    public void ajouterPlanning(LocalDate debut , LocalDate fin ){
        if (Planning.verifDateDebut(debut) && Planning.verifDateFin(debut,fin)){
            planning = new Planning(debut , fin ) ;
            planning.setJournées(calendrier.creerJournéesPeriode(debut,fin));
        }
    }




}
