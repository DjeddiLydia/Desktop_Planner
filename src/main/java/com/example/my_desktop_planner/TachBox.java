package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Contollers.AjouterTache;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class TachBox extends VBox {

    private Tache tache ;


    public TachBox(Tache tache) {
        super(40) ;
      this.tache=tache ;

       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Convert LocalTime to String
        String timeString = creanau.getDebut().format(formatter);
        Label HD = new Label(timeString) ;

        Label libre = new Label() ;

        if (creanau.crenLibre()==true)
        libre.setText("Libre");
        else libre.setText("Occupé");

        Label tache = new Label() ;

        if (creanau.getTache()!= null )
        {tache.setText("Tache " + creanau.getTache().getNom()); }

        DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = creanau.getFin().format(formatter);

        Label HF = new Label(time) ;
        this.getChildren().addAll(HD , libre , tache , HF );*/
        Label nom = new Label(tache.getNom()) ;
        this.getChildren().add(nom) ;





    }


    public Tache getTache() {
        return tache;
    }
}
