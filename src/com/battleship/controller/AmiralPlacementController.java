package com.battleship.controller;

import com.battleship.model.*;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import java.util.ResourceBundle;

//import com.battleship.vue.AmiralView;

public class AmiralPlacementController extends BaseController
{

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
  public GridPane assigniationGrid;
  Matelot joueurs[] = new Matelot[6];
  private Partie partie;
  private Equipe equipe;
  private HashMap<Rectangle, Navire> navireRectangleAssociation;
  private HashMap<Pane, Case> paneCaseAssociation;
  private ArrayList<Rectangle> unselectableShip;
  private Rectangle shipSelected;
  private boolean orientation;
  private Plateau plateau;

  public void endGame()
  {
    System.out.println("end");
  }

//  public EventHandler<MouseEvent> placementShip()
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

  /**
   * place le bateau sur le plateau.
   */
  public EventHandler<MouseEvent> placementShip()
  {
    return event -> {
      if (shipSelected != null) {
        Navire navire = navireRectangleAssociation.get(shipSelected);
        Node source = (Node) event.getSource();
        ArrayList<Pane> tempPaneList = new ArrayList<>();
        boolean positionable;
        positionable = isPositionable(navire, source, tempPaneList);
        if (positionable) {
          for (Pane pane : tempPaneList) {
            shipSelected.setFill(Color.GREY);
            paneCaseAssociation.get(pane).setStatus(Status.NAVIRE);
            unselectableShip.add(shipSelected);
            navireRectangleAssociation.get(shipSelected).getCaseOccupees().add(paneCaseAssociation.get(pane));
          }
          shipSelected = null;
          partyIsReady();
          //System.out.println(navireRectangleAssociation);
          //System.out.println(paneCaseAssociation);
          //System.out.println(navire.getCaseOccupees());
        }
      }
    };
  }

  private void partyIsReady()
  {
    boolean allShipPlaced = true;
    for (Rectangle rectangle : navireRectangleAssociation.keySet()) {
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
  private EventHandler<MouseEvent> drawShipPrevision()
  {
    return event -> {
      if (shipSelected != null) {
        Navire navire = navireRectangleAssociation.get(shipSelected);
        Node source = (Node) event.getSource();
        ArrayList<Pane> tempPaneList = new ArrayList<>();
        boolean positionable;
        positionable = isPositionable(navire, source, tempPaneList);
        for (Pane pane : tempPaneList) {
          if (positionable) {
            pane.setStyle("-fx-background-color: grey");
          } else {
            pane.setStyle("-fx-background-color: red");
          }
        }
      }
    };
  }

  private EventHandler<MouseEvent> getOpPlayer()
  {
    return event -> {

    };
  }

  /**
   * renvoie vrai si le navire est positionnable.
   *
   * @param navire
   * @param source
   * @param tempPaneList
   * @return
   */
  private boolean isPositionable(Navire navire, Node source, ArrayList<Pane> tempPaneList)
  {
    boolean positionable = true;
    int j = 0;
    if (orientation) { /*si orientation, horizontal*/
      if (GridPane.getColumnIndex(source) + navire.getTaille() <= NB_CASES) {
        for (int i = 0; i < navire.getTaille(); i++) {
          Pane pane = (Pane) gameMainGrid.getChildren().get((GridPane.getColumnIndex(source) + i) * NB_CASES + (GridPane.getRowIndex(source) + 1));
          tempPaneList.add(pane);
          positionable = caseIsAccessible(pane);
          if (!positionable) break;
        }
      } else {
        while (GridPane.getColumnIndex(source) + j < NB_CASES) {
          Pane pane = (Pane) gameMainGrid.getChildren().get((GridPane.getColumnIndex(source) + j) * 10 + (GridPane.getRowIndex(source) + 1));
          tempPaneList.add(pane);
          j++;
        }
        positionable = false;
      }
    } else { /*sinon, vertical*/
      if (GridPane.getRowIndex(source) + navire.getTaille() <= NB_CASES) {
        for (int i = 0; i < navire.getTaille(); i++) {
          Pane pane = (Pane) gameMainGrid.getChildren().get(GridPane.getColumnIndex(source) * NB_CASES + (GridPane.getRowIndex(source) + 1 + i));
          tempPaneList.add(pane);
          positionable = caseIsAccessible(pane);
          if (!positionable) break;
        }
      } else {
        while (GridPane.getRowIndex(source) + j < NB_CASES) {
          Pane pane = (Pane) gameMainGrid.getChildren().get(GridPane.getColumnIndex(source) * 10 + (GridPane.getRowIndex(source) + 1 + j));
          tempPaneList.add(pane);
          j++;
        }
        positionable = false;
      }
    }
    return positionable;
  }


  private boolean caseIsAccessible(Pane pane)
  {
    boolean result = caseVerification(pane, paneCaseAssociation);
    int panePosition = (GridPane.getColumnIndex(pane)) * NB_CASES + (GridPane.getRowIndex(pane) + 1);
    if (result) {
      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (i != 0 || j != 0) {
            /*
             * vérifie si l'on est pas au bord
             * Par exemple, 40 et 41 ne sont pas allignés.
             * */
            int tempPosition = (GridPane.getColumnIndex(pane) + j) * NB_CASES + (GridPane.getRowIndex(pane) + 1) + i;
            if (tempPosition > 0 && tempPosition <= 100) {
              if (!(tempPosition % NB_CASES == 0 && panePosition % NB_CASES == 1)
                  && !(tempPosition % NB_CASES == 1 && panePosition % NB_CASES == 0)) {
                Pane temp = (Pane) gameMainGrid.getChildren().get(tempPosition);
                result = caseVerification(temp, paneCaseAssociation);
                if (!result) break;
              }
            }
          }
        }
        if (!result) break;
      }
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
    plateau = new Plateau();
    for (int i = 0; i < NB_CASES; i++) {
      for (int j = 0; j < NB_CASES; j++) {
        Pane pane = new Pane();
        Case laCase = new Case(i, j, Status.VIDE);
        plateau.getLesCases()[i][j] = laCase;
        paneCaseAssociation.put(pane, laCase);
        pane.setOnMouseClicked(this.placementShip());
        pane.setOnMouseEntered(this.drawShipPrevision());
        pane.setOnMouseExited(this.refreshColor());
        gameMainGrid.add(pane, i, j);
      }
    }
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 10; j++) {
        ComboBox<Matelot> combo = new ComboBox<>();
        if (i == 0) {
//          combo.setOnMouseClicked();
        } else {

        }
        assigniationGrid.add(combo, i, j);
      }
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

    for (int i = 0; i < 6; i++) {
      joueurs[i] = new Matelot();
    }

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
          if (caseVerification((Pane) childen, paneCaseAssociation)) {
            childen.setStyle("-fx-color: white");
          } else {
            childen.setStyle("-fx-background-color:  blue");
          }
        }
      }
    };
  }

  public void ready(MouseEvent mouseEvent)
  {

  }
}
