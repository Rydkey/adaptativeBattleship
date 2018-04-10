package com.battleship;

import com.battleship.model.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;

import static com.battleship.model.Equipe.EquipeGentille;
import static com.battleship.model.Status.VIDE;

public class App
{
  public static void main(String[] args) throws IntrospectionException
  {
    Partie partie = new Partie();
    Matelot matelot1 = new Matelot();
    Matelot matelot2 = new Matelot();
    Matelot matelot3 = new Matelot();

    matelot1 = new Attaquant();
    matelot1.setEquipe(EquipeGentille);
    matelot1.setName("M1");
    matelot2 = new Defenseur();
    matelot2.setEquipe(EquipeGentille);
    matelot2.setName("M2");
    matelot3 = new Defenseur();
    matelot3.setEquipe(EquipeGentille);
    matelot3.setName("M3");
    Torpilleur torpilleur1 = new Torpilleur();
    Equipage equipage = new Equipage();

    equipage.setAttaquant((Attaquant) matelot1);
    equipage.setDefenseur((Defenseur) matelot2);
    equipage.setDefenseur((Defenseur) matelot3);

    Amiral amiral1 = new Amiral();
    amiral1.setName("archibald");
    amiral1.setTableauJoueurs(new HashMap<>());
    amiral1.getTableauJoueurs().put(torpilleur1, equipage);
//    for (Matelot matelot : equipage.getClass().getDeclaredField("")) {
//
//    }

    equipage.getAttaquant().getNaviresAssignes().add(torpilleur1);

    Plateau plateau = new Plateau();


    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        Case thecase = new Case(i, j, VIDE);
        plateau.getLesCases().add(thecase);
        System.out.println(plateau.getLesCases().get((i + 1) * j).getX());
      }
      System.out.println("\n");
    }

    System.out.println(amiral1.getTableauJoueurs().get(torpilleur1).getDefenseur().getName());
  }
}
