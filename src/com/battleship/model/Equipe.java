package com.battleship.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Equipe {

  private List<Joueur> listeJoueur;
  private HashMap<Navire, Equipage> assignationNavireEquipage;
  private static NomEquipe nomEquipe;
  private Amiral amiral;

  public Equipe() {
    this.assignationNavireEquipage = new HashMap<>();
    this.listeJoueur = new ArrayList<>();
  }

  public Equipe(NomEquipe nomEquipe) {
    this.nomEquipe = nomEquipe;
    this.assignationNavireEquipage = new HashMap<>();
    this.listeJoueur = new ArrayList<>();
  }

  public static NomEquipe getNomEquipe() {
    return nomEquipe;
  }

  public static void setNomEquipe(NomEquipe nomEquipe) {
    Equipe.nomEquipe = nomEquipe;
  }

  public HashMap<Navire, Equipage> getAssignationNavireEquipage() {
    return assignationNavireEquipage;
  }

  public void setAssignationNavireEquipage(HashMap<Navire, Equipage> assignationNavireEquipage) {
    this.assignationNavireEquipage = assignationNavireEquipage;
  }

  public List<Joueur> getListeJoueur() {
    return listeJoueur;
  }

  public void setListeJoueur(List<Joueur> listeJoueur) {
    this.listeJoueur = listeJoueur;
  }

  public Amiral getAmiral()
  {
    return amiral;
  }

  public void setAmiral(Amiral amiral)
  {
    this.amiral = amiral;
  }
}
