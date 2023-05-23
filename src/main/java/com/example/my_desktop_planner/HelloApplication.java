package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.SerUtilisateur;
import com.example.my_desktop_planner.Planification.Creneau;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       /* LocalTime d = LocalTime.of(16 , 50) ;
        LocalTime f = LocalTime.of(18 , 30) ;

        LocalTime d2 = LocalTime.of(11 , 30) ;
        LocalTime f2 = LocalTime.of(16 , 50) ;



        Creneau cre = new Creneau(d , f);
        Creneau cre2 = new Creneau(d2 , f2);

        System.out.println(cre2.equals(cre));

       /* App app = new App() ;
        Utilisateur user1 = new Utilisateur("Rosa" , "123" ) ;
        Utilisateur user2 = new Utilisateur("Lola" , "hi" ) ;
        Utilisateur user3 = new Utilisateur("Rosa" , "123lij" ) ;
        app.addUser(user1) ;
        app.addUser(user2) ;
        app.addUser(user3);

        for (Utilisateur u : app.getUsers())
        {System.out.println("Pseudo " + u.getPseudo()); }*/

        //App app = new App() ;

       // App app = App.loadFromFile("App");
        // System.out.println("utilisateur ajouté   " + app.signUp("rosa" ,"12sd3") ) ;
        //System.out.println("utilisateur ajouté   " + app.signUp("kjsdbkjb" ,"123") ) ;
        //System.out.println("utilisateur ajouté   " + app.signUp("kjb" ,"123") ) ;
       /* for (SerUtilisateur u : app.getUsers())
        {System.out.println("Pseudo " + u.getPseudo()); }

        app.saveToFile("App");*/



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authentification/Authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}