package com.example.my_desktop_planner.Gestion;

import com.example.my_desktop_planner.Planification.Planning;
import com.example.my_desktop_planner.Taches_Prj.Tache;
import com.example.my_desktop_planner.Taches_Prj.TacheSimple;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class App implements Serializable {

    private List<Utilisateur> users ;

    public App() {
        users = new ArrayList<Utilisateur>();
    }

    public  List<Utilisateur> getUsers() {
        return users;
    }

    public boolean addUser(Utilisateur newUser) {

        if (this.containsUser(newUser.getPseudo())) return false;

        else {
            users.add(newUser);
            return true;
        }
    }

    public boolean containsUser(String pseudo) {
        for (Utilisateur user : users) {
            if (user.getPseudo().equals(pseudo)) {
                return true;
            }
        }
        return false;
    }

    public void saveToFile(String fileName) {
        String desktopPath = System.getProperty("user.home") + "/Desktop";
        Path filePath = Paths.get(desktopPath, fileName);
        try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile());
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(this);
            System.out.println("App information saved to file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static App loadFromFile(String fileName) {
        App app = null;
        String desktopPath = System.getProperty("user.home") + "/Desktop";
        Path filePath = Paths.get(desktopPath, fileName);
        try (FileInputStream fileIn = new FileInputStream(filePath.toFile());

             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            app = (App) objectIn.readObject();
            System.out.println("App information loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return app;
    }

    public Utilisateur signUp(String pseudo) {
        Utilisateur user = new Utilisateur(pseudo);
        boolean b = this.addUser(user) ;
        if (b==true)return user ;
        else return null ;
    }

    public Utilisateur logIn(String pseudo) {
        for (Utilisateur user : users) {
            if (user.getPseudo().equals(pseudo)) {
               return user ;
            }
        }
     return null ;
    }

}
