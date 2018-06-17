package com.battleship.communication;

import java.io.*;
import java.net.Socket;

public class InterConnection extends Thread
{
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private BufferedReader reader;
  private String login;

  public InterConnection(Socket socket, String login) throws IOException
  {
    this.login = login;
    this.out = new ObjectOutputStream(socket.getOutputStream());
    this.in = new ObjectInputStream(socket.getInputStream());
    this.reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public void run()
  {
    Thread emission = new Thread(new Emission(out, reader, login));
    Thread recepetion = new Thread(new Reception(in));
    emission.setDaemon(true);
    recepetion.setDaemon(true);
    emission.start();
    recepetion.start();
  }

  public ObjectOutputStream getOut()
  {
    return out;
  }

  public void setOut(ObjectOutputStream out)
  {
    this.out = out;
  }

  public ObjectInputStream getIn()
  {
    return in;
  }

  public void setIn(ObjectInputStream in)
  {
    this.in = in;
  }

  public BufferedReader getReader()
  {
    return reader;
  }

  public void setReader(BufferedReader reader)
  {
    this.reader = reader;
  }

  public String getLogin()
  {
    return login;
  }

  public void setLogin(String login)
  {
    this.login = login;
  }
}
