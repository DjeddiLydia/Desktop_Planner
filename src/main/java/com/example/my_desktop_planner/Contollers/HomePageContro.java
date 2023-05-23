package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.SerUtilisateur;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageContro implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane anchorPane;

    private Utilisateur utilisateur ;
    private App app ;

    public HomePageContro(Utilisateur utilisateur){
        this.utilisateur=utilisateur;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //DatePicker datePicker = new DatePicker(LocalDate.now());


        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);

        Node popupContent = datePickerSkin.getPopupContent();
        anchorPane.getChildren().add(popupContent) ;
        popupContent.setLayoutX(20);
        popupContent.setLayoutY(100) ;

        VBox innerBoxContainer = new VBox();
        innerBoxContainer.setSpacing(10);
        innerBoxContainer.setPadding(new Insets(10));

        // Create inner boxes
        for (int i = 1; i <= 10; i++) {
            Pane innerBox = new Pane();
            innerBox.setPrefSize(300, 50);
            innerBox.setStyle("-fx-background-color: #DDDDDD;");
            innerBoxContainer.getChildren().add(innerBox);
        }

        // Set VBox as the content of ScrollPane
        scrollPane.setContent(innerBoxContainer);




    }


    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }


}








