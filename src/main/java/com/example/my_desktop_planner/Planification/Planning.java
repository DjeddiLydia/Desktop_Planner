package com.example.my_desktop_planner.Planification;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Planning {

    private LocalDate debut ;
    private LocalDate fin ;

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

    public Journée Rechjournee(LocalDate d ){
        Iterator<Journée> iterator = journées.iterator() ;
        Journée jr =  iterator.next();
        while((jr.getDate() != d) && iterator.hasNext()){
            jr =  iterator.next();
        }
        if(jr.getDate() == d) return  jr ;
        else return null ;
    }

    public void AfficherPlan(){
        for (Journée j : journées){
            System.out.println(j.getDate());
        }
    }


}
