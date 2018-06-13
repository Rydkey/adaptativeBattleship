package com.battleship.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public abstract class Navire implements NavireInterface
{
  private List<Case> caseOccupees;
  private int taille;
  private boolean touche;
  private boolean coule;
  private Timestamp recharge;
  private boolean pretATirer;

  public Navire()
  {
    this.caseOccupees = new ArrayList<>();
    this.touche = false;
    this.coule = false;
    this.pretATirer = true;
    this.recharge = new Timestamp(System.currentTimeMillis());
  }

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

  public boolean isPretATirer()
  {
    return pretATirer;
  }

  public void setPretATirer(boolean pretATirer)
  {
    this.pretATirer = pretATirer;
  }

  public int getTaille() {
    return taille;
  }

  public void setTaille(int taille) {
    this.taille = taille;
  }

  public Timestamp getRecharge()
  {
    return recharge;
  }

  public void setRecharge(Timestamp recharge)
  {
    this.recharge = recharge;
  }

  @Override
  public void deplace(int[] vecteur)
  {
    for (Case currentCase : caseOccupees) {
      currentCase.setX(vecteur[0]);
      currentCase.setY(vecteur[1]);
    }
  }

  @Override
  public void tire()
  {

  }
}
