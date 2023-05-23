package com.example.my_desktop_planner.Planification;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Planning {

    private LocalDate debut ;
    private LocalDate fin ;

    private TreeSet<Creneau> creneauxLibres ;

    private TreeSet<Journée>journées ;


    public Planning ( LocalDate d , LocalDate f , TreeSet<Journée> j){
        debut = d ;
        fin = f ;
        journées = j ;
    }

    public Planning ( LocalDate d , LocalDate f ){
        debut = d ;
        fin = f ;
    }

    public Planning(){}

    public void setCreneauxLibres() {
        this.creneauxLibres = this.listCreneauxLibres() ;
    }

    public TreeSet<Creneau> getCreneauxLibres() {
        return creneauxLibres;
    }

    public static boolean verifDateDebut(LocalDate d ){
        if (d.isBefore(LocalDate.now())) return false ;
        else return true ;
    }

    public void setJournées(TreeSet<Journée> jrs){
        journées = jrs ;
    }


    public static boolean verifDateFin(LocalDate d , LocalDate f ){
        if (f.isBefore(d)) return false ;
        else return true ;
    }

    public boolean periodeIncluse(LocalDate d , LocalDate f){
        return debut.compareTo(d) <=0 && fin.compareTo(f)>=0 ;
    }

    public Journée Rechjournee(LocalDate d ){
        Iterator<Journée> iterator = journées.iterator() ;
        Journée jr =  iterator.next();
        while((jr.getDate() != d) && iterator.hasNext()){
            jr =  iterator.next();
        }
        if(jr.getDate() == d) return  jr ;
        else return null ;
    }

   /* public void AfficherPlan(){
        for (Journée j : journées){
            System.out.println(j.getDate());
        }
    }*/

    public void ajouterjournée(Journée j){
        journées.add(j) ;
    }

    public TreeSet<Creneau> listCreneauxLibres(){
        TreeSet<Creneau> crenlibres = new TreeSet<>() ;
        for (Journée j : journées){
            crenlibres.addAll(j.listCreneauxLibres()) ;
        }
        return  crenlibres ;
    }

}
