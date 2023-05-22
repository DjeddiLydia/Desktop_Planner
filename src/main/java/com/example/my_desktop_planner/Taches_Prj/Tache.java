package com.example.my_desktop_planner.Taches_Prj;

import java.time.Duration;
import java.time.LocalDate;

public class Tache {
    private String nom ;
    private Duration durée ;
    private LocalDate dateLimite  ;
    private Priorité priorité ;


    public Duration getDurée() {
        return durée;
    }

    public void setDurée(Duration durée) {
        this.durée = durée;
    }
}
