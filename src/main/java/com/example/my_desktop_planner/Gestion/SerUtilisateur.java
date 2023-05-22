package com.example.my_desktop_planner.Gestion;

import java.io.Serializable;

public class SerUtilisateur implements Serializable {


    private String pseudo ;
    private String password ;

    public SerUtilisateur(String pseu , String pass)
    { this.pseudo=pseu ;
    this.password=pass;}



    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
