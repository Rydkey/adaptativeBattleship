package com.battleship.model;

public class Matelot extends Joueur
{
  public Matelot()
  {
    super();
  }

  public Matelot(String name){
    super(name);
  }

  @Override
  public String toString()
  {
    return getName();
  }
}
