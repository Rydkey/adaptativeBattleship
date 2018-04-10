package com.battleship.model;

import java.util.List;

public class Defenseur extends Matelot
{
  public Defenseur()
  {
  }

  public Defenseur(List<Navire> naviresAssignes, Equipe equipe)
  {
    super(naviresAssignes, equipe);
  }
}
