package com.battleship.model;

import java.util.HashMap;

public class Equipe
{
  HashMap<Navire, Equipage> tableauJoueurs;
  private String name;

  public Equipe()
  {
    this.tableauJoueurs = new HashMap<>();
  }

  public Equipe(String name)
  {
    this.name = name;
    this.tableauJoueurs = new HashMap<>();

  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public HashMap<Navire, Equipage> getTableauJoueurs()
  {
    return tableauJoueurs;
  }

  public void setTableauJoueurs(HashMap<Navire, Equipage> tableauJoueurs)
  {
    this.tableauJoueurs = tableauJoueurs;
  }
}
