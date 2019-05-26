package net.rptools.maptool.renderer.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class SidePanelController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="ImageGridPane"
  private GridPane ImageGridPane; // Value injected by FXMLLoader

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert ImageGridPane != null : "fx:id=\"ImageGridPane\" was not injected: check your FXML file 'Untitled'.";

  }
}

