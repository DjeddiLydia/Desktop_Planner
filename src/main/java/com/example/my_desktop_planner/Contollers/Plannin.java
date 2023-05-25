package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Box;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class Plannin implements Initializable {

    private Utilisateur utilisateur;

    public Plannin(Utilisateur user) {
        this.utilisateur = user;
    }

    @FXML
    private ScrollPane scroll;
    @FXML
    private Button Refresh , PlannificationAuto , RetourHome;

    private HBox innerBoxContainer = new HBox() ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createPlanning();


    }

    public void createPlanning() {

        innerBoxContainer.setSpacing(10);
        scroll.setContent(innerBoxContainer);
        LocalDate date = utilisateur.getPlanning().getDebut();

        while (!date.isAfter(utilisateur.getPlanning().getFin())) {


            VBox vbox = new VBox();
            vbox.setSpacing(5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = date.format(formatter);

            Label d = new Label(formattedDate);
            vbox.getChildren().add(d);
            vbox.setPrefWidth(150);
            vbox.setPrefHeight(800);
            vbox.setStyle("-fx-background-color: #E1EFF2;");
            vbox.setStyle("-fx-border-color:  #FFB775;");
            vbox.setStyle("-fx-border-width: 5px ");
            innerBoxContainer.getChildren().add(vbox);

            Journée j = utilisateur.getPlanning().rechercheJour(date);
            if (j != null) {
                for (Creneau c : j.getCreneaus()) {
                    Box innerBox = new Box(c, this.utilisateur);
                    innerBox.setPrefSize(150, 50);
                    innerBox.setStyle("-fx-background-color: #DDDDDD;");
                    innerBox.setStyle("-fx-border-color:  #845EF1;");
                    vbox.getChildren().add(innerBox);
                }

            }
            date = date.plusDays(1);
        }


        Refresh.setOnMouseClicked(mouseEvent -> {
            deletePlanning();
            createPlanning();

        });

        PlannificationAuto.setOnMouseClicked(mouseEvent -> {
           try {showPlannificationAuto(); }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        });




    }

    public void deletePlanning(){
        innerBoxContainer.getChildren().clear();


    }

    public void showPlannificationAuto() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/plannification/PlannificationAuto.fxml"));
        loader.setControllerFactory(obj-> new PlannificationAuto(this.utilisateur));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage() ;
        stage.setScene(scene);
        stage.show();


    }

    /* public void switchToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/HomePage/HomePge.fxml"));
        loader.setControllerFactory(obj-> new HomePageContro());
        Stage stage = (Stage) RetourHome.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }*/


}
