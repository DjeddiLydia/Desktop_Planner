package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Contollers.AffichTach;
import com.example.my_desktop_planner.Contollers.AjouterTache;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class Box extends VBox {

    private Creneau creneau ;
    private Utilisateur utilisateur ;



    public Box (Creneau creanau ,  Utilisateur user) {
        super(30) ;
        this.creneau=creanau ;
        this.utilisateur=user ;

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

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");



        this.getChildren().addAll(HD , libre , tache  ,  HF );
        if (creanau.getTache()!=null) {
            if(creanau.getTache().getCouleur()!= null){

            String hexColor = convertToHexa(Color.valueOf(creanau.getTache().getCouleur()))  ;
            this.setStyle("-fx-background-color: " + hexColor + ";"); }
            else {  this.setStyle("-fx-border-color:  #FFB775;"); }

            String time1 = creanau.getTache().getHeurelimite().format(formater);

            Label TL = new Label("time limit " +time1) ;
            this.getChildren().addAll(new Label("Durée tache " + creanau.getTache().getDurée().toString()) , new Label( "Date limite "+( creanau.getTache().getDateLimite()).format(formatter1) ), TL  ) ;

        }
        else { this.setStyle("-fx-border-color:  #FFB775;");}

        this.setOnMouseClicked(event -> {

            try {showAffichTache();}
            catch (IOException e) {
                throw new RuntimeException(e);
            }



        });



    }

    public void showAffichTache() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/plannification/AffichTaches.fxml"));
        loader.setControllerFactory(obj-> new AffichTach(this.utilisateur , creneau));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage() ;
        stage.setScene(scene);
        stage.show();


    }
    public String convertToHexa (Color c ) {
        String hexString = String.format("#%02X%02X%02X", (int)(c.getRed() * 255),
                (int)(c.getGreen() * 255), (int)(c.getBlue() * 255));
        return hexString ;
    }




}
