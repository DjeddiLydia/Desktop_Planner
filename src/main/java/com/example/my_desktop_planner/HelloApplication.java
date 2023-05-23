package com.example.my_desktop_planner;

import com.example.my_desktop_planner.Gestion.App;
import com.example.my_desktop_planner.Gestion.SerUtilisateur;
import com.example.my_desktop_planner.Gestion.Utilisateur;
import com.example.my_desktop_planner.Planification.Calendrier;
import com.example.my_desktop_planner.Planification.Creneau;
import com.example.my_desktop_planner.Planification.Journée;
import com.example.my_desktop_planner.Taches_Prj.Priorité;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import com.example.my_desktop_planner.Taches_Prj.TacheSimple;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

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



        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("authentification/Authentification.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/


        Utilisateur user1 = new Utilisateur("Rosa"  ) ;
        ArrayList<Tache> taches = new ArrayList<>();
        // Création de tâches
        TacheSimple tache1 = new TacheSimple();
        tache1.setNom("Tâche 1");
        tache1.setDurée(Duration.ofHours(2));
        tache1.setDateLimite(LocalDate.of(2023, 5, 30));
        tache1.setHeurelimite(LocalTime.of(12, 0));
        tache1.setPriorité(Priorité.Medium);

        TacheSimple tache2 = new TacheSimple();
        tache2.setNom("Tâche 2");
        tache2.setDurée(Duration.ofHours(3));
        tache2.setDateLimite(LocalDate.of(2023, 5, 25));
        tache2.setHeurelimite(LocalTime.of(17, 0));
        tache2.setPriorité(Priorité.High);

        TacheSimple tache3 = new TacheSimple();
        tache3.setNom("Tâche 3");
        tache3.setDurée(Duration.ofHours(1));
        tache3.setDateLimite(LocalDate.of(2023, 5, 25));
        tache3.setHeurelimite(LocalTime.of(15, 0));
        tache3.setPriorité(Priorité.Low);

        // Ajout des tâches à l'ArrayList
        taches.add(tache1);
        taches.add(tache2);
        taches.add(tache3);

        user1.setTaches(taches) ;

        user1.trierTaches();

        user1.affichTaches();


    }

    public static void main(String[] args) {
        launch();
    }
}