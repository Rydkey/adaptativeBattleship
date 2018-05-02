package com.battleship.controller;

import com.battleship.model.Equipe;
import com.battleship.model.Partie;
import com.battleship.vue.AmiralView;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AmiralController implements EventHandler<MouseEvent> {

  private AmiralView amiralView;
  private Partie partie;
  private Equipe equipe;

  public AmiralController(Partie partie, Stage stage, Equipe equipe) {
    this.equipe = equipe;
    this.partie = partie;
    BorderPane root = new BorderPane();
    Scene scene;
    scene = new Scene(root, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight(), Color.BLACK);
    stage.setScene(scene);
    stage.setTitle("Test Amiral Vue");
//    stage.getIcons().add(new Image("Assets/img/Schooner.png"));
    stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    stage.show();
    this.amiralView = new AmiralView(stage, partie, equipe);
    amiralView.setController(this);
    amiralView.initStage();
  }

  @Override
  public void handle(MouseEvent event) {
  }
}
