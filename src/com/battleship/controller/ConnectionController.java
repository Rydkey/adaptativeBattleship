package com.battleship.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ConnectionController
{
  public Button btnQuit;
  public Button btnCreatePartie;
  public Button btnFindPartie;

  public void quit(MouseEvent mouseEvent)
  {
    System.exit(0);
  }

  public void createPartie(MouseEvent mouseEvent) throws IOException
  {
    Parent loader = FXMLLoader.load(getClass().getResource("/fxml/createView.fxml"));
    Stage stage = (Stage) btnFindPartie.getScene().getWindow();
    Scene scene = new Scene(loader);
    stage.setScene(scene);
  }

  public void findPartie(MouseEvent mouseEvent) throws IOException
  {
    Parent loader = FXMLLoader.load(getClass().getResource("/fxml/joinView.fxml"));
    Stage stage = (Stage) btnFindPartie.getScene().getWindow();
    Scene scene = new Scene(loader);
    stage.setScene(scene);
  }
}
