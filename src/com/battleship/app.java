package com.battleship;

import com.battleship.model.Amiral;
import com.battleship.model.Case;
import com.battleship.model.Matelot;
import com.battleship.model.Partie;
import com.battleship.vue.AmiralVue;
import javafx.application.Application;

public class app
{
  public static void main(String[] args)
  {
    Partie partie = new Partie();
    Matelot matelot1 = new Matelot();
    Matelot matelot2 = new Matelot();
    Matelot matelot3 = new Matelot();
    Matelot matelot4 = new Matelot();
    Amiral amiral1 = new Amiral();
    Amiral amiral2 = new Amiral();

    Application.launch(AmiralVue.class,args);
  }
}
