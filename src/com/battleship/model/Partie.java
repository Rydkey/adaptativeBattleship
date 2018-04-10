package com.battleship.model;

import java.util.List;

public class Partie
{
  private Plateau[] plateau;
  private List<Joueur> joueurs;

  public Partie()
  {
  }

  public Partie(Plateau[] plateau, List<Joueur> joueurs)
  {
    this.plateau = plateau;
    this.joueurs = joueurs;
  }

  public Plateau[] getPlateau()
  {
    return plateau;
  }

  public void setPlateau(Plateau[] plateau)
  {
    this.plateau = plateau;
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
