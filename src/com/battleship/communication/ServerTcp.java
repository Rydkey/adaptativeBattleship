package com.battleship.communication;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerTcp
{
  private int port;
  private String ip;
  private ServerSocket serverSocket;

  public ServerTcp() { }

  public ServerTcp(int port) throws IOException
  {
    this.port = port;
    this.serverSocket = new ServerSocket(port);
  }

  public void mainloop() throws IOException
  {
    while (true) {
      InterConnection interConnection = new InterConnection(serverSocket.accept());
      interConnection.setDaemon(true);
      interConnection.start();
    }
  }
}
