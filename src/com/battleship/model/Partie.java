package com.battleship.model;

import java.util.HashMap;
import java.util.List;

public class Partie
{
  private HashMap<Equipe,Plateau> plateaux;

  public Partie()
  {
    this.plateaux = new HashMap<>();
  }

  public HashMap<Equipe, Plateau> getPlateaux()
  {
    return plateaux;
  }

  public void setPlateaux(HashMap<Equipe, Plateau> plateaux)
  {
    this.plateaux = plateaux;
  }

}
