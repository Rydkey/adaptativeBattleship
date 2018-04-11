package com.battleship.exceptions;

public class CantShootException extends Exception
{
  public CantShootException()
  {
    System.out.println("Vous ne pouvez pas tirer");
  }
}
