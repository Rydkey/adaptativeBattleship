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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

//import com.battleship.vue.AmiralView;

public class AmiralController extends BaseController implements Initializable {

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
  private Partie partie;
  private Equipe equipe;
  private HashMap<Rectangle, Navire> navireRectangleAssociation;
  private Rectangle shipSelected;


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
    EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event)
      {
        Node source = (Node) event.getSource();
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        System.out.println("col : " + colIndex);
        event.consume();
      }
    };
    return mousePositionHandler;
  }

  public void selectShip(MouseEvent event)
  {
    Rectangle clicked = (Rectangle) event.getSource();
    Navire navireCorrespondant = navireRectangleAssociation.get(clicked);
    if (navireCorrespondant != null) {
      if (shipSelected != null) {
        shipSelected.setFill(Color.GREY);
      }
      clicked.setFill(Color.GREEN);
      shipSelected = clicked;
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    navireRectangleAssociation = new HashMap<>();
    for (int i = 0; i < NB_CASES; i++)
      for (int j = 0; j < NB_CASES; j++) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(this.click());
        gameMainGrid.add(pane, i, j);
      }
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
}
