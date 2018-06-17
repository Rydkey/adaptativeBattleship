package com.battleship.model;

public class Partie
{
  private boolean enCours;
  private Equipe[] equipes;
  private Plateau[] plateaux;

  public Partie()
  {
    this.equipes = new Equipe[2];
    this.plateaux = new Plateau[2];
  }

  public Equipe[] getEquipes()
  {
    return equipes;
  }

  public void setEquipes(Equipe[] equipes)
  {
    this.equipes = equipes;
  }

  public Plateau[] getPlateaux()
  {
    return plateaux;
  }

  public void setPlateaux(Plateau[] plateaux)
  {
    this.plateaux = plateaux;
  }

  public boolean isEnCours()
  {
    return enCours;
  }

  public void setEnCours(boolean enCours)
  {
    this.enCours = enCours;
  }

}
