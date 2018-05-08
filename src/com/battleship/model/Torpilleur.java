package com.battleship.model;

import com.battleship.model.Navire;

public class Torpilleur extends Navire
{
  public Torpilleur(){
    super();
    setTaille(2);
  }

  @Override
  public void deplace(int[] vecteur) {}

  @Override
  public void tire() {}
}
