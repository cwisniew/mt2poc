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
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/** Controller class for the side panel. */
public class SidePanelController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="imagesTitlePane"
  private TitledPane imagesTitlePane; // Value injected by FXMLLoader

  @FXML // fx:id="resourceLibrary"
  private Parent resourceLibrary;

  @FXML // fx:id="resourceLibraryController"
  private ResourceLibraryController resourceLibraryController;

  @FXML // fx:id="layersListView"
  private ListView<String> layersListView;

  @FXML // fx:id="lastRenderMs"
  private Label lastRenderMs;

  /** The names of the resource files that contain the images we will be using. */
  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert imagesTitlePane != null
        : "fx:id=\"imagesTitlePane\" was not injected: check your FXML file 'Untitled'.";

    assert resourceLibrary != null
        : "fx:id=\"resourceLibrary\" was not injected: check your FXML file 'Untitled'.";

    assert resourceLibraryController != null
        : "fx:id=\"resourceLibraryController\" was not injected: check your FXML file 'Untitled'.";

    assert layersListView != null
        : "fx:id=\"layersListView\" was not injected: check your FXML file 'Untitled'.";

    assert lastRenderMs != null
        : "fx:id=\"lastRenderMs\" was not injected: check your FXML file 'Untitled'.";
  }

  public void setLastRenderMs(long ms) {
    String timeTaken = ms + " ms";
    lastRenderMs.setText(timeTaken);
    System.out.println(timeTaken);
  }
}
