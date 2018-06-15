package com.battleship;

import com.battleship.model.Defenseur;
import com.battleship.model.Equipe;
import com.battleship.model.Matelot;
import com.battleship.model.Navire;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import com.battleship.vue.AmiralView;

public class App extends Application
{

  public static final String VUE_JOUEUR  = "/fxml/joueurView.fxml";
  public static final String VUE_AMIRAL  = "/fxml/amiralPlacementView.fxml";

  public static void navireSetDefenseur(Equipe equipe, Navire navire, Matelot matelot)
  {
    equipe.getAssignationNavireEquipage().get(navire).setDefenseur((Defenseur) matelot);
  }

  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
//    Equipe equipeA = new Equipe(NomEquipe.EQUIPEA);
//    Equipe equipeB = new Equipe(NomEquipe.EQUIPEB);
//    Partie partie = new Partie();
//    for (int i = 0; i < 8; i++) {
//      if (i == 0) {
//        Amiral amiral1 = new Amiral();
//        amiral1.setName("Amiral A");
//        equipeA.setAmiral(amiral1);
//      } else {
//        Joueur joueur;
//        if (i < 3) {
//          if (equipeB.getListeJoueur().isEmpty()) {
//            joueur = new Amiral();
//            joueur.setName("Amiral B");
//            equipeB.setAmiral((Amiral) joueur);
//          } else {
//            joueur = new Matelot();
//          }
//          equipeB.getListeJoueur().add(joueur);
//        } else {
//          joueur = new Matelot();
//          equipeA.getListeJoueur().add(joueur);
//        }
//        if (joueur.getName() != null) {
//          joueur.setName("M" + i);
//        }
//      }
//    }
//    Plateau plateauEquipeA = new Plateau();
//    Plateau plateauEquipeB = new Plateau();
//
//    partie.getPlateaux().put(equipeA, plateauEquipeA);
//    partie.getPlateaux().put(equipeB, plateauEquipeB);
//
//    AmiralPlacementController amiralController = new AmiralPlacementController(partie, primaryStage, equipeA);

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
//        if(torpilleur1.isPretATirer()){
//          torpilleur1.tire();
//          torpilleur1.setPretATirer(false);
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

//    Plateau plateauA = new Plateau();
//    Plateau plateauB = new Plateau();
//
//    for (int i = 0; i < 8; i++) {
//      for (int j = 0; j < 8; j++) {
//        Case thecase = new Case(i, j, VIDE);
//        plateauA.getLesCases()[i][j] = (thecase);
//      }
//    }

    /*amiral view*/
    //Parent root = FXMLLoader.load(getClass().getResource(VUE_AMIRAL));
    /*player view*/
    Parent root = FXMLLoader.load(getClass().getResource(VUE_JOUEUR));
    primaryStage.setTitle("test");
    primaryStage.setScene(new Scene(root, 1280, 720));
    primaryStage.show();
  }
}
