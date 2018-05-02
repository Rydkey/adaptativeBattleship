package com.battleship.model;

import java.util.Collections;
import java.util.List;

public abstract class Joueur {
    private String Name;

    public Joueur() {
    }

    public Joueur(Equipe equipe) {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
