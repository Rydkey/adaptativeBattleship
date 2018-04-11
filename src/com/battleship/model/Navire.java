package com.battleship.model;

import java.util.List;

public abstract class Navire implements NavireInterface
{
  private List<Case> caseOccupees;
  private boolean touche;
  private boolean coule;
  private int recharge;
  private boolean pret;
  private String name;

  public Navire() {}

  public List<Case> getCaseOccupees()
  {
    return caseOccupees;
  }

  public void setCaseOccupees(List<Case> caseOccupees)
  {
    this.caseOccupees = caseOccupees;
  }

  public boolean isTouche()
  {
    return touche;
  }

  public void setTouche(boolean touche)
  {
    this.touche = touche;
  }

  public boolean isCoule()
  {
    return coule;
  }

  public void setCoule(boolean coule)
  {
    this.coule = coule;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean isPret()
  {
    return pret;
  }

  public void setPret(boolean pret)
  {
    this.pret = pret;
  }

  @Override
  public void deplace(int[] vecteur)
  {
    for (Case currentCase: caseOccupees) {
      currentCase.setX(vecteur[0]);
      currentCase.setY(vecteur[1]);
    }
  }

  @Override
  public void tire()
  {
    /*@Todo: lancer timer*/
  }
}
