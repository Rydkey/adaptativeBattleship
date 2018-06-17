package com.battleship.communication;

import java.io.IOException;
import java.net.Socket;

public class Client
{
  public static void main(String[] args) throws IOException
  {
    Socket socket = new Socket("localhost", 2018);
    InterConnection interConnection = new InterConnection(socket, "client");
    interConnection.start();
    while (socket.isConnected()) ;
  }
}
