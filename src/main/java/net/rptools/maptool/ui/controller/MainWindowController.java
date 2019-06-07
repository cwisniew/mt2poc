/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * MapTool Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.maptool.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import net.rptools.maptool.map.view.MapView;

public class MainWindowController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="quitMenu"
  private MenuItem quitMenu; // Value injected by FXMLLoader

  @FXML // fx:id="mainBorderPane"
  private BorderPane mainBorderPane; // Value injected by FXMLLoader

  @FXML // fx:id="drawSquareButton"
  private Button drawSquareButton;

  /** The main {@link MapView}. */
  private MapView mapView;

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert quitMenu != null
        : "fx:id=\"quitMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
    assert mainBorderPane != null
        : "fx:id=\"mainBorderPane\" was not injected: check your FXML file 'MainScreen.fxml'.";
    assert drawSquareButton != null
        : "fx:id=\"drawSquareButton\" was not injected: check your FXML file 'Untitled'.";
  }

  public void setMapView(MapView view) {
    mainBorderPane.setCenter(view.getParentNode());
    mapView = view;
  }

  public void setLeft(Pane pane) {
    mainBorderPane.setLeft(pane);
  }

  @FXML
  private void handleQuitAction(ActionEvent event) {
    System.exit(0);
    Platform.exit();
  }

  @FXML
  void doPointerTool(MouseEvent event) {
    mapView.setPointerTool();
  }

  @FXML
  void doRectangleTool(MouseEvent event) {
    mapView.setRectangleTool();
  }
}
