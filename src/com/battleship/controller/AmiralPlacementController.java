package com.battleship.controller;

import com.battleship.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.InputMethodEvent;
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
import java.util.*;

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
  private Matelot joueurs[] = new Matelot[6];
  private Partie partie;
  private Equipe equipe;
  private HashMap<Rectangle, Navire> rectangleNavireAssociation;
  private HashMap<Pane, Case> paneCaseAssociation;
  private ArrayList<Rectangle> unselectableShip;
  private Rectangle shipSelected;
  private boolean orientation;
  private Plateau plateau;

  public void endGame()
  {
    System.out.println("end");
  }

  /**
   * place le bateau sur le plateau.
   */
  public EventHandler<MouseEvent> placementShip()
  {
    return event -> {
      if (shipSelected != null) {
        Navire navire = rectangleNavireAssociation.get(shipSelected);
        Node source = (Node) event.getSource();
        ArrayList<Pane> tempPaneList = new ArrayList<>();
        boolean positionable;
        positionable = isPositionable(navire, source, tempPaneList);
        if (positionable) {
          for (Pane pane : tempPaneList) {
            shipSelected.setFill(Color.GREY);
            paneCaseAssociation.get(pane).setStatus(Status.NAVIRE);
            unselectableShip.add(shipSelected);
            rectangleNavireAssociation.get(shipSelected).getCaseOccupees().add(paneCaseAssociation.get(pane));
          }
          shipSelected = null;
          partyIsReady();
        }
      }
    };
  }

  private void partyIsReady()
  {
    boolean allShipPlaced = true;
    for (Rectangle rectangle : rectangleNavireAssociation.keySet()) {
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
        Navire navire = rectangleNavireAssociation.get(shipSelected);
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
    //System.out.println("getOpPlayer function");
    return event -> {
      //System.out.println("getOpPlayer function event");
      ComboBox combo = (ComboBox) event.getSource();
      ArrayList<Matelot> temp = new ArrayList<>();
      for (Matelot matelot : joueurs) {
        if (matelot != null) {
          if (GridPane.getColumnIndex(combo) == 0) {
            if (!(matelot instanceof Defenseur)) {
              temp.add(matelot);
            }
          } else {
            if (!(matelot instanceof Attaquant)) {
              temp.add(matelot);
            }
          }
        }
      }
      combo.setItems(FXCollections.observableArrayList(temp));
      combo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
      {

        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
        {
          System.out.println("enter changed function");
          //System.out.println("old value "+oldValue);
          //System.out.println("new value "+newValue);
          Navire n = null;
          int i =0;
          //System.out.println(GridPane.getRowIndex(combo));
          for (Map.Entry<Rectangle, Navire> navire : rectangleNavireAssociation.entrySet()) {
            if(i==GridPane.getRowIndex(combo)){
              n = navire.getValue();
              break;
            }
            i++;
          }
          if (newValue.intValue() >= 0) {
            Attaquant attaquant = new Attaquant(temp.get(newValue.intValue()).getName());
            Defenseur defenseur = new Defenseur(temp.get(newValue.intValue()).getName());
            System.out.println(attaquant);
            if (GridPane.getColumnIndex(combo) == 0) {
              System.out.println("enter attaquant section");
              equipe.getAssignationNavireEquipage().get((n)).setAttaquant(attaquant);
              for (int joueur = 0; joueur < joueurs.length; joueur++) {
                if (joueurs[joueur] == temp.get(newValue.intValue())){
                  joueurs[joueur] = attaquant;
                }
              }
            }else{
              System.out.println("enter defender section");
              equipe.getAssignationNavireEquipage().get((n)).setDefenseur(defenseur);
              for (int joueur = 0; joueur < joueurs.length; joueur++) {
                if (joueurs[joueur] == temp.get(newValue.intValue())){
                  joueurs[joueur] = defenseur;
                }
              }
            }
            HashMap assignation = equipe.getAssignationNavireEquipage();
            for (int joueur = 0; joueur < joueurs.length; joueur++) {
              System.out.println("checking player : " + joueurs[joueur]);
              if (joueurs[joueur] instanceof Defenseur || joueurs[joueur] instanceof Attaquant){
                System.out.println("find player");
                Iterator it = assignation.entrySet().iterator();
                Boolean noAssignation = true;
                System.out.println("cheking boat");
                while (it.hasNext()) {
                  Map.Entry pair = (Map.Entry)it.next();
                  System.out.println("boat "+pair.getKey());
                  System.out.println(" boat attaquant "+((Equipage)pair.getValue()).getAttaquant());
                  System.out.println(" boat attaquant "+((Equipage)pair.getValue()).getDefenseur());
                  if(((Equipage)pair.getValue()).getAttaquant() == joueurs[joueur] || ((Equipage)pair.getValue()).getDefenseur() == joueurs[joueur]){
                    System.out.println("found boat");
                    //System.out.println("attaquant "+((Equipage)pair.getValue()).getAttaquant());
                    //System.out.println("Defenseur "+((Equipage)pair.getValue()).getDefenseur());
                    noAssignation = false;
                    break;
                  }
                }
                //it.remove(); // avoids a ConcurrentModificationException
                if(noAssignation){
                  System.out.println("found lazy");
                  Matelot matelot = new Matelot(joueurs[joueur].getName());
                  joueurs[joueur] =  null;
                  joueurs[joueur] = matelot;
                }
              }
              System.out.println("#######################");
            }
            //System.out.println(joueurs);
            System.out.println("----------------------------------------");
          }
        }
      });
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
    Navire navireCorrespondant = rectangleNavireAssociation.get(clicked);
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
    rectangleNavireAssociation = new HashMap<>();
    paneCaseAssociation = new HashMap<>();
    unselectableShip = new ArrayList<>();
    orientation = false;
    equipe = new Equipe(NomEquipe.EQUIPEA);
    plateau = new Plateau();
    for (int i = 0; i < 3; i++) {
      joueurs[i] = new Matelot();
      joueurs[i].setName("joueur" + i);
    }
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
        combo.setOnMouseClicked(this.getOpPlayer());
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

    rectangleNavireAssociation.put(cuirasseRectangle, c1);
    rectangleNavireAssociation.put(croiseurRectangle1, cr1);
    rectangleNavireAssociation.put(croiseurRectangle2, cr2);
    rectangleNavireAssociation.put(torpilleurRectangle1, t1);
    rectangleNavireAssociation.put(torpilleurRectangle2, t2);
    rectangleNavireAssociation.put(torpilleurRectangle3, t3);
    rectangleNavireAssociation.put(sousMarinRectangle1, s1);
    rectangleNavireAssociation.put(sousMarinRectangle2, s2);
    rectangleNavireAssociation.put(sousMarinRectangle3, s3);
    rectangleNavireAssociation.put(sousMarinRectangle4, s4);


    for (Map.Entry<Rectangle, Navire> navire : rectangleNavireAssociation.entrySet()) {
      equipe.getAssignationNavireEquipage().put(navire.getValue(), new Equipage());
    }
  }

  private EventHandler<? super InputMethodEvent> test()
  {
    return event -> {
      System.out.println("bite");
    };
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
