package com.battleship.exceptions;

public class UnauthorizedActionException extends Exception
{
  public UnauthorizedActionException()
  {
    System.out.println("Vous n'êtes pas autorisé à faire cette action.");
  }
}
