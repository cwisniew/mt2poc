package net.rptools.maptool.renderer.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainWindowController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="quitMenu"
  private MenuItem quitMenu; // Value injected by FXMLLoader

  @FXML // fx:id="mainBorderPane"
  private BorderPane mainBorderPane; // Value injected by FXMLLoader

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert quitMenu != null : "fx:id=\"quitMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
    assert mainBorderPane != null : "fx:id=\"mainBorderPane\" was not injected: check your FXML file 'MainScreen.fxml'.";

  }


  public void setMain(Pane pane) {
    mainBorderPane.setCenter(pane);
  }

  public  void setLeft(Pane pane) {
    mainBorderPane.setLeft(pane);
  }
}
