package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Planification.Planning;

import java.util.List;

public class TacheDecomposable extends Tache  {

    private List<TacheSimple> taches ;

    @Override
    public boolean planifier(Planning plan ) {
        return true ;
    }




}
