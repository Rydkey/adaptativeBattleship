package com.battleship.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Emission implements Runnable
{
  private ObjectOutputStream out;
  private BufferedReader bufferedReader;

  public Emission() { }

  public Emission(ObjectOutputStream out, BufferedReader bufferedReader)
  {
    this.out = out;
    this.bufferedReader = bufferedReader;
  }

  @Override
  public void run()
  {
    while (true) {
      try {
        out.writeObject(bufferedReader.readLine());
        out.flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
