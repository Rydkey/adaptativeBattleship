package com.battleship.model;

import java.util.HashMap;

public class Amiral extends Joueur
{
  HashMap<Navire, Equipage> tableauJoueurs;

  public Amiral()
  {
    super();
    this.tableauJoueurs = new HashMap<>();
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
