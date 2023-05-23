package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Planning;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import java.util.Set;

public class AjoutCreneau implements Initializable {

    @FXML
    private DatePicker date1;
    @FXML
    private DatePicker date2;
    @FXML
    private DatePicker date3;


    @FXML
    private Button Valider;
    @FXML
    private MenuButton H1, H2, MIN1, MIN2;

    @FXML
    private RadioButton radio1, radio2;

    @FXML
    private Text errorDate, errorTime;

    private LocalTime timeDebut, timeFin;
    private Utilisateur utilisateur ;

    public AjoutCreneau (Utilisateur user) {this.utilisateur = user ;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        date1.setValue(null);
        date2.setValue(null);
        date3.setValue(null);
        date1.setDisable(true);
        date2.setDisable(true);
        date3.setDisable(true);
        Valider.setDisable(true);


        for (int hour = 0; hour < 24; hour++) {
            String hourText = String.format("%02d", hour);

            MenuItem menuItem = new MenuItem(hourText);
            menuItem.setOnAction(event -> {
                H1.setText(hourText); // Set the selected font as the text of the MenuButton

            });

            H1.getItems().add(menuItem);
        }

        for (int hour = 0; hour < 24; hour++) {
            String hourText = String.format("%02d", hour);

            MenuItem menuItem = new MenuItem(hourText);
            menuItem.setOnAction(event -> {
                H2.setText(hourText); // Set the selected font as the text of the MenuButton

            });

            H2.getItems().add(menuItem);
        }

        for (int min = 0; min < 60; min++) {
            String mintext = String.format("%02d", min);

            MenuItem menuItem = new MenuItem(mintext);
            menuItem.setOnAction(event -> {
                MIN1.setText(mintext); // Set the selected font as the text of the MenuButton

            });

            MIN1.getItems().add(menuItem);
        }

        for (int min = 0; min < 60; min++) {
            String mintext = String.format("%02d", min);

            MenuItem menuItem = new MenuItem(mintext);
            menuItem.setOnAction(event -> {
                MIN2.setText(mintext); // Set the selected font as the text of the MenuButton

            });

            MIN2.getItems().add(menuItem);
        }


        radio1.setOnMouseClicked(actionEvent -> {
            radio2.setSelected(false);

            if (radio1.isSelected()) {
                Valider.setDisable(false);
                date3.setDisable(true);
                date1.setDisable(false);
                date2.setDisable(false);

            } else {
                Valider.setDisable(true);

                date1.setDisable(true);
                date2.setDisable(true);

            }
        });


        radio2.setOnMouseClicked(actionEvent -> {
            radio1.setSelected(false);

            if (radio2.isSelected()) {
                Valider.setDisable(false);
                date1.setDisable(true);
                date2.setDisable(true);
                date3.setDisable(false);
            } else {
                Valider.setDisable(true);
                date3.setDisable(true);
            }
        });

        Valider.setOnMouseClicked(actionEvent -> {

            if (radio1.isSelected()) {
                if (date1.getValue() == null || date2.getValue() == null) {
                    errorDate.setVisible(true);
                }


                try {
                    timeDebut = convertToTime(H1.getText(), MIN1.getText());
                    timeFin = convertToTime(H2.getText(), MIN2.getText());

                } catch (NumberFormatException e) {
                    errorTime.setVisible(true);
                }


            }


        });


    }


    public LocalTime convertToTime(String h, String min) {

        return LocalTime.of(Integer.parseInt(h), Integer.parseInt(min));
    }

}





