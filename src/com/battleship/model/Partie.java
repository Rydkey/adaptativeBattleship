package com.battleship.model;

import java.util.HashMap;
import java.util.List;

public class Partie
{
  private HashMap<Equipe,Plateau> plateaux;
  private List<Joueur> joueurs;

  public Partie()
  {
  }

  public HashMap<Equipe, Plateau> getPlateaux()
  {
    return plateaux;
  }

  public void setPlateaux(HashMap<Equipe, Plateau> plateaux)
  {
    this.plateaux = plateaux;
  }

  public List<Joueur> getJoueurs()
  {
    return joueurs;
  }

  public void setJoueurs(List<Joueur> joueurs)
  {
    this.joueurs = joueurs;
  }
}
