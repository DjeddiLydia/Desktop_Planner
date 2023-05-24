package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import com.example.my_desktop_planner.Taches_Prj.TacheDecomposable;
import com.example.my_desktop_planner.Taches_Prj.TacheSimple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AjouterTache implements Initializable {

    public AjouterTache(Utilisateur user)
    { this.utilisateur = user ; }

    @FXML
    private RadioButton simple  , decomp ;

    @FXML
    private MenuButton H , MIN , H1 , MIN1 , priorité;

    @FXML
    private Button Ajouter ;

    @FXML
    private TextField nom , periodicité;
    @FXML
    private Text ErrorNom , ErrorDate , ErrorDurée , ErrorType  , ErrorHeure;
    @FXML
    private DatePicker deadline ;
    private Utilisateur utilisateur ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nom.setText(null);
        H.setText("H");
        MIN.setText("min");
        H1.setText("H");
        MIN1.setText("min");
        periodicité.setText("0");




        MenuItem menuItem1 = new MenuItem("High");
        MenuItem menuItem2 = new MenuItem("Medium");
        MenuItem menuItem3 = new MenuItem("Low");


        priorité.getItems().addAll(menuItem1 , menuItem2 , menuItem3);


        menuItem1.setOnAction(event -> {
            priorité.setText("High"); // Set the selected font as the text of the MenuButton

        });
        menuItem2.setOnAction(event -> {
            priorité.setText("Medium"); // Set the selected font as the text of the MenuButton

        });
        menuItem3.setOnAction(event -> {
            priorité.setText("Low"); // Set the selected font as the text of the MenuButton

        });


        simple.setOnMouseClicked(actionEvent -> {

            if (simple.isSelected()==true)
            { decomp.setSelected(false);
            }
        });

        decomp.setOnMouseClicked(actionEvent -> {

            if (decomp.isSelected()==true)
            { simple.setSelected(false);
            }

        });

        for (int hour = 0; hour < 24; hour++) {
            String hourText = String.format("%02d", hour);

            MenuItem menuItem = new MenuItem(hourText);
            menuItem.setOnAction(event -> {
                H.setText(hourText); // Set the selected font as the text of the MenuButton

            });

            H.getItems().add(menuItem);
        }

        for (int min = 0; min < 60; min++) {
            String mintext = String.format("%02d", min);

            MenuItem menuItem = new MenuItem(mintext);
            menuItem.setOnAction(event -> {
                MIN.setText(mintext); // Set the selected font as the text of the MenuButton

            });

            MIN.getItems().add(menuItem);
        }

        for (int hour = 0; hour < 24; hour++) {
            String hourText = String.format("%02d", hour);

            MenuItem menuItem = new MenuItem(hourText);
            menuItem.setOnAction(event -> {
                H1.setText(hourText); // Set the selected font as the text of the MenuButton

            });

            H1.getItems().add(menuItem);
        }

        for (int min = 0; min < 60; min++) {
            String mintext = String.format("%02d", min);

            MenuItem menuItem = new MenuItem(mintext);
            menuItem.setOnAction(event -> {
                MIN1.setText(mintext); // Set the selected font as the text of the MenuButton

            });

            MIN1.getItems().add(menuItem);
        }

        Ajouter.setOnMouseClicked(actionEvent -> {

            if (nom.getText()==null )
            { ErrorNom.setVisible(true); }
            else {ErrorNom.setVisible(false); }

            if (H.getText().equals("H") || MIN.getText().equals("min") )
            {ErrorDurée.setVisible(true); }
            else { ErrorDurée.setVisible(false);  }

            if (H1.getText().equals("H") || MIN1.getText().equals("min") )
            {ErrorHeure.setVisible(true); }
            else { ErrorHeure.setVisible(false);  }

            if (deadline.getValue()==null)
            { ErrorDate.setVisible(true);}
            else {ErrorDate.setVisible(false); }

            if (!simple.isSelected() & !decomp.isSelected() )
            { ErrorType.setVisible(true);}
            else {ErrorType.setVisible(false); }
            if (  !( ErrorType.isVisible()&ErrorDurée.isVisible() & ErrorDate.isVisible() & ErrorNom.isVisible() & ErrorHeure.isVisible() )  )

            {   if (simple.isSelected())
            {  System.out.println("TACHE AJOUTER");
                TacheSimple tachesimple = new TacheSimple(nom.getText() ,convertToDuree(H.getText() , MIN.getText() )  , deadline.getValue() , convertToTime(H1.getText() , MIN1.getText() ), Tache.StringToPropriete(priorité.getText()) ,Integer.parseInt(periodicité.getText()) );
                utilisateur.ajoutTache(tachesimple );
            } }

                {   if (decomp.isSelected())
                {
                    TacheDecomposable tacheDecomposable = new TacheDecomposable(nom.getText() ,convertToDuree(H.getText() , MIN.getText() )  , deadline.getValue() , convertToTime(H1.getText() , MIN1.getText() ), Tache.StringToPropriete(priorité.getText())  );
                    utilisateur.ajoutTache(tacheDecomposable );
                }


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
}
