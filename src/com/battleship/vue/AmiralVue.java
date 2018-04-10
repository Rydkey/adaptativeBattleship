package com.battleship.vue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AmiralVue extends Application
{
  @Override
  public void start(Stage primaryStage){
    primaryStage.setTitle("HelloWord");
    Group root = new Group();
    Scene scene = new Scene(root,300,250);
    VBox fleets = new VBox(40);
    for (int i = 0; i < 2; i++) {
      VBox  fleet = new VBox(10);
      for (int j = 1; j < 5; j++) {
        HBox ships = new HBox(10);
        for (int k = j; k > 0 ; k--) {
          HBox ship = new HBox(2);
          for (int l = 0; l < 5-j; l++) {
            Rectangle shipPart = new Rectangle(10,10,Color.LIGHTGREEN);
            ship.getChildren().add(shipPart);
          }
          ships.getChildren().add(ship);
        }
        fleet.getChildren().add(ships);
      }
      fleets.getChildren().add(fleet);
    }

    VBox battlefield = new VBox();

    VBox crew = new VBox();
    root.getChildren().add(fleets);
    root.getChildren().add(battlefield);
    root.getChildren().add(crew);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
