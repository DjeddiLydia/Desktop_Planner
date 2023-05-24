package com.example.my_desktop_planner.Contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AjouterTache implements Initializable {

    @FXML
    private RadioButton simple  , decomp ;

    @FXML
    private MenuButton H , MIN;

    @FXML
    private Button Ajouter ;

    @FXML
    private TextField nom ;
    @FXML
    private Text ErrorNom , ErrorDate , ErrorDurée , ErrorType ;
    @FXML
    private DatePicker deadline ;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        nom.setText(null);
        H.setText("H");
        MIN.setText("min");

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

        Ajouter.setOnMouseClicked(actionEvent -> {

            if (nom.getText()==null )
            { ErrorNom.setVisible(true); }
            else {ErrorNom.setVisible(false); }

            if (H.getText().equals("H") || MIN.getText().equals("min") )
            {ErrorDurée.setVisible(true); }
            else { ErrorDurée.setVisible(false);  }

            if (deadline.getValue()==null)
            { ErrorDate.setVisible(true);}
            else {ErrorDate.setVisible(false); }

            if (!simple.isSelected() & !decomp.isSelected() )
            { ErrorType.setVisible(true);}
            else {ErrorType.setVisible(false); }
            if (  !( ErrorType.isVisible()&ErrorDurée.isVisible() & ErrorDate.isVisible() & ErrorNom.isVisible() )  )

            { }







        });



    }
}
