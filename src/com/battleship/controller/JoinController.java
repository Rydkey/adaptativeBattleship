package com.battleship.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class JoinController
{
  public Button btnJoin;

  public void join(MouseEvent mouseEvent) throws IOException
  {
    Parent loader = FXMLLoader.load(getClass().getResource("/fxml/joueurView.fxml"));
    Stage stage = (Stage) btnJoin.getScene().getWindow();
    Scene scene = new Scene(loader);
    stage.setScene(scene);
  }
}
