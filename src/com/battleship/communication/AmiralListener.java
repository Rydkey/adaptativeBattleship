package com.battleship.communication;

import com.battleship.model.Partie;

import java.io.IOException;

public class AmiralListener implements Runnable
{
  private Partie partie;
  private InterConnection interConnection;


  public AmiralListener(Partie partie, InterConnection interConnection) throws IOException
  {
    this.partie = partie;
    this.interConnection = interConnection;
  }

  @Override
  public void run()
  {
    while (true) {
      try {
        interConnection.getOut().writeObject(interConnection.getIn());
        interConnection.getOut().flush();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
