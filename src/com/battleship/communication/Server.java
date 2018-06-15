package com.battleship.communication;

import java.io.IOException;

public class Server
{
  public static void main(String[] args) throws IOException
  {
    ServerTcp serverTcp = new ServerTcp(2018);
    serverTcp.mainloop();
  }
}
