package com.battleship;

import com.battleship.controller.AmiralController;
import com.battleship.model.*;
import com.battleship.model.Navire;
import com.battleship.model.Torpilleur;
import com.battleship.vue.AmiralView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import static com.battleship.model.Status.VIDE;

public class App extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Hello World!");
    Equipe equipeA = new Equipe(NomEquipe.EQUIPEA);
    Equipe equipeB = new Equipe(NomEquipe.EQUIPEB);
    Partie partie = new Partie();
    for (int i = 0; i < 8; i++) {
      if (i == 0) {
        Amiral amiral1 = new Amiral();
        amiral1.setName("Amiral A");
        equipeA.getListeJoueur().add(amiral1);
      } else {
        Joueur joueur;
        if (i < 3) {
          if (equipeB.getListeJoueur().isEmpty()) {
            joueur = new Amiral();
            joueur.setName("Amiral B");
          } else {
            joueur = new Matelot();
          }
          equipeB.getListeJoueur().add(joueur);
        } else {
          joueur = new Matelot();
          equipeA.getListeJoueur().add(joueur);
        }
        if (joueur.getName() != null) {
          joueur.setName("M" + i);
        }
      }
    }
    Plateau plateauEquipeA = new Plateau();
    Plateau plateauEquipeB = new Plateau();

    partie.getPlateaux().put(equipeA,plateauEquipeA);
    partie.getPlateaux().put(equipeB,plateauEquipeB);

    AmiralController amiralController = new AmiralController(partie, primaryStage, equipeA);

//    AmiralView amiralView = new AmiralView();

    /*Tout les équipages par rapport aux navires */
//    for (Map.Entry<Navire, Equipage> entry : equipeA.getAssignationNavireEquipage().entrySet()) {
//      Navire navire = entry.getKey();
//      System.out.print("Navire : " + navire.getName());
//      Equipage equipage1 = entry.getValue();
//      for (Matelot matelot: equipage1.getJoueursInEquipage()) {
//        System.out.print(matelot.getClass().getSimpleName()+" "+matelot.getName() + " ");
//      }
//      System.out.println();
//    }

    /*Permission de tirer*/
//      if (equipeA.getAssignationNavireEquipage().get(torpilleur1).getAttaquant() == matelot1){
//        if(torpilleur1.isPretATirrer()){
//          torpilleur1.tire();
//          torpilleur1.setPretATirrer(false);
//          /*@Todo: check case other plateau*/
//        }else{
//          throw new CantShotException();
//        }
//      }

    /*Permission de deplacer*/
//      if (equipeA.getAssignationNavireEquipage().get(torpilleur1).getDefenseur() == matelot1){
//        if (!torpilleur1.isTouche() || !torpilleur1.isCoule()){
//          System.out.println("tu peux bouger bébé");
//          /*@Todo: check mouvement possible*/
//        }else{
//          throw new CantMoveException();
//        }
//      }

    Plateau plateauA = new Plateau();
    Plateau plateauB = new Plateau();

    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Case thecase = new Case(i, j, VIDE);
        plateauA.getLesCases()[i][j] = (thecase);
      }

    }
  }

  public static void navireSetDefenseur(Equipe equipe, Navire navire, Matelot matelot) {
    equipe.getAssignationNavireEquipage().get(navire).setDefenseur((Defenseur) matelot);
  }
}
