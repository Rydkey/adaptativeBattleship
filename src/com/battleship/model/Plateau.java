package com.battleship.model;

import java.util.Collections;
import java.util.List;

public class Plateau
{
  private Case[][] lesCases;

  public Plateau()
  {
    this.lesCases = new Case[8][8];
  }

  public Case[][] getLesCases()
  {
    return lesCases;
  }

  public void setLesCases(Case[][] lesCases)
  {
    this.lesCases = lesCases;
  }
}
