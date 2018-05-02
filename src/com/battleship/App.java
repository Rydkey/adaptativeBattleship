package com.battleship;

import com.battleship.model.*;
import com.battleship.vue.AmiralVue;
import javafx.application.Application;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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
    partie.getJoueurs().add(matelot1);
    matelot2 = new Defenseur();
    matelot2.setEquipe(EquipeGentille);
    matelot2.setName("M2");
    partie.getJoueurs().add(matelot2);
    matelot3 = new Defenseur();
    matelot3.setEquipe(EquipeGentille);
    matelot3.setName("M3");
    partie.getJoueurs().add(matelot3);
    Torpilleur torpilleur1 = new Torpilleur();
    Equipage equipage = new Equipage();

    equipage.setAttaquant((Attaquant) matelot1);
    equipage.setDefenseur((Defenseur) matelot2);
    equipage.setDefenseur((Defenseur) matelot3);

    Amiral amiral1 = new Amiral();
    amiral1.setName("archibald");
    amiral1.setTableauJoueurs(new HashMap<>());
    amiral1.getTableauJoueurs().put(torpilleur1, equipage);

    for (Field matelot : equipage.getClass().getDeclaredFields()) {
      System.out.println(matelot.getName());
    }

    Plateau battlefield = new Plateau();
    Case lesCases[][] = new Case[8][8];
    battlefield.setLesCases(lesCases);

    Application.launch(AmiralVue.class,args);
  }
}
