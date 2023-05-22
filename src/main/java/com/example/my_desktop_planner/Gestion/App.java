package com.example.my_desktop_planner.Gestion;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class App implements Serializable {

    private Set<SerUtilisateur> users;

    public App() {
        users = new HashSet<>();
    }

    public Set<SerUtilisateur> getUsers() {
        return users;
    }

    public boolean addUser(SerUtilisateur newUser) {

        if (this.containsUser(newUser.getPseudo())) return false;

        else {
            users.add(newUser);
            return true;
        }
    }

    public boolean containsUser(String pseudo) {
        for (SerUtilisateur user : users) {
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

    public SerUtilisateur signUp(String pseudo, String password) {
        SerUtilisateur user = new SerUtilisateur(pseudo, password);
        boolean b = this.addUser(user) ;
        if (b==true)return user ;
        else return null ;
    }

    public SerUtilisateur logIn(String pseudo, String password) {
        for (SerUtilisateur user : users) {
            if (user.getPseudo().equals(pseudo)) {
                if (user.getPassword()==password) return user ;
                else {return null ; }
            }
        }
     return null ;
    }
}
