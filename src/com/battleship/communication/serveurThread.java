package com.battleship.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;

public class serveurThread extends Thread
{
  private ServerSocket socketserver;
  private Socket socket;
  private BufferedReader in;
  private ObjectInputStream oisReq;
  private ObjectOutputStream oosReq;

  serveurThread(ServerSocket s) throws IOException, ClassNotFoundException
  {
    socketserver = s;
    socket = socketserver.accept();
    oisReq = new ObjectInputStream(socket.getInputStream());
    oosReq = new ObjectOutputStream(socket.getOutputStream());
  }

  public void run()
  {
    System.out.println("un client veut de se connecter.");
    String name = null;
    try {
      name = oisReq.readObject().toString();
      System.out.println(name + " s'est connecté.");
      while (true) {
        String message = null;
        try {
          message = oisReq.readObject().toString();
        } catch (IOException | ClassNotFoundException e) {
          e.printStackTrace();
        }
        System.out.println('(' + new java.util.Date().toString() + ") | " + name + " : " + message);
      }
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}
