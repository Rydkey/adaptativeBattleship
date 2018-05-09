package com.battleship.controller;

import com.battleship.model.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ResourceBundle;

//import com.battleship.vue.AmiralView;

public class AmiralPlacementController extends BaseController implements Initializable {

  public AnchorPane anchorPane;
  public Rectangle cuirasseRectangle;
  public Rectangle croiseurRectangle1;
  public GridPane gameMainGrid;
  public Rectangle croiseurRectangle2;
  public Rectangle torpilleurRectangle1;
  public Rectangle torpilleurRectangle2;
  public Rectangle torpilleurRectangle3;
  public Rectangle sousMarinRectangle1;
  public Rectangle sousMarinRectangle2;
  public Rectangle sousMarinRectangle3;
  public Rectangle sousMarinRectangle4;
  public Text messageContainer;
  public Button quit;
  public Button ready;
  private Partie partie;
  private Equipe equipe;
  private HashMap<Rectangle, Navire> navireRectangleAssociation;
  private HashMap<Pane, Case> paneCaseAssociation;
  private ArrayList<Rectangle> unselectableShip;
  private Rectangle shipSelected;
  private boolean orientation;


  public void endGame()
  {
    System.out.println("end");
  }

//  public EventHandler<MouseEvent> click()
//  {
//    EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent event)
//      {
//        Node source = (Node) event.getSource();
//        Integer colIndex = GridPane.getColumnIndex(source);
//        Integer rowIndex = GridPane.getRowIndex(source);
//        System.out.println("col : " + colIndex);
//        event.consume();
//      }
//    };
//    return mousePositionHandler;
//  }

  public EventHandler<MouseEvent> click()
  {
    return event -> {
      if (shipSelected != null) {
        Navire navire = navireRectangleAssociation.get(shipSelected);
        Node source = (Node) event.getSource();
        ArrayList<Pane> tempPaneList = new ArrayList<>();
        boolean positionable = true;
        for (int i = 0; i < navire.getTaille(); i++) {
          /*récupère le pane sous la souris (si le pane est en 1:1, il faut récuperer le pane 11)*/
          int j;
          if (orientation) {
            if (GridPane.getColumnIndex(source) + navire.getTaille() <= 8) {
              for (j = 0; j < navire.getTaille(); j++) {
                Pane pane = (Pane) gameMainGrid.getChildren().get((GridPane.getColumnIndex(source) + j) * 10 + (GridPane.getRowIndex(source) + 1));
                tempPaneList.add(pane);
                positionable = caseIsAcceccible(pane);
                if (!positionable) break;
              }
            }
          } else {
            if (GridPane.getRowIndex(source) + navire.getTaille() <= 8) {
              for (j = 0; j < navire.getTaille(); j++) {
                Pane pane = (Pane) gameMainGrid.getChildren().get(GridPane.getColumnIndex(source) * 10 + (GridPane.getRowIndex(source) + 1 + j));
                tempPaneList.add(pane);
                positionable = caseIsAcceccible(pane);
                if (!positionable) break;
              }
            }
          }
        }
        if (positionable) {
          for (Pane pane : tempPaneList) {
            shipSelected.setFill(Color.GREY);
            paneCaseAssociation.get(pane).setStatus(Status.NAVIRE);
            unselectableShip.add(shipSelected);
          }
          shipSelected = null;
          refreshColor();
          partyIsReady();
        }
      }
    };
  }

  private void partyIsReady()
  {
    boolean allShipPlaced = true;
    Iterator<Rectangle> itr = navireRectangleAssociation.keySet().iterator();
    while (itr.hasNext()) {
      Rectangle rectangle = itr.next();
      if (!unselectableShip.contains(rectangle)) {
        allShipPlaced = false;
        break;
      }
    }
    if (allShipPlaced) {
      ready.setVisible(true);
    }
  }

