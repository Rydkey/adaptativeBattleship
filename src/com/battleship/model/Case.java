package com.battleship.model;

public class Case
{
  private int x;
  private int y;
  private Status status;

  public Case()
  {
  }

  public Case(int x, int y, Status status)
  {
    this.x = x;
    this.y = y;
    this.status = status;
  }

  public int getX()
  {
    return x;
  }

  public void setX(int x)
  {
    this.x = x;
  }

  public int getY()
  {
    return y;
  }

  public void setY(int y)
  {
    this.y = y;
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
