package com.battleship.extensions;

public class CantShootException extends Exception
{
  public CantShootException()
  {
    System.out.println("Vous ne pouvez pas tirer");
  }
}
