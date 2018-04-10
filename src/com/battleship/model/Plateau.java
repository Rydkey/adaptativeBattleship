package com.battleship.model;

import java.util.Collections;
import java.util.List;

public class Plateau
{
  private List<Case> lesCases;

  public Plateau()
  {
    this.lesCases = Collections.emptyList();
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
