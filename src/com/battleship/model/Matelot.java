package com.battleship.model;

import java.util.ArrayList;
import java.util.List;

public class Matelot extends Joueur
{

  private List<Navire> naviresAssignes;

  public Matelot()
  {
    super();
    this.naviresAssignes = new ArrayList<>();
  }

  public Matelot(List<Navire> naviresAssignes, Equipe equipe)
  {
    super(equipe);
    this.naviresAssignes = naviresAssignes;
  }

  public List<Navire> getNaviresAssignes()
  {
    return naviresAssignes;
  }

  public void setNaviresAssignes(List<Navire> naviresAssignes)
  {
    this.naviresAssignes = naviresAssignes;
  }
}
