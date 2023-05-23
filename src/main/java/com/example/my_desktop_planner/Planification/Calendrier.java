package com.example.my_desktop_planner.Planification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Calendrier {

    private TreeSet<Journée> journées = new TreeSet<>() ;
    private List<Planning> plannings  ;





    public void setJournées(TreeSet<Journée> jrs){
        journées = jrs ;
    }

    public void ajouterPlanning(Planning p ){
        plannings.add(p) ;
    } //A compléter ( il reste à parcourir les journées ....)

    public TreeSet<Journée> getJournées(){
        return journées ;
    }

    public Journée Rechjournee(LocalDate d ){
       /* Iterator<Journée> iterator = journées.iterator() ;
        Journée jr =  iterator.next();
        while((jr.getDate() != d) && iterator.hasNext()){
            jr =  iterator.next();
        }
        if(jr.getDate() == d) return  jr ;
        else return null ;*/

        for (Journée j : journées ){
            if (j.getDate().equals(d)) {
                return j ;
            }
        }
        return null ;

    }

   public TreeSet<Journée> creerJournéesPeriode(LocalDate debut , LocalDate fin){
        TreeSet<Journée> journéesPlan = new TreeSet<>()   ;
        LocalDate date = debut ;
        while ( !date.isAfter(fin)){
            if (Rechjournee(date)!=null){
                //System.out.println(date);
               journéesPlan.add(Rechjournee(date)) ;

            }
            date = date.plusDays(1) ;

        }
       return journéesPlan ;
    }


    public void Affichjours(){
        for (Journée j : journées ){
            System.out.println(j.getDate());
        }
    }



}
