package com.battleship.communication;

import com.battleship.model.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerTcp
{
  private int port;
  private String ip;
  private ServerSocket serverSocket;
  private HashMap<InterConnection, Joueur> connectionJoueurHashMap;
  private Partie partie;

  public ServerTcp(int port) throws IOException
  {
    this.port = port;
    this.serverSocket = new ServerSocket(port);
    this.connectionJoueurHashMap = new HashMap<>();
    this.partie = new Partie();
  }

  public void partieInitialization() throws IOException
  {
    while (!partie.isEnCours()) {
      Socket clientSocket = serverSocket.accept();
      Amiral amiral = new Amiral();
      InterConnection interConnection = new InterConnection(clientSocket, "serveur");
      String message;
      if (partie.getEquipes()[0] == null) {
        partie.getEquipes()[0] = new Equipe();
        partie.getEquipes()[0].setAmiral(amiral);
        connectionJoueurHashMap.put(interConnection, amiral);
        message = "vous êtes Amiral de l'équipe 1";
        interConnection.getOut().writeObject(message);
      } else {
        partie.getEquipes()[0] = new Equipe();
        message = "vous êtes Amiral de l'équipe 2";
        interConnection.getOut().writeObject(message);
      }
      for (InterConnection inter:
           connectionJoueurHashMap.keySet()) {
        if (inter != interConnection){
          inter.getOut().writeObject("connection d'un nouveau challenger");
        }
      }

      interConnection.setDaemon(true);
      interConnection.start();
    }
  }
}
