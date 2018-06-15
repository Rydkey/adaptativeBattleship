package com.battleship.communication;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Reception implements Runnable
{
  private ObjectInputStream in;

  public Reception(ObjectInputStream in)
  {
    this.in = in;
  }

  @Override
  public void run()
  {
    while(true){
      try {
        System.out.println(in.readObject().toString());
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
