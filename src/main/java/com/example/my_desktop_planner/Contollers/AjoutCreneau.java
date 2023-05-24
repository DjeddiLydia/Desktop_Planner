package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Planification.Planning;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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
    private Button Valider , VoirPlanning;
    @FXML
    private MenuButton H1, H2, MIN1, MIN2 ,H3 , MIN3;

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

        for (int hour = 0; hour < 24; hour++) {
            String hourText = String.format("%02d", hour);

            MenuItem menuItem = new MenuItem(hourText);
            menuItem.setOnAction(event -> {
                H3.setText(hourText); // Set the selected font as the text of the MenuButton

            });

            H3.getItems().add(menuItem);
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
        for (int min = 0; min < 60; min++) {
            String mintext = String.format("%02d", min);

            MenuItem menuItem = new MenuItem(mintext);
            menuItem.setOnAction(event -> {
                MIN3.setText(mintext); // Set the selected font as the text of the MenuButton

            });

            MIN3.getItems().add(menuItem);
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

            if ( !(errorTime.isVisible() & errorDate.isVisible()) )

            {




            if(radio2.isSelected())
            {
                LocalDate date = date3.getValue() ;
                if (!date.isBefore(utilisateur.getPlanning().getDebut()) & !date.isAfter(utilisateur.getPlanning().getFin()))
                {  System.out.println("crenau ajouter");
                    Creneau creneau = new Creneau(convertToTime(H1.getText() , MIN1.getText()) , convertToTime(H2.getText() , MIN2.getText()) , convertToDuree(H3.getText() , MIN3.getText() ) , date ) ;
                    utilisateur.ajoutercreneau(date , creneau);
                    System.out.println("crenau ajouter");

                }



            }}

            if(radio1.isSelected())
            {
                LocalDate d1 = date1.getValue() ;
                LocalDate d2 = date2.getValue() ;

                    Creneau creneau = new Creneau(convertToTime(H1.getText() , MIN1.getText()) , convertToTime(H2.getText() , MIN2.getText()) , convertToDuree(H3.getText() , MIN3.getText() )  ) ;

                    utilisateur.ajouterCreneauPeriode(date1.getValue() , date2.getValue() , creneau);
                    System.out.println("crenau ajouter");





            }


        });

        VoirPlanning.setOnMouseClicked(actionEvent -> {
            try {switchToVoirPlan(); }
        catch (IOException e) {
            throw new RuntimeException(e);
        }



        });





    }


    public LocalTime convertToTime(String h, String min) {

        return LocalTime.of(Integer.parseInt(h), Integer.parseInt(min));
    }

    public Duration convertToDuree(String h, String min) {
        int H = Integer.parseInt(h) ;
        int M = Integer.parseInt(min) ;


        return Duration.ofHours(H).plus(Duration.ofMinutes(M)) ;
    }

    public void switchToVoirPlan () throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/Planning/Planing.fxml"));
        loader.setControllerFactory(obj -> new Plannin(this.utilisateur));
        Stage stage = (Stage) VoirPlanning.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }



}





