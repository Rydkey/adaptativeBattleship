package com.battleship.controller;

import com.battleship.model.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.*;

public class JoueurController extends BaseController implements Initializable
{

  public AnchorPane anchorPane;
  public Rectangle cuirasseRectangle;
  public Rectangle croiseurRectangle1;
  public GridPane ourGameGrid;
  public GridPane ennemiesGameGrid;
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
  private Partie partie;
  private Equipe equipe;
  //private HashMap<Rectangle, Navire> navireRectangleAssociation;
  private HashMap<Pane, Case> ourPaneCaseAssociation;
  private HashMap<Pane, Case> ennemyPaneCaseAssociation;
  private ArrayList<Rectangle> unselectableShip;
  private Navire shipSelected;
  private boolean orientation;
  private boolean sameX;
  private Plateau ourPlateau;
  private Plateau ennemyPlateau;
  private Boolean positionable = false;
  private Equipe e = new Equipe();
  private ArrayList<Joueur> lJ = new ArrayList<>();
  private ArrayList<Pane> actionPaneList = new ArrayList<>();
  private HashMap<Navire, Equipage> lE = new HashMap<>();

  /*
   * fonction d'initialisation
   * */
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    System.out.println("ok");
    //navireRectangleAssociation = new HashMap<>();
    ourPaneCaseAssociation = new HashMap<>();
    ennemyPaneCaseAssociation = new HashMap<>();
    unselectableShip = new ArrayList<>();
    orientation = false;
    ourPlateau = new Plateau();
    ennemyPlateau = new Plateau();
    for (int i = 0; i < NB_CASES; i++) {
      for (int j = 0; j < NB_CASES; j++) {
        Pane ourPane = new Pane();
        Pane ennemyPane = new Pane();
        Case ourCase = new Case(i, j, Status.VIDE);
        Case ennemyCase = new Case(i, j, Status.VIDE);
        ourPlateau.getLesCases()[i][j] = ourCase;
        ennemyPlateau.getLesCases()[i][j] = ennemyCase;
        ourPaneCaseAssociation.put(ourPane, ourCase);
        ennemyPaneCaseAssociation.put(ennemyPane, ennemyCase);
        if (i == 1 && (j == 1 || j == 3 || j == 7)) {
          ourPaneCaseAssociation.get(ourPane).setStatus(Status.NAVIRE);
        }
        if (i == 4 && j == 5) {
          ourPaneCaseAssociation.get(ourPane).setStatus(Status.NAVIRE);
        }
        if (i == 5 && (j == 3 || j == 4 || j == 5 || j == 6)) {
          ourPaneCaseAssociation.get(ourPane).setStatus(Status.NAVIRE);
        }
        if (j == 2 && (i == 3 || i == 4)) {
          ourPaneCaseAssociation.get(ourPane).setStatus(Status.NAVIRE);
        }
        ourPane.setOnMouseClicked(this.interactionShip());
        //ourPane.setOnMouseEntered(this.drawShipPrevision());
        //pane.setOnMouseExited(this.refreshColor());
        ourGameGrid.add(ourPane, i, j);
        ennemiesGameGrid.add(ennemyPane, i, j);
      }
    }
    Cuirasse c1 = new Cuirasse();
    c1.getCaseOccupees().add(ourPlateau.getLesCases()[5][3]);
    c1.getCaseOccupees().add(ourPlateau.getLesCases()[5][4]);
    c1.getCaseOccupees().add(ourPlateau.getLesCases()[5][5]);
    c1.getCaseOccupees().add(ourPlateau.getLesCases()[5][6]);
    Croiseur cr1 = new Croiseur();
    Croiseur cr2 = new Croiseur();
    Torpilleur t1 = new Torpilleur();
    Torpilleur t2 = new Torpilleur();
    Torpilleur t3 = new Torpilleur();
    t3.getCaseOccupees().add(ourPlateau.getLesCases()[3][2]);
    t3.getCaseOccupees().add(ourPlateau.getLesCases()[4][2]);
    SousMarin s1 = new SousMarin();
    s1.getCaseOccupees().add(ourPlateau.getLesCases()[1][1]);
    SousMarin s2 = new SousMarin();
    s2.getCaseOccupees().add(ourPlateau.getLesCases()[1][3]);
    SousMarin s3 = new SousMarin();
    s3.getCaseOccupees().add(ourPlateau.getLesCases()[4][5]);
    SousMarin s4 = new SousMarin();
    s4.getCaseOccupees().add(ourPlateau.getLesCases()[1][7]);

    /*navireRectangleAssociation.put(cuirasseRectangle, c1);
    navireRectangleAssociation.put(croiseurRectangle1, cr1);
    navireRectangleAssociation.put(croiseurRectangle2, cr2);
    navireRectangleAssociation.put(torpilleurRectangle1, t1);
    navireRectangleAssociation.put(torpilleurRectangle2, t2);
    navireRectangleAssociation.put(torpilleurRectangle3, t3);
    navireRectangleAssociation.put(sousMarinRectangle1, s1);
    navireRectangleAssociation.put(sousMarinRectangle2, s2);
    navireRectangleAssociation.put(sousMarinRectangle3, s3);
    navireRectangleAssociation.put(sousMarinRectangle4, s4);*/

