package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Planning;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class AjouterPlanningContro implements Initializable {

    private Utilisateur user;
    @FXML
    private DatePicker dateDebut, dateFin;
    @FXML
    private Button Ajouter;
    @FXML
    private Text error;


    public AjouterPlanningContro(Utilisateur u) {
        this.user = u;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dateDebut.setValue(null);
        dateFin.setValue(null);


        Ajouter.setOnMouseClicked(actionEvent -> {

            if (dateDebut.getValue() != null & dateFin.getValue() != null) {

                if (Planning.verifDateDebut(dateDebut.getValue()) & Planning.verifDateFin(dateDebut.getValue(), dateFin.getValue())) {
                    user.ajouterPlanning(dateDebut.getValue(), dateFin.getValue());
                    try {
                        switchToAjoutPlan();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                } else {
                    error.setVisible(true);
                }

            } else {
                error.setVisible(true);
            }


        });



    }

    public void switchToAjoutPlan () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/Plannification/AjoutCreneau.fxml"));
        loader.setControllerFactory(obj -> new AjoutCreneau(this.user));
        Stage stage = (Stage) Ajouter.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }




}
