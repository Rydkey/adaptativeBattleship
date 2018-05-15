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
  private Plateau ourPlateau;
  private Plateau ennemyPlateau;
  private Equipe e = new Equipe();
  private ArrayList<Joueur> lJ = new ArrayList<>();
  private HashMap<Navire,Equipage> lE = new HashMap<>();


  /*
   * fonction d'initialisation
   * */
  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
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
        if(i ==1 && (j==1 || j==3 || j==5 || j==7)){
          ourPaneCaseAssociation.get(ourPane).setStatus(Status.NAVIRE);
        }
        ourPane.setOnMouseClicked(this.interactionShip());
        //pane.setOnMouseExited(this.refreshColor());
        ourGameGrid.add(ourPane, i, j);
        ennemiesGameGrid.add(ennemyPane,i,j);
      }
    }
    Cuirasse c1 = new Cuirasse();
    Croiseur cr1 = new Croiseur();
    Croiseur cr2 = new Croiseur();
    Torpilleur t1 = new Torpilleur();
    Torpilleur t2 = new Torpilleur();
    Torpilleur t3 = new Torpilleur();
    SousMarin s1 = new SousMarin();
    s1.getCaseOccupees().add(ourPlateau.getLesCases()[1][1]);
    SousMarin s2 = new SousMarin();
    s2.getCaseOccupees().add(ourPlateau.getLesCases()[1][3]);
    SousMarin s3 = new SousMarin();
    s3.getCaseOccupees().add(ourPlateau.getLesCases()[1][5]);
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
    lJ.add(d1);
    Attaquant a2 = new Attaquant();
    a2.setName("a2");
    lJ.add(a2);

    Equipage e1 = new Equipage(a1,d1);
    lE.put(s1,e1);
    lE.put(s2,e1);
    //System.out.println(e1);
    Equipage e2 = new Equipage(a2,d1);
    lE.put(s3,e2);
    lE.put(s4,e2);
    //System.out.println(e2);

    e.setListeJoueur(lJ);
    e.setAssignationNavireEquipage(lE);
    //System.out.println(e);

    Timer timer = new Timer();
    timer.schedule(new refreshView(), 0, 500);

  }

  public EventHandler<MouseEvent> interactionShip()
  {
    return event -> {
      //System.out.println(event.getSource());
      Pane pane = (Pane)event.getSource();
      shipSelected = getNavireOfCase(pane);
      //System.out.println(ennemiesGameGrid.getChildren().get(0));
      //System.out.println(shipSelected);
      if (shipSelected != null) {
        for(int i = 1; i< 65;i++){
          Pane ennemyPane = (Pane) ennemiesGameGrid.getChildren().get(i);
          System.out.println(ennemyPane.getStyle());
          //ennemyPane.setStyle("-fx-background-color:  white");
        }
        for(Case laCase : shipSelected.getCaseOccupees()){
          for(int i = -1;i<2;i++){
            for(int j = -1; j<2;j++){
              if(laCase.getX()+i < NB_CASES && laCase.getX()+i > -1 && laCase.getY()+j<NB_CASES && laCase.getY()+j > -1){
                //System.out.println((laCase.getY()+i) * NB_CASES + laCase.getX()+j+1);
                Pane ennemyPane = (Pane) ennemiesGameGrid.getChildren().get((laCase.getX()+i) * NB_CASES + laCase.getY()+j+1);
                //System.out.println(ennemyPane);
                ennemyPane.setStyle("-fx-background-color:  orange");
              }
            }
          }
        }
      }
    };
  }

  private boolean caseVerification(Pane temp)
  {
    boolean result;
    switch (ourPaneCaseAssociation.get(temp).getStatus()) {
      case VIDE:
        result = true;
        break;
      default:
        result = false;
        break;
    }
    return result;
  }


  class refreshView extends TimerTask {
    public void run() {
      for (Node children : ourGameGrid.getChildren() ) {
        Case tempCase = ourPaneCaseAssociation.get(children);
        if (tempCase != null) {
          if (caseVerification((Pane) children)) {
            children.setStyle("-fx-color: white");
          } else {
            // Getting a Set of Key-value pairs
            Set entrySet = e.getAssignationNavireEquipage().entrySet();
            // Obtaining an iterator for the entry set
            Iterator it = entrySet.iterator();

            // Iterate through HashMap entries(Key-Value pairs)
            while(it.hasNext()){
              Map.Entry me = (Map.Entry)it.next();
              Navire navire = (Navire)me.getKey();
              List<Case> casesOccupees = navire.getCaseOccupees();
              Equipage equipage = (Equipage)me.getValue();
              String aN = equipage.getAttaquant().getName();
              String dN = equipage.getDefenseur().getName();
              for (Case laCase : casesOccupees){
                if(laCase.getX() == tempCase.getX() && laCase.getY() == tempCase.getY()){
                  if(aN.equals("a1") || dN.equals("a1")){
                    children.setStyle("-fx-background-color:  green");
                  }else{
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
  }
  public Navire getNavireOfCase(Pane pane)
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
}