    Attaquant a1 = new Attaquant();
    a1.setName("a1");
    lJ.add(a1);
    Defenseur d1 = new Defenseur();
    d1.setName("d1");
    Defenseur d2 = new Defenseur();
    d2.setName("d2");
    lJ.add(d2);
    Attaquant a2 = new Attaquant();
    a2.setName("a2");
    lJ.add(a2);

    Equipage e1 = new Equipage(a1, d1);
    lE.put(s1, e1);
    lE.put(s2, e1);
    Equipage e2 = new Equipage(a2, d2);
    lE.put(s3, e2);
    lE.put(s4, e2);
    lE.put(c1, e1);
    lE.put(t3, e2);

    e.setListeJoueur(lJ);
    e.setAssignationNavireEquipage(lE);
    //System.out.println(e);

    Timer timer = new Timer();
    timer.schedule(this, 0, 500);
  }

  private EventHandler<MouseEvent> interactionShip()
  {
    return event -> {
      Pane pane = (Pane) event.getSource();
      Navire shipSelectedTemp = getNavireOfCase(pane);
      Node source = (Node) event.getSource();
      int yPosition = GridPane.getRowIndex(source);
      int xPosition = GridPane.getColumnIndex(source);
      int index = xPosition * NB_CASES + yPosition + 1;
      List<Case> newCase = new ArrayList<>();
      int movement;
      if (shipSelected != null) {
        //System.out.println(pane);
        //System.out.println(actionPaneList.contains(pane));
        if (actionPaneList.contains(pane)) {
          /*System.out.println(yPosition);
          System.out.println(xPosition);
          System.out.println(shipSelected.getCaseOccupees().get(0).getX());
          System.out.println(shipSelected.getCaseOccupees().get(0).getY());*/
          //System.out.println(shipSelected.getCaseOccupees());
          if (xPosition == shipSelected.getCaseOccupees().get(0).getX()) {
            //System.out.println("sameX");
            movement = (yPosition - shipSelected.getCaseOccupees().get(0).getY()) / Math.abs(yPosition - shipSelected.getCaseOccupees().get(0).getY());
            for (Case oldCase : shipSelected.getCaseOccupees()
                ) {
              newCase.add(ourPlateau.getLesCases()[oldCase.getX()][oldCase.getY() + movement]);
              if (!newCase.contains(oldCase)) {
                oldCase.setStatus(Status.VIDE);
              }
              ourPlateau.getLesCases()[oldCase.getX()][oldCase.getY() + movement].setStatus(Status.NAVIRE);
            }
          } else {
            //System.out.println("sameY");
            movement = (xPosition - shipSelected.getCaseOccupees().get(0).getX()) / Math.abs(xPosition - shipSelected.getCaseOccupees().get(0).getX());
            for (Case oldCase : shipSelected.getCaseOccupees()) {
              newCase.add(ourPlateau.getLesCases()[oldCase.getX() + movement][oldCase.getY()]);
              if (!newCase.contains(oldCase)) {
                oldCase.setStatus(Status.VIDE);
              }
              ourPlateau.getLesCases()[oldCase.getX() + movement][oldCase.getY()].setStatus(Status.NAVIRE);

            }
          }
          //System.out.println(movement);
          //System.out.println(newCase);
          shipSelected.setCaseOccupees(newCase);
          //System.out.println(shipSelected.getCaseOccupees());
        }
        //int xPositionNav = shipSelected.getCaseOccupees().get(0).getX();
        //int yPositionNav = shipSelected.getCaseOccupees().get(0).getY();
        shipSelected = null;
        actionPaneList.clear();
      } else {
        actionPaneList.clear();
        if (shipSelectedTemp != null) {
          if (lE.get(shipSelectedTemp).getDefenseur().getName().equals("d1")) {
            shipSelected = shipSelectedTemp;
            System.out.println(shipSelected);
            isPositionable(shipSelected, source);
          } else {
            System.out.println("Not your ship");
          }
        } else {
          System.out.println("No ship");
        }
      }
    };
  }

  @Override
  public void run()
  {
    for (Pane pane : actionPaneList) {
      if (positionable) {
        pane.setStyle("-fx-background-color: grey");
      } else {
        pane.setStyle("-fx-background-color: red");
      }
    }
    for (Node children : ourGameGrid.getChildren()) {
      Case tempCase = ourPaneCaseAssociation.get(children);
      if (tempCase != null) {
        if (caseVerification((Pane) children, ourPaneCaseAssociation) && !actionPaneList.contains(children)) {
          children.setStyle("-fx-color: white");
        } else {
          // Getting a Set of Key-value pairs
          Set entrySet = e.getAssignationNavireEquipage().entrySet();
          // Obtaining an iterator for the entry set

          // Iterate through HashMap entries(Key-Value pairs)
          for (Object anEntrySet : entrySet) {
            Map.Entry me = (Map.Entry) anEntrySet;
            Navire navire = (Navire) me.getKey();
            List<Case> casesOccupees = navire.getCaseOccupees();
            Equipage equipage = (Equipage) me.getValue();
            String aN = equipage.getAttaquant().getName();
            String dN = equipage.getDefenseur().getName();
            for (Case laCase : casesOccupees) {
              if (laCase.getX() == tempCase.getX() && laCase.getY() == tempCase.getY()) {
                if (aN.equals("d1") || dN.equals("d1")) {
                  children.setStyle("-fx-background-color:  green");
                } else {
                  children.setStyle("-fx-background-color:  blue");
                }
                break;
              }
            }
          }
        }
      }
    }
  }

  private Navire getNavireOfCase(Pane pane)
  {
    // Getting a Set of Key-value pairs
    Set entrySet = e.getAssignationNavireEquipage().entrySet();
    // Obtaining an iterator for the entry set
    Iterator it = entrySet.iterator();
    Boolean find = false;
    Navire navire = null;
    Navire navireTemp;
    Case laCase = ourPaneCaseAssociation.get(pane);

    // Iterate through HashMap entries(Key-Value pairs)
    while (it.hasNext() && !find) {
      Map.Entry me = (Map.Entry) it.next();
      navireTemp = (Navire) me.getKey();
      List<Case> casesOccupees = navireTemp.getCaseOccupees();
      Equipage equipage = (Equipage) me.getValue();
      String aN = equipage.getAttaquant().getName();
      String dN = equipage.getDefenseur().getName();
      for (Case tempCase : casesOccupees) {
        if (laCase.getX() == tempCase.getX() && laCase.getY() == tempCase.getY()) {
          find = true;
          navire = navireTemp;
          break;
        }
      }
    }
    return navire;
  }




  /*private EventHandler<MouseEvent> drawShipPrevision()
  {
    return event -> {
      if (shipSelected != null) {
        Navire navire = shipSelected;
        Node source = (Node) event.getSource();
        ArrayList<Pane> actionPaneList = new ArrayList<>();
        boolean positionable;
        positionable = isPositionable(navire, source, actionPaneList);
        for (Pane pane : actionPaneList) {
          if (positionable) {
            pane.setStyle("-fx-background-color: grey");
          } else {
            pane.setStyle("-fx-background-color: red");
          }
        }
      }
    };
  }*/


  private void isPositionable(Navire navire, Node source)
  {
    int panePosition;
    int oldPanePosition;
    int[] listMovement = {1, -1, NB_CASES, -NB_CASES};
    sameX = true;
    //System.out.println("click position");
    ArrayList<Pane> tempPaneList = new ArrayList<>();
    for (int move : listMovement) {
      int xCoord = -1;
      for (Case laCase : navire.getCaseOccupees()) {
        if (sameX) {
          if (xCoord == -1) {
            xCoord = laCase.getX();
          } else {
            sameX = (xCoord == laCase.getX());
            xCoord = laCase.getX();
          }
        }
        oldPanePosition = laCase.getX() * NB_CASES + laCase.getY() + 1;
        Pane oldPane = (Pane) ourGameGrid.getChildren().get(oldPanePosition);
        //System.out.println(caseIsAccessible(oldPane, move));
        if(caseIsAccessible(oldPane,move)) {
          panePosition = laCase.getX() * NB_CASES + laCase.getY() + 1 + move;
          Pane pane = (Pane) ourGameGrid.getChildren().get(panePosition);
          if (ourPaneCaseAssociation.get(pane).getStatus().equals(Status.VIDE)) {
            tempPaneList.add(pane);
          }
        }
      }
      //System.out.println("sameX : " + sameX);
      if (Math.abs(move) == 1) {
        if (sameX) {
          if (tempPaneList.size() == Math.abs(move)) {
            actionPaneList.addAll(tempPaneList);
          }
        } else {
          if (tempPaneList.size() == navire.getCaseOccupees().size()) {
            actionPaneList.addAll(tempPaneList);
          }
        }
      } else {
        if (sameX) {
          if (tempPaneList.size() == navire.getCaseOccupees().size()) {
            actionPaneList.addAll(tempPaneList);
          }
        } else {
          if (tempPaneList.size() == 1) {
            actionPaneList.addAll(tempPaneList);
          }
        }
      }
      tempPaneList.clear();
    }
  }

  private boolean caseIsAccessible(Pane pane, int move)
  {
    boolean result = false;
    int panePosition = (GridPane.getColumnIndex(pane)) * NB_CASES + (GridPane.getRowIndex(pane) + 1);
    int tempPosition = (GridPane.getColumnIndex(pane)) * NB_CASES + (GridPane.getRowIndex(pane) + 1 + move);
    if (tempPosition > 0 && tempPosition <= 100) {
      if (!(tempPosition % NB_CASES == 0 && panePosition % NB_CASES == 1)
          && !(tempPosition % NB_CASES == 1 && panePosition % NB_CASES == 0)) {
        result = true;
      }
    }
    return result;
  }
}
