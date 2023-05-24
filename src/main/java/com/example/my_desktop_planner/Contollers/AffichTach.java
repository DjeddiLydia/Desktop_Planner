package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.TachBox;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AffichTach implements Initializable {

    private Utilisateur utilisateur ;
    private Creneau creneau ;
    private Tache tache ;
    private Plannin plannin ;

    @FXML
    private ScrollPane scroll ;
    @FXML
    private Button plannifier ;
    public AffichTach(Utilisateur user , Creneau cren )
    { this.utilisateur=user ;
    creneau = cren ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        VBox vbox = new VBox() ;

        for(Tache tache : utilisateur.getTaches()){
            TachBox tachBox = new TachBox(tache) ;
            tachBox.setOnMouseClicked(event -> {
               this.tache =  tachBox.getTache() ;
               System.out.println(tache.getNom());

            });
            tachBox.setStyle("-fx-background-color: #DDDDDD;");
            tachBox.setStyle("-fx-border-color:  #845EF1;");
            vbox.getChildren().add(tachBox);
        }
        scroll.setContent(vbox);

        plannifier.setOnMouseClicked(event -> {
            if(tache!=null){
               utilisateur.Planif_Man(tache ,creneau );

            }

        });



    }
}
