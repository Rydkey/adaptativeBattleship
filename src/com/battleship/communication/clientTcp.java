package com.battleship.communication;

import java.io.*;
import java.net.Socket;


public class clientTcp
{

  public static void main(String[] zero)
  {
    Socket socket;
    ObjectInputStream oisReq;
    ObjectOutputStream oosReq;
    BufferedReader consoleIn; // flux de lecture lignes depuis clavier

    try {
      socket = new Socket("localhost", 2009);
      consoleIn = new BufferedReader(new InputStreamReader(System.in));
      oosReq = new ObjectOutputStream(socket.getOutputStream());
      oisReq = new ObjectInputStream(socket.getInputStream());
      System.out.println("connecter, entrez un pseudo : ");
      String name = consoleIn.readLine();
      oosReq.writeObject(name);
      oosReq.flush();
      while (socket.isConnected()){
        String s = consoleIn.readLine();
        System.out.println("vous : "+ s);
        oosReq.writeObject(s);
        oosReq.flush();
      }
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
