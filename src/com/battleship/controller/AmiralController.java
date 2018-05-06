package com.battleship.controller;

import com.battleship.model.Equipe;
import com.battleship.model.Partie;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

//import com.battleship.vue.AmiralView;

public class AmiralController implements EventHandler<MouseEvent>
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
  //  private AmiralView amiralView;
  private Partie partie;
  private Equipe equipe;

  @Override
  public void handle(MouseEvent event)
  {
    System.out.println("mouse click detected! " + event.getSource());
  }

  public void endGame()
  {
    System.out.println("end");
  }

  public void click(MouseEvent event)
  {
    Node source = (Node)event.getSource() ;
    Integer colIndex = GridPane.getColumnIndex(source);
    Integer rowIndex = GridPane.getRowIndex(source);
    System.out.printf(""+colIndex);
  }
}
