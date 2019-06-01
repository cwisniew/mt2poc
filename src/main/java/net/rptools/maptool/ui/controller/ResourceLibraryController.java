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

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;
import javafx.scene.layout.TilePane;
import net.rptools.maptool.AppConfig;

public class ResourceLibraryController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="resourceDirectory"
  private TreeView<?> resourceDirectory; // Value injected by FXMLLoader

  @FXML // fx:id="resourceImagePanel"
  private TilePane resourceImagePanel; // Value injected by FXMLLoader


  @Inject
  AppConfig appConfig;

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert resourceDirectory != null
        : "fx:id=\"resourceDirectory\" was not injected: check your FXML file 'ResourceLibrary.fxml'.";
    assert resourceImagePanel != null
        : "fx:id=\"resourceImagePanel\" was not injected: check your FXML file 'ResourceLibrary.fxml'.";

    assert appConfig != null : "appConfig was not injected.";
  }
}
