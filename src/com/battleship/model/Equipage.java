package com.battleship.model;

import java.util.LinkedList;
import java.util.List;

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

  public List<Matelot> getAllEquipage()
  {
    List<Matelot> equipage = new LinkedList<>();
    equipage.add(this.attaquant);
    equipage.add(this.attaquant);
    return equipage;
  }

}
