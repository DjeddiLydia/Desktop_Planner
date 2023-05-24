package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Planification.Creneau;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;

public class Box extends VBox {


    public Box (Creneau creanau) {
        super(40) ;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

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
        this.getChildren().addAll(HD , libre , tache , HF );



    }


}
