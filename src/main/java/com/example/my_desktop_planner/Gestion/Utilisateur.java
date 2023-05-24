package com.example.my_desktop_planner.Gestion;

import com.example.my_desktop_planner.Planification.Calendrier;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import com.example.my_desktop_planner.Taches_Prj.TacheDecomposable;
import com.example.my_desktop_planner.Taches_Prj.TacheSimple;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Utilisateur {

    private String pseudo ;

    private Calendrier calendrier = new Calendrier()  ;

    private Planning planning  ;

    private ArrayList<Tache >  taches = new ArrayList<>();

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
                      c.decomposer(planning);
                      c.rendreoccupé();
                  }
                  else System.out.println("On peut pas rajouter la tache au créneau");
              }
          }
    }



    public void ajouterPlanning(LocalDate debut , LocalDate fin ){
        if (Planning.verifDateDebut(debut) && Planning.verifDateFin(debut,fin)){
            planning = new Planning(debut , fin ) ;
            planning.setJournées(calendrier.creerJournéesPeriode(debut,fin));
        }
    }

    //Rajouter un créneau pour toutes les journées du planning
    public void ajouterCreneauPeriode(LocalDate debut , LocalDate fin,Creneau c){
        if (planning.periodeIncluse(debut,fin)) {
            LocalDate date = debut;
            while (!date.isAfter(fin)) {
                Creneau c1 = c.copie() ;
                c1.setJournée(date);
                if (planning.Rechjournee(date) != null) planning.Rechjournee(date).ajouterCreneau(c1);
                else {
                    Journée jr = new Journée(date);
                    jr.ajouterCreneau(c1);
                    planning.ajouterjournée(jr);
                    planning.getCreneauxLibres().add(c1) ;
                }
                date = date.plusDays(1);
            }
        }
    }

    public void ajoutercreneau(LocalDate date, Creneau c){

        if (planning.Rechjournee(date) != null) planning.Rechjournee(date).ajouterCreneau(c);
        else {
            Journée jr = new Journée(date);
            jr.ajouterCreneau(c);
            planning.ajouterjournée(jr);
            planning.getCreneauxLibres().add(c) ;
        }
    }


    //Trier le tableau des taches selon la priorité puis la date deadline puis l'heure du deadline
    public void trierTaches(){
        Collections.sort(taches , Comparator.comparing(Tache :: getDateLimite).thenComparing(Tache :: getHeurelimite).thenComparing(Tache :: getPriorité));
    }


    public boolean PlanifAuto(){
        int i=0 ;
        if (!taches.isEmpty()) {
            while (i != taches.size()) {
                Tache t = taches.get(i);
                if (t instanceof TacheSimple){
                     if (t.planifier(planning)) ;
                     else return false ;
                }
                else if (t instanceof TacheDecomposable){
                    if (t.planifier(planning)) ;
                    else return false ;
                }
                i++ ;
            }
            return true ;
        }
        else return false ;
    }

    public void setTaches(ArrayList<Tache > t) {
        taches = t ;
    }

    public void affichTaches(){
        for(Tache t : taches ){
            System.out.println(t.getNom());
        }
    }


    public void ajoutTache(Tache t){
        taches.add(t) ;
    }


}
