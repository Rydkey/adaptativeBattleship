package com.battleship.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateController
{
  public Button btnCreate;
  public void create(MouseEvent mouseEvent) throws IOException
  {
    Parent loader = FXMLLoader.load(getClass().getResource("/fxml/amiralPlacementView.fxml"));
    Stage stage = (Stage) btnCreate.getScene().getWindow();
    Scene scene = new Scene(loader);
    stage.setScene(scene);
  }
}
