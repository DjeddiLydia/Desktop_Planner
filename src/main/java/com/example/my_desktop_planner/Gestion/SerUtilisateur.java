package com.example.my_desktop_planner.Gestion;

import java.io.Serializable;

public class SerUtilisateur implements Serializable {


    private String pseudo ;


    public SerUtilisateur(String pseu )
    { this.pseudo=pseu ;
  }



    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


}
