package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Decomposable;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class TacheDecomposable extends Tache implements Decomposable {

    private TreeSet<TacheSimple> tachesSimples  ;


    public TacheDecomposable(String n , LocalDate d , LocalTime t , Priorité p){
        super(n,d,t,p);
    }

    @Override
    public boolean planifier(Planning plan ) {
        for (Creneau c : plan.getCreneauxLibres() ){
            if ( !getDateLimite().isAfter(c.getDatejournée())) {
                if (c.ajoutTache(this)) {
                    c.decomposer(plan);//Ajouter le cas de la durée minimale
                    c.rendreoccupé();
                    plan.getCreneauxLibres().remove(c);
                    return true;
                }
            }
        }
        //On fait la décomposition de la tache en sous taches
        return decomposer(plan) ;
    }

    public void gérerSousTache(int i , Creneau c){

        TacheSimple t = new TacheSimple(getNom()+i,getDateLimite(),getHeurelimite(),getPriorité()) ;
        if (getDurée().compareTo(c.getDuree()) >= 0) t.setDurée(c.getDuree());
        else t.setDurée(getDurée());
        setDurée(getDurée().minus(t.getDurée()));
        tachesSimples.add(t) ;
        c.ajoutTache(t) ;
        c.rendreoccupé();


    }

    @Override
    public boolean decomposer(Planning plan) {
        int i = 1 ; //Numéro de la sous tache
        Iterator<Creneau> iterator = plan.getCreneauxLibres().iterator() ;
        Creneau cr ;
        while (getDurée().compareTo(Duration.ZERO) > 0 && iterator.hasNext()) {
            cr = iterator.next() ;
            if (!getDateLimite().isAfter(cr.getDatejournée())) {
                gérerSousTache(i, cr);
                plan.getCreneauxLibres().remove(cr);
                cr = iterator.next();
                i++;
            }
            else return false ;
        }
        if (getDurée().compareTo(Duration.ZERO) > 0) return false ;
        else return true;
    }
}
