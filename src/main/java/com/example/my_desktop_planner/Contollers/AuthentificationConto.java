package com.example.my_desktop_planner.Contollers;

import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.SerUtilisateur;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthentificationConto implements Initializable {

    @FXML
    private Button signup;
    @FXML
    private TextField pseudo;

    @FXML
    private Text error1;

    private Utilisateur utilisateur;
    private App app ;
    Stage stage ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        App app = new App() ;



        pseudo.setText(null) ;


        signup.setOnMouseClicked(actionEvent -> {

            if (pseudo.getText() != null) {


                utilisateur = app.signUp(pseudo.getText());

                if (utilisateur == null) {
                    error1.setVisible(true);
                } else {


                    System.out.println(pseudo.getText());

                    try {

                        switchToHomePage();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }

            }




        });

        signup.setOnMouseClicked(actionEvent -> {

            if (pseudo.getText() != null) {


                utilisateur = app.signUp(pseudo.getText());

                if (utilisateur == null) {
                    error1.setVisible(true);
                } else {


                    System.out.println(pseudo.getText());

                    try {
                        switchToHomePage();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                }

            }




        });


    }


    public void switchToHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/my_desktop_planner/HomePage/HomePge.fxml"));
        loader.setControllerFactory(obj-> new HomePageContro(this.utilisateur));
        Stage stage = (Stage) signup.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);

    }



}
