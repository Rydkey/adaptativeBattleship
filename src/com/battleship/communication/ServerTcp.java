package com.battleship.communication;

import com.battleship.model.*;

import java.io.IOException;
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
    Partie partie = new Partie();
    for (int i = 0; i < partie.getEquipes().length; i++) {
      partie.getEquipes()[i] = new Equipe();
    }
    while (!partie.isEnCours()) {
      Socket clientSocket = serverSocket.accept();
      InterConnection interConnection = new InterConnection(clientSocket, "serveur");
      interConnection.setDaemon(true);
      interConnection.start();
      EquipeInitializer(interConnection, partie);
      for (Joueur j : partie.getEquipes()[0].getListeJoueur()){
        System.out.println(j.getClass());
      }
    }
  }

  private void EquipeInitializer(InterConnection interConnection, Partie partie)
  {
    Joueur joueur;
    if (partie.getEquipes()[0].getListeJoueur().isEmpty()) {
      joueur = new Amiral();
      partie.getEquipes()[0].getListeJoueur().add(joueur);
    }
    if (partie.getEquipes()[1].getListeJoueur().isEmpty()) {
      joueur = new Amiral();
      partie.getEquipes()[1].getListeJoueur().add(joueur);
    } else {
      joueur = new Matelot();
      int countEquipeA = partie.getEquipes()[0].getListeJoueur().size();
      int countEquipeB = partie.getEquipes()[1].getListeJoueur().size();
      if (countEquipeA > countEquipeB) {
        partie.getEquipes()[1].getListeJoueur().add(joueur);
      } else {
        partie.getEquipes()[0].getListeJoueur().add(joueur);
      }
    }
    connectionJoueurHashMap.put(interConnection, joueur);
  }
}
