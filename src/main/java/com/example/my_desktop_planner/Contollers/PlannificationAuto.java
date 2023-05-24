package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.TachBox;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlannificationAuto implements Initializable {
    private Utilisateur utilisateur ;

    @FXML
    private ScrollPane scroll1 , scroll2 ;
    @FXML
    private VBox vbox1 , vbox2 ;

    @FXML
    private Button AjouterTache , Plannifier ;


    private ArrayList<Tache> taches = new ArrayList<>() ;

    public PlannificationAuto(Utilisateur user )
    {utilisateur=user  ;}



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        for(Tache tache : utilisateur.getTaches()){
            TachBox tachBox = new TachBox(tache) ;
            tachBox.setOnMouseClicked(event -> {
                taches.add(tache) ;
              TachBox tach = new TachBox(tache) ;
                tach.setPrefHeight(50);
                tach.setPrefWidth(100);
                tach.setStyle("-fx-background-color: #DDDDDD;");
                tach.setStyle("-fx-border-color:  #845EF1;");
              vbox2.getChildren().add(tach) ;
            });

            tachBox.setPrefHeight(50);
            tachBox.setPrefWidth(100);
            tachBox.setStyle("-fx-background-color: #DDDDDD;");
            tachBox.setStyle("-fx-border-color:  #845EF1;");
            vbox1.getChildren().add(tachBox);
        }

        Plannifier.setOnMouseClicked(mouseEvent -> {
           Utilisateur.trierTaches(taches);
           System.out.println(utilisateur.PlanifAuto(taches) );




        });


    }
}
