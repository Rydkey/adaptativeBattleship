package com.battleship.exceptions;

public class CantShotException extends Exception
{
  public CantShotException()
  {
    System.out.println("Vous ne pouvez pas tirer");
  }
}
