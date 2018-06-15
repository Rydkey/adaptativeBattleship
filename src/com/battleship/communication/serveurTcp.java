package com.battleship.communication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class serveurTcp
{

  public static void main(String[] zero)
  {
    ServerSocket socket;
    try {
      socket = new ServerSocket(2009);
      Thread t = new Thread(new serveurThread(socket));
      System.out.println("Mes employeurs sont prêts !");
//      t.setDaemon(true);
      t.start();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }

  }
}
