package com.example.my_desktop_planner.Planification;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Journée implements Comparable<Journée> {

    private LocalDate date;
    private TreeSet<Creneau> creneaus = new TreeSet<>();
    private static final Duration dureeMax = Duration.ofMinutes(1440);
    private Duration dureePrise;


    public Journée(LocalDate d) {
        this.date = d;
    }

    public LocalDate getDate() {return this.date ; }


    public void ajouterCreneau(Creneau c) {

            this.creneaus.add(c);
    }

    public TreeSet<Creneau> getCreneaus() {return this.creneaus ; }

    @Override
    public boolean equals(Object o ){
        if (this  ==o ) return true ;
        if (o== null || o.getClass()!= getClass()) return false ;

        return this.date == ((Journée) o).getDate() ;
    }


   /* public Creneau NextCrenLib(){
        Creneau c = iterator1.next() ;
        while (!c.crenLibre() && iterator1.hasNext()){
            c = iterator1.next() ;
        }
        if (c.crenLibre()) return  c ;
        else return null ;
    }*/


    @Override
    public int compareTo(Journée o) {
        return date.compareTo(o.getDate());
    }

    public TreeSet<Creneau> listCreneauxLibres(){
        TreeSet<Creneau> crenlibres = new TreeSet<>() ;
        for (Creneau c : creneaus){
             if (c.crenLibre()) crenlibres.add(c) ;
        }
        return  crenlibres ;
    }



}
