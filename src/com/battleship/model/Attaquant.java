package com.battleship.model;

import java.util.List;

public class Attaquant extends Matelot
{
  public Attaquant()
  {
  }

  public Attaquant(List<Navire> naviresAssignes, Equipe equipe)
  {
    super(naviresAssignes, equipe);
  }

}
