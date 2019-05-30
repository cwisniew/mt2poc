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
package net.rptools.maptool.renderer.ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

/** Controller class for the side panel. */
public class SidePanelController {

  /** The names of the resource files that contain the images we will be using. */
  private static String[] imageNames = {
    "Dragon.png",
    "Eagle.png",
    "Elf.png",
    "Hero.png",
    "Mage.png",
    "Mystic.png",
    "Troll.png",
    "Wolf.png"
  };

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="imageVBox"
  private VBox imageVBox; // Value injected by FXMLLoader

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert imageVBox != null
        : "fx:id=\"imageVBox\" was not injected: check your FXML file 'Untitled'.";

    for (String iname : imageNames) {
      Image image = new Image(getClass().getResourceAsStream("/assets/images/" + iname));

      ImageView imageView = new ImageView(image);
      imageView.setFitWidth(80);
      imageView.setPreserveRatio(true);

      imageView.setOnDragDetected(
          event -> {
            Dragboard dboard = imageView.startDragAndDrop(TransferMode.ANY);

            ClipboardContent clipboardContent = new ClipboardContent();
            clipboardContent.putImage(image);

            dboard.setContent(clipboardContent);

            event.consume();
          });

      imageView.setOnDragDone(
          event -> {
            event.consume();
          });

      imageVBox.getChildren().add(imageView);
    }
  }
}
