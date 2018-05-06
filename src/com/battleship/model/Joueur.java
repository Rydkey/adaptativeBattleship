package com.battleship.model;

public abstract class Joueur
{
  private String Name;

  public Joueur()
  {
  }

  public Joueur(String name)
  {
    Name = name;
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
