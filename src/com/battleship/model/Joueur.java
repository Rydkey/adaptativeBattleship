package com.battleship.model;

import java.util.Collections;
import java.util.List;

public abstract class Joueur
{
  private Equipe equipe;
  private String Name;

  public Joueur()
  {
  }

  public Joueur(Equipe equipe)
  {
    this.equipe = equipe;
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
