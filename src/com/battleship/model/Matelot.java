package com.battleship.model;

import java.util.List;

public class Matelot extends Joueur
{
  public Matelot()
  {
    super();
  }

  public Matelot(List<Navire> naviresAssignes, Equipe equipe)
  {
    super(equipe);
  }

}
