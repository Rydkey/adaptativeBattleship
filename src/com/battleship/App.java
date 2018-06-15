package com.battleship;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import com.battleship.vue.AmiralView;

public class App extends Application
{

  private static final String VUE_JOUEUR  = "/fxml/joueurView.fxml";
  private static final String VUE_AMIRAL  = "/fxml/amiralPlacementView.fxml";
  private static final String VUE_CONNECT  = "/fxml/connectionView.fxml";


  public static void main(String[] args)
  {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception
  {
    /*amiral view*/
    //Parent sceneAmiral = FXMLLoader.load(getClass().getResource(VUE_AMIRAL));
    /*player view*/
    //Parent sceneJoueur = FXMLLoader.load(getClass().getResource(VUE_JOUEUR));
    /*connection view*/
    Parent sceneConnection = FXMLLoader.load(getClass().getResource(VUE_CONNECT));
    primaryStage.setTitle("Welcome");
    primaryStage.setScene(new Scene(sceneConnection, 1280, 720));
    primaryStage.show();
  }
}
