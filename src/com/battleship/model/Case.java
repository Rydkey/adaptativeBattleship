package com.battleship.model;

public class Case
{
  private int[] coordonnees;
  private Status status;

  public Case()
  {
  }

  public Case(int[] coordonnees, Status status)
  {
    this.coordonnees = coordonnees;
    this.status = status;
  }

  public int[] getCoordonnees()
  {
    return coordonnees;
  }

  public void setCoordonnees(int[] coordonnees)
  {
    this.coordonnees = coordonnees;
  }

  public Status getStatus()
  {
    return status;
  }

  public void setStatus(Status status)
  {
    this.status = status;
  }
}