  /*
   * colorie les cases en fonction du bateau sélectionné
   * */
  private EventHandler<MouseEvent> drawShipPrevisqion()
  {
    return event -> {
      if (shipSelected != null) {
        Navire navire = navireRectangleAssociation.get(shipSelected);
        Node source = (Node) event.getSource();
        for (int i = 0; i < navire.getTaille(); i++) {
          /*récupère le pane sous la souris (si le pane est en 1:1, il faut récuperer le pane 11)*/
          int j = 0;
          if (orientation) {
            if (GridPane.getColumnIndex(source) + navire.getTaille() > 8) {
              while (GridPane.getColumnIndex(source) + j < 8) {
                Pane tempA = (Pane) gameMainGrid.getChildren().get((GridPane.getColumnIndex(source) + j) * 10 + (GridPane.getRowIndex(source) + 1));
                tempA.setStyle("-fx-background-color: red");
                j++;
              }
            } else {
              for (j = 0; j < navire.getTaille(); j++) {
                Pane tempA = (Pane) gameMainGrid.getChildren().get((GridPane.getColumnIndex(source) + j) * 10 + (GridPane.getRowIndex(source) + 1));
                if (caseIsAcceccible(tempA)) {
                  tempA.setStyle("-fx-background-color: purple");
                } else {
                  tempA.setStyle("-fx-background-color: red");
                }
              }
            }
          } else {
            if (GridPane.getRowIndex(source) + navire.getTaille() > 8) {
              while (GridPane.getRowIndex(source) + j < 8) {
                Pane tempA = (Pane) gameMainGrid.getChildren().get(GridPane.getColumnIndex(source) * 10 + (GridPane.getRowIndex(source) + 1 + j));
                tempA.setStyle("-fx-background-color: red");
                j++;
              }
            } else {
              for (j = 0; j < navire.getTaille(); j++) {
                Pane tempA = (Pane) gameMainGrid.getChildren().get(GridPane.getColumnIndex(source) * 10 + (GridPane.getRowIndex(source) + 1 + j));
                if (caseIsAcceccible(tempA)) {
                  tempA.setStyle("-fx-background-color: purple");
                } else {
                  tempA.setStyle("-fx-background-color: red");
                }
              }
            }
          }
        }
      }
    };
  }

  private boolean caseIsAcceccible(Pane tempA)
  {
    boolean result;
    switch (paneCaseAssociation.get(tempA).getStatus()) {
      case VIDE:
        result = true;
        break;
      default:
        result = false;
        break;
    }
    return result;
  }

  /*
   * Affiche le bateau selectionné en vert et permet de choisir un bateau à placer.
   * */
  public void selectShip(MouseEvent event)
  {
    Rectangle clicked = (Rectangle) event.getSource();
    Navire navireCorrespondant = navireRectangleAssociation.get(clicked);
    if (navireCorrespondant != null && !unselectableShip.contains(clicked)) {
      if (shipSelected != null) {
        shipSelected.setFill(Color.BLUE);
      }
      clicked.setFill(Color.GREEN);
      shipSelected = clicked;
    }
  }

  public void changeOrientation(KeyEvent event)
  {
    if (event.getCode() == KeyCode.R) {
      orientation = !orientation;
      event.consume();
    }
  }

  /*
   * fonction d'initialisation
   * */
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    navireRectangleAssociation = new HashMap<>();
    paneCaseAssociation = new HashMap<>();
    unselectableShip = new ArrayList<>();
    orientation = false;
    for (int i = 0; i < NB_CASES; i++)
      for (int j = 0; j < NB_CASES; j++) {
        Pane pane = new Pane();
        Case laCase = new Case(i, j, Status.VIDE);
        paneCaseAssociation.put(pane, laCase);
        pane.setOnMouseClicked(this.click());
        pane.setOnMouseEntered(this.drawShipPrevisqion());
        pane.setOnMouseExited(this.refreshColor());
        gameMainGrid.add(pane, i, j);
      }
    ready.setVisible(false);
    Cuirasse c1 = new Cuirasse();
    Croiseur cr1 = new Croiseur();
    Croiseur cr2 = new Croiseur();
    Torpilleur t1 = new Torpilleur();
    Torpilleur t2 = new Torpilleur();
    Torpilleur t3 = new Torpilleur();
    SousMarin s1 = new SousMarin();
    SousMarin s2 = new SousMarin();
    SousMarin s3 = new SousMarin();
    SousMarin s4 = new SousMarin();
    navireRectangleAssociation.put(cuirasseRectangle, c1);
    navireRectangleAssociation.put(croiseurRectangle1, cr1);
    navireRectangleAssociation.put(croiseurRectangle2, cr2);
    navireRectangleAssociation.put(torpilleurRectangle1, t1);
    navireRectangleAssociation.put(torpilleurRectangle2, t2);
    navireRectangleAssociation.put(torpilleurRectangle3, t3);
    navireRectangleAssociation.put(sousMarinRectangle1, s1);
    navireRectangleAssociation.put(sousMarinRectangle2, s2);
    navireRectangleAssociation.put(sousMarinRectangle3, s3);
    navireRectangleAssociation.put(sousMarinRectangle4, s4);
  }

  private EventHandler<MouseEvent> refreshColor()
  {
    return event -> {
      for (Node childen : gameMainGrid.getChildren()) {
        Case tempCase = paneCaseAssociation.get(childen);
        if (tempCase != null) {
          if (caseIsAcceccible((Pane) childen)) {
            childen.setStyle("-fx-color: white");
          } else {
            childen.setStyle("-fx-background-color: grey");
          }
        }
      }
    };
  }

  public void ready(MouseEvent mouseEvent)
  {

  }
}
