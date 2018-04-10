package com.battleship.model;

public class Equipage
{
  Attaquant attaquant;
  Defenseur defenseur;

  public Equipage()
  {
  }

  public Equipage(Attaquant attaquant, Defenseur defenseur)
  {
    this.attaquant = attaquant;
    this.defenseur = defenseur;
  }

  public Attaquant getAttaquant()
  {
    return attaquant;
  }

  public void setAttaquant(Attaquant attaquant)
  {
    this.attaquant = attaquant;
  }

  public Defenseur getDefenseur()
  {
    return defenseur;
  }

  public void setDefenseur(Defenseur defenseur)
  {
    this.defenseur = defenseur;
  }

}
