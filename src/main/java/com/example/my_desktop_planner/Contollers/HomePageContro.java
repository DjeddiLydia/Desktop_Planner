package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Box;
import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.SerUtilisateur;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Taches_Prj.Catégorie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class HomePageContro implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ScrollPane scrollPane , scroll1;
    @FXML
    private Button ajouterPlanning , AjouterTache , deconnexion , VoirPlanning , plus ,Ajouter ;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane Categ;

    private Utilisateur utilisateur ;
    private App app ;
    @FXML
    private ColorPicker colorPicker ;
    @FXML
    private TextField nom ;
    @FXML
    private Label user ;
    private Stage stage ;

    private VBox vbox2 = new VBox();


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

        user.setText(utilisateur.getPseudo());


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
        chargerscroll();

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

        plus.setOnMouseClicked(actionEvent -> {

          if(!Categ.isVisible())
          { Categ.setVisible(true);}

          else{Categ.setVisible(false); }


        });

        Ajouter.setOnMouseClicked(actionEvent -> {


         if (nom.getText()!=null)
         {
             Catégorie c = new Catégorie(nom.getText() , colorPicker.getValue() ) ;
             utilisateur.ajouterCatégorie(c);
             System.out.println(c.getName());
             deletescroll();
             chargerscroll();



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
        stage = new Stage() ;
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

    public String convertToHexa (Color c ) {
        String hexString = String.format("#%02X%02X%02X", (int)(c.getRed() * 255),
                (int)(c.getGreen() * 255), (int)(c.getBlue() * 255));
        return hexString ;
    }

    public void deletescroll ()
    { vbox2.getChildren().clear();}

    public void chargerscroll ()
    {
        vbox2.setSpacing(10);
        for (Catégorie categ : utilisateur.getCatégories()) {

            VBox mini = new VBox() ;
            mini.setPrefWidth(200);
            mini.setPrefHeight(80);

            //mini.setStyle("-fx-background-color: "+ convertToHexa(categ.getColor())+";");
            //mini.setStyle("-fx-background-color: #DDDDDD;");
            String hexColor = convertToHexa(categ.getColor()) ;
            mini.setStyle("-fx-background-color: " + hexColor + ";");
            //mini.setStyle("-fx-border-color:  #845EF1;");
            Label nom = new Label(categ.getName()) ;
            mini.getChildren().add(nom ) ;
            vbox2.getChildren().add(mini) ;
        }
        scroll1.setContent(vbox2) ;

    }


}








