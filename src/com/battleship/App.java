package com.battleship;

import com.battleship.model.*;
import com.battleship.model.Navire;
import com.battleship.model.Torpilleur;

import static com.battleship.model.Status.VIDE;

public class App
{
  public static void main(String[] args)
  {
    Partie partie = new Partie();
    Equipe equipeA = new Equipe("Equipe A");
    Matelot matelot1 = new Matelot();
    Matelot matelot2 = new Matelot();
    Matelot matelot3 = new Matelot();

    matelot1 = new Attaquant();
    matelot1.setEquipe(equipeA);
    matelot1.setName("M1");
    matelot2 = new Defenseur();
    matelot2.setEquipe(equipeA);
    matelot2.setName("M2");
    Torpilleur torpilleur1 = new Torpilleur();
    torpilleur1.setName("torpilleur");
    Equipage equipage = new Equipage();

    equipage.setAttaquant((Attaquant) matelot1);
    equipage.setDefenseur((Defenseur) matelot2);

    Amiral amiral1 = new Amiral();
    amiral1.setName("archibald");
    equipeA.getTableauJoueurs().put(torpilleur1, equipage);


    /*Tout les équipages par rapport aux navires */
//    for (Map.Entry<Navire, Equipage> entry : equipeA.getTableauJoueurs().entrySet()) {
//      Navire navire = entry.getKey();
//      System.out.print("Navire : " + navire.getName());
//      Equipage equipage1 = entry.getValue();
//      for (Matelot matelot: equipage1.getJoueursInEquipage()) {
//        System.out.print(matelot.getClass().getSimpleName()+" "+matelot.getName() + " ");
//      }
//      System.out.println();
//    }

    /*Permission de tirer*/
//      if (equipeA.getTableauJoueurs().get(torpilleur1).getAttaquant() == matelot1){
//        if(torpilleur1.isPretATirrer()){
//          torpilleur1.tire();
//          torpilleur1.setPretATirrer(false);
//          /*@Todo: check case other plateau*/
//        }else{
//          throw new CantShotException();
//        }
//      }

    /*Permission de deplacer*/
//      if (equipeA.getTableauJoueurs().get(torpilleur1).getDefenseur() == matelot1){
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

  public static void navireSetDefenseur(Equipe equipe, Navire navire, Matelot matelot)
  {
    equipe.getTableauJoueurs().get(navire).setDefenseur((Defenseur) matelot);
  }
}
