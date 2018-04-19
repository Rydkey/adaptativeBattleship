package com.battleship.model;

import java.util.Collections;
import java.util.List;

public class Plateau
{
  private Case[][] lesCases;

  public Plateau()
  {
    this.lesCases = new Case[10][10];
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
