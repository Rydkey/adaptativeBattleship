package com.battleship.model;

import java.util.List;

abstract class Joueur
{
  private List<Navire> naviresAssignes;
  private Equipe equipe;
  private String Name;

  public Joueur()
  {
  }

  public Joueur(List<Navire> naviresAssignes, Equipe equipe)
  {
    this.naviresAssignes = naviresAssignes;
    this.equipe = equipe;
  }

  public List<Navire> getNaviresAssignes()
  {
    return naviresAssignes;
  }

  public void setNaviresAssignes(List<Navire> naviresAssignes)
  {
    this.naviresAssignes = naviresAssignes;
  }

  public Equipe getEquipe()
  {
    return equipe;
  }

  public void setEquipe(Equipe equipe)
  {
    this.equipe = equipe;
  }

  public String getName()
  {
    return Name;
  }

  public void setName(String name)
  {
    Name = name;
  }
}
