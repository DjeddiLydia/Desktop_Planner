package com.example.my_desktop_planner.Planification;

import java.time.LocalDate;
import java.util.Set;

public class Planning {

    private LocalDate debut ;
    private LocalDate fin ;

    private Set<Journée>journées ;

    public Planning ( LocalDate d , LocalDate f , Set<Journée> j){
        debut = d ;
        fin = f ;
        journées = j ;
    }

    public Planning(){}
    public boolean verifDateDebut(LocalDate d ){
        if (d.isBefore(LocalDate.now())) return false ;
        else return true ;
    }

    public boolean VerifDateFin(LocalDate f){
        if (f.isBefore(debut)) return false ;
        else return true ;
    }


}
