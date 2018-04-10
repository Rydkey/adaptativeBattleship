package com.battleship.extensions;

public class UnauthorizedActionException extends Exception
{
  public UnauthorizedActionException()
  {
    System.out.println("Vous n'êtes pas autorisé à faire cette action.");
  }
}
