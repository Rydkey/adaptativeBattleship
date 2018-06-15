package com.battleship.controller;

import com.battleship.model.Case;
import com.battleship.model.Navire;
import com.battleship.model.Status;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TimerTask;

public class BaseController extends TimerTask implements Initializable
{
  public static final int NB_CASES = 10;

  public boolean caseVerification(Pane temp, HashMap<Pane, Case> paneCaseAssociation)
  {
    boolean result;
    switch (paneCaseAssociation.get(temp).getStatus()) {
      case VIDE:
        result = true;
        break;
      default:
        result = false;
        break;
    }
    return result;
  }

  @Override
  public void run() {}

  @Override
  public void initialize(URL location, ResourceBundle resources){  }
}
