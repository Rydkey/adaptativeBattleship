package com.battleship.communication;

import java.io.*;
import java.net.Socket;

public class InterConnection extends Thread
{
  private Socket socket;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private BufferedReader reader;

  public InterConnection() { }

  public InterConnection(Socket socket) throws IOException
  {
    this.socket = socket;
    out = new ObjectOutputStream(socket.getOutputStream());
    in = new ObjectInputStream(socket.getInputStream());
    reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public void run()
  {
    Thread emission = new Thread(new Emission(out,reader));
    Thread recepetion = new Thread(new Reception(in));
    emission.setDaemon(true);
    recepetion.setDaemon(true);
    emission.start();
    recepetion.start();
  }
}
