package com.battleship.exceptions;

public class CantMoveException extends Exception
{
  public CantMoveException()
  {
    System.out.println("Vous ne pouvez pas bouger");
  }
}
