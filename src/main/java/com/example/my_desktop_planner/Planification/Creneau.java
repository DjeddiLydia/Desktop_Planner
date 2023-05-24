package com.example.my_desktop_planner.Planification;

import com.example.my_desktop_planner.Taches_Prj.Tache;
import com.example.my_desktop_planner.Taches_Prj.TacheSimple;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Creneau implements Comparable <Creneau> , Decomposable{
    private boolean libre;
    private LocalDate datejournée ;
    private LocalTime debut;
    private LocalTime fin;
    private Duration duree;
    private Duration dureemin;
    private Tache tache;
    private boolean bloque ;


    public  Creneau(LocalTime d, LocalTime f, Duration min ) {
        this.libre = true;
        this.debut = d;
        this.fin = f;
        this.duree = Duration.between(this.debut, this.fin);
        this.dureemin = min;
        this.bloque = false ;
    }

    public  Creneau(LocalTime d, LocalTime f) {
        this.libre = true;
        this.debut = d;
        this.fin = f;
        this.duree = Duration.between(this.debut, this.fin);
        this.bloque = false ;
    }

    public Creneau(LocalTime d, LocalTime f, Duration min, LocalDate djr ) {
        this.libre = true;
        this.debut = d;
        this.fin = f;
        this.duree = Duration.between(this.debut, this.fin);
        this.dureemin = min;
        this.bloque = false ;
        this.datejournée = djr ;
    }

    public LocalDate getDatejournée() {
        return datejournée;
    }

    public Duration getDuree() {
        return this.duree;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime d){this.debut = d ; }

    public void rendreoccupé(){
        libre = false ;
    }

    public void setlibre(){
        libre = true ;
    }

    public LocalTime getFin()
    {return this.fin ;  }

    public void setFin(LocalTime f)
    {this.fin = f ; }

    public Tache getTache() {return this.tache ; }

    @Override

    public boolean equals(Object o ){
        if (this ==o ) return true ;
        if (o== null || o.getClass()!= getClass()) return false ;

        if (this.getDebut().equals(((Creneau) o).getDebut()) & this.getFin().equals(((Creneau) o).getFin())) return true ;

        if (this.getDebut().isAfter(((Creneau) o).getDebut() )  & ((this.getDebut().isAfter(((Creneau) o).getFin() ))) || (this.getDebut().equals(((Creneau) o).getFin() )))  return false ;
        if (this.getDebut().isBefore(((Creneau) o).getDebut() )  & ((this.getFin().isBefore(((Creneau) o).getDebut() ))) || (this.getFin().equals(((Creneau) o).getDebut() )))  return false ;
        else return true ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(debut, fin);
    }

    @Override
    public int compareTo(Creneau autreCreneau) {
        return debut.compareTo(autreCreneau.getDebut());
    }

    public boolean ajoutTache(Tache t){
       if ( t.getDurée().compareTo(duree)<=0) {
           tache = t ;
           return true ;
       }
       else {
           System.out.println("La durée de la tache est supérieure à la durée du créneau");
           return false ;
       }
    }

    public boolean decomposer(Planning plan){
        if (tache != null){
            if ( duree.minus(tache.getDurée()).compareTo(dureemin) >= 0 ){
                 //On fait la décompostion du créneau
                duree = tache.getDurée() ;
                Creneau newcren = new Creneau(debut.plus(duree) , fin , dureemin , datejournée) ;
                fin = debut.plus(duree) ;
                Journée j = plan.Rechjournee(datejournée) ;
                plan.getCreneauxLibres().add(newcren) ;
                //Traiter le cas d'ajout d'un créneau libre
                j.ajouterCreneau(newcren);
                return  true ;
            }
            else{
                System.out.println("decomposition impossible du créneau\n");
                return false  ;
            }
        }
        else {
            System.out.println("La tache est null");
            return false ;
        }
    }

    public void setJournée(LocalDate journée) {
        this.datejournée = journée;
    }

    public Creneau copie(){
        Creneau c = new Creneau(debut , fin , dureemin) ;
        return c ;
    }


    public boolean crenLibre(){
        if (libre=true) return true ;
        else return false ;
    }


}
