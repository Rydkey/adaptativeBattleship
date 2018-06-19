package com.battleship.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client
{
  public static void main(String[] args) throws IOException
  {
    System.out.println("entrez un pseudo");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    String pseudo = bufferedReader.readLine();
    Socket socket = new Socket("localhost", 2018);
    InterConnection interConnection = new InterConnection(socket, pseudo);
    interConnection.start();
    while (socket.isConnected()) ;
  }
}
