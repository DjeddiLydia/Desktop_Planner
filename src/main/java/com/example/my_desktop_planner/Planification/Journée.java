package com.example.my_desktop_planner.Planification;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Journée implements Comparable <Journée>{

    private LocalDate date;
    private Set<Creneau> creneaus;
    private static final Duration dureeMax = Duration.ofMinutes(1440);
    private Duration dureePrise;


    public Journée(LocalDate d) {
        this.date = d;
    }

    public LocalDate getDate() {return this.date ; }

    public void AjouterCreneau(Creneau c) {

            this.creneaus.add(c);

    }

    @Override
    public int compareTo(Journée j ) {
        return date.compareTo(j.getDate());
    }

    @Override
    public boolean equals(Object o ){
        if (this  ==o ) return true ;
        if (o== null || o.getClass()!= getClass()) return false ;

        return this.date == ((Journée) o).getDate() ;

    }
}
