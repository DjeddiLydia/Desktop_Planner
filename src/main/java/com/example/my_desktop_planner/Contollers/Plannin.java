package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Box;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class Plannin implements Initializable {

    private Utilisateur utilisateur ;

    public Plannin (Utilisateur user) {this.utilisateur = user ; }

    @FXML
    private ScrollPane scroll ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        HBox innerBoxContainer = new HBox() ;
        innerBoxContainer.setSpacing(10);
       // innerBoxContainer.setPrefWidth(scroll.getPrefWidth());
        //innerBoxContainer.setPrefHeight(scroll.getPrefHeight());
        scroll.setContent(innerBoxContainer);
        LocalDate date = utilisateur.getPlanning().getDebut();

        while ( !date.isAfter(utilisateur.getPlanning().getFin()) )  {

            System.out.println(date);

            VBox vbox = new VBox() ;
            vbox.setSpacing(5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = date.format(formatter);
            Label d = new Label(formattedDate) ;
            vbox.getChildren().add(d) ;
            vbox.setPrefWidth(150);
            vbox.setPrefHeight(800);
            vbox.setStyle("-fx-background-color: #E1EFF2;");
            vbox.setStyle("-fx-border-color:  #FFB775;");
            vbox.setStyle("-fx-border-width: 5px ");
            innerBoxContainer.getChildren().add(vbox);

            Journée j = utilisateur.getPlanning().rechercheJour(date) ;
            if (j!=null )
            {     for (Creneau c : j.getCreneaus()) {
                Box innerBox = new Box(c);
                innerBox.setPrefSize(150, 50);
                innerBox.setStyle("-fx-background-color: #DDDDDD;");
                innerBox.setStyle("-fx-border-color:  #845EF1;");
                vbox.getChildren().add(innerBox);


            }

            }
            date=date.plusDays(1) ;




        }



    }
}
