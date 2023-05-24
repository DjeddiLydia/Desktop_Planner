package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Box;
import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.SerUtilisateur;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class HomePageContro implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button ajouterPlanning , AjouterTache , deconnexion , VoirPlanning;

    @FXML
    private AnchorPane anchorPane;

    private Utilisateur utilisateur ;
    private App app ;


    public HomePageContro(Utilisateur utilisateur , App a){
        this.utilisateur=utilisateur;
        this.app = a ;
    }
    public HomePageContro(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //DatePicker datePicker = new DatePicker(LocalDate.now());


        /*DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);

        Node popupContent = datePickerSkin.getPopupContent();
        anchorPane.getChildren().add(popupContent) ;
        popupContent.setLayoutX(20);
        popupContent.setLayoutY(100) ;*/

        Creneau creneau1 = new Creneau(LocalTime.of(9, 0), LocalTime.of(10, 0));
        Creneau creneau2 = new Creneau(LocalTime.of(14, 30), LocalTime.of(16, 0));
        Creneau creneau3 = new Creneau(LocalTime.of(18, 0), LocalTime.of(19, 30));
        Creneau creneau4 = new Creneau(LocalTime.of(20, 0), LocalTime.of(21, 0));



        VBox innerBoxContainer = new VBox();
        innerBoxContainer.setSpacing(40);
        innerBoxContainer.setPadding(new Insets(10));

        // Create inner boxes


        // Set VBox as the content of ScrollPane
        scrollPane.setContent(innerBoxContainer);

        ajouterPlanning.setOnMouseClicked(actionEvent -> {

            try {
                showAjouterPlanning();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }




        });

        datePicker.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {


                Journée j = utilisateur.getCalendrier().Rechjournee(datePicker.getValue());

                if (j != null) {

                    for (Creneau c : j.getCreneaus()) {
                        Box innerBox = new Box(c , this.utilisateur);
                        innerBox.setPrefSize(300, 50);
                        innerBox.setStyle("-fx-background-color: #DDDDDD;");
                        innerBox.setStyle("-fx-border-color:  #845EF1;");
                        innerBoxContainer.getChildren().add(innerBox);


                    }
                }
            }


        });

        AjouterTache.setOnMouseClicked(actionEvent -> {

            try {showAjouterTache(); }
         catch (IOException e) {
            throw new RuntimeException(e);
        }


        });


        AjouterTache.setOnMouseClicked(actionEvent -> {

            try {showAjouterTache(); }
            catch (IOException e) {
                throw new RuntimeException(e);
            }


        });

        deconnexion.setOnMouseClicked(actionEvent -> {

           this.app.saveToFile("App");
           try {switchToAuthent(); }
           catch (IOException e) {
                throw new RuntimeException(e);
            }



        });

        VoirPlanning.setOnMouseClicked(actionEvent -> {

            try {showToVoirPlan(); }
            catch (IOException e) {
                throw new RuntimeException(e);
            }


        });










    }


    public void showAjouterPlanning() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/Plannification/AjouterPlanning.fxml"));
        loader.setControllerFactory(obj-> new AjouterPlanningContro(this.utilisateur));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage() ;
        stage.setScene(scene);
        stage.show();


    }
    public void showAjouterTache() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/plannification/AjouterTach.fxml"));
        loader.setControllerFactory(obj-> new AjouterTache(this.utilisateur));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage() ;
        stage.setScene(scene);
        stage.show();


    }

    public void switchToAuthent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/authentification/Authentification.fxml"));
        loader.setControllerFactory(obj-> new AuthentificationConto() );
        Stage stage = (Stage) deconnexion.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }

    public void showToVoirPlan () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/Planning/Planing.fxml"));
        loader.setControllerFactory(obj -> new Plannin(this.utilisateur));
        Stage stage = new Stage() ;
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

    }


    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }


}








