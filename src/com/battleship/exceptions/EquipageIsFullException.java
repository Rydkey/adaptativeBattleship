package com.battleship.exceptions;

public class EquipageIsFullException extends Exception
{
  public EquipageIsFullException()
  {
    System.out.println("Cet équipage est pleins");
  }
}
