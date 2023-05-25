package com.example.my_desktop_planner.Taches_Prj;

import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Decomposable;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class TacheDecomposable extends Tache implements Decomposable , Serializable {

    private ArrayList<TacheSimple> tachesSimples   = new ArrayList<>();


    public TacheDecomposable(String n , LocalDate d , LocalTime t , Priorité p){
        super(n,d,t,p);
    }

    public TacheDecomposable(String n ,Duration dr , LocalDate d , LocalTime t , Priorité p, EtatRealisation e){
        super(n,dr,d,t,p,e);
    }

    public TacheDecomposable(String n ,Duration dr , LocalDate d , LocalTime t , Priorité p){
        super(n,dr,d,t,p);
    }
    @Override
    public boolean planifier(Planning plan ) {
        for (Creneau c : plan.getCreneauxLibres() ){
            if ( !getDateLimite().isBefore(c.getDatejournée())) {
                if (c.ajoutTache(this)) {
                    c.decomposer(plan);//Ajouter le cas de la durée minimale
                    c.rendreoccupé();
                    plan.getCreneauxLibres().remove(c);
                    c = plan.getCreneauxLibres().first() ;
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
        t.setCategorie(getCategorie());
        c.ajoutTache(t) ;
        c.rendreoccupé();
        System.out.println("CR " + c.getDatejournée() );

    }

    @Override
    public boolean decomposer(Planning plan) {
        TreeSet<Creneau> creneaus =  proposerDecomposition(plan);
        if (creneaus!=null){
            for (Creneau c : creneaus){
                c.rendreoccupé();
                //plan.getCreneauxLibres().remove(c) ;
            }
            return true ;
        }
        else {
            System.out.println("Liste de créneaux vide ");
            return false ;
        }
       /* int i = 1 ; //Numéro de la sous tache
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
        else return true;*/
    }


    public TreeSet<Creneau> proposerDecomposition(Planning plan){
        int i = 1 ; //Numéro de la sous tache
        TreeSet<Creneau> creneaus = new TreeSet<>() ;
        plan.setCreneauxLibres();
        Iterator<Creneau> iterator = plan.getCreneauxLibres().iterator() ;
        for (Creneau crf: plan.getCreneauxLibres() ){
            System.out.println("Cr Trouvé");
        }
        Creneau cr ;
        Duration dureeinitiale = getDurée() ;
        while (getDurée().compareTo(Duration.ZERO) > 0 && iterator.hasNext()) {
            cr = iterator.next() ;
            if (!getDateLimite().isBefore(cr.getDatejournée())) {
                gérerSousTache(i, cr);
                System.out.println("Durée restante : "+getDurée());
                creneaus.add(cr) ;
                i++;
            }
            else{
                System.out.println("Deadline dépassé");
                setDurée(dureeinitiale);
                //return null ;
                return creneaus ;
            }
            System.out.println("Next "+iterator.hasNext());
        }
        if (getDurée().compareTo(Duration.ZERO) > 0) {
            setDurée(dureeinitiale);
            System.out.println("Pas de créneaux suffisants");
            return creneaus ;
           // return null ;
        }
        else {
            setDurée(dureeinitiale);
            return creneaus;
        }
    }

    public void DefineEtatRealisation(){
        boolean stop = false ;
        int i =0 ;
        TacheSimple t = tachesSimples.get(i) ;
        while (!stop && i!=tachesSimples.size()){
            if (!t.getEtat().equals(EtatRealisation.Completed)) stop=true ;
            i++ ;
            t = tachesSimples.get(i) ;
        }
        if (!stop) {
            setEtat(EtatRealisation.Completed);
            return;
        }
        i =0 ;
        stop = false ;
        t = tachesSimples.get(i) ;
        while (!stop && i!=tachesSimples.size()){
            if (!t.getEtat().equals(EtatRealisation.Cancelled)) stop=true ;
            i++ ;
            t = tachesSimples.get(i) ;
        }
        if (!stop) {
            setEtat(EtatRealisation.Cancelled);
            return;
        }

        i =0 ;
        stop = false ;
        t = tachesSimples.get(i) ;
        while (!stop && i!=tachesSimples.size()){
            if (!t.getEtat().equals(EtatRealisation.Cancelled)) stop=true ;
            i++ ;
            t = tachesSimples.get(i) ;
        }
        if (!stop) {
            setEtat(EtatRealisation.Cancelled);
            return;
        }
    }
}
