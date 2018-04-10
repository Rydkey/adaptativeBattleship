package com.battleship.model;

import java.util.List;

public class Plateau
{
  private List<Case> lesCases;

  public Plateau()
  {
  }

  public Plateau(List<Case> lesCases)
  {
    this.lesCases = lesCases;
  }

  public List<Case> getLesCases()
  {
    return lesCases;
  }

  public void setLesCases(List<Case> lesCases)
  {
    this.lesCases = lesCases;
  }
}
