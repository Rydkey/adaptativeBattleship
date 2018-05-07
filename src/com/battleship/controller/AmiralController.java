package com.battleship.controller;

import com.battleship.model.Equipe;
import com.battleship.model.Partie;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.net.URL;
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

  @Override
  public void initialize(URL location, ResourceBundle resources)
  {
    for (int i = 0; i < NB_CASES; i++)
      for (int j = 0; j < NB_CASES; j++) {
        Pane pane = new Pane();
        pane.setOnMouseClicked(this.click());
        gameMainGrid.add(pane, i, j);
      }
  }
}
