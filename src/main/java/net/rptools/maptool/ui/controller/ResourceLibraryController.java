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

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import net.rptools.maptool.AppConfig;
import net.rptools.maptool.general.event.GUIStartupEvent;

public class ResourceLibraryController {

  @FXML // ResourceBundle that was given to the FXMLLoader
  private ResourceBundle resources;

  @FXML // URL location of the FXML file that was given to the FXMLLoader
  private URL location;

  @FXML // fx:id="resourceDirectory"
  private TreeView<ResourceDirectory> resourceDirectory; // Value injected by FXMLLoader

  @FXML // fx:id="resourceImagePanel"
  private TilePane resourceImagePanel; // Value injected by FXMLLoader

  /** Parameters used for snapshot for drag and drop. */
  private SnapshotParameters dndSnapshotParameters;

  @Inject AppConfig appConfig;

  @Inject EventBus eventBus;

  @FXML // This method is called by the FXMLLoader when initialization is complete
  void initialize() {
    assert resourceDirectory != null
        : "fx:id=\"resourceDirectory\" was not injected: check your FXML file 'ResourceLibrary.fxml'.";
    assert resourceImagePanel != null
        : "fx:id=\"resourceImagePanel\" was not injected: check your FXML file 'ResourceLibrary.fxml'.";

    assert appConfig != null : "appConfig was not injected.";
    assert eventBus != null : "eventBus was not injected.";

    eventBus.register(this);

    dndSnapshotParameters = new SnapshotParameters();
    dndSnapshotParameters.setFill(Color.TRANSPARENT);
  }

  @Subscribe
  private void handleGUIStartup(GUIStartupEvent event) {
    resourceDirectory.setRoot(buildDirectoryTree(appConfig.getResourceLibraryDir().toFile()));
    resourceDirectory.setShowRoot(false);

    resourceDirectory.setCellFactory(
        tv ->
            new TreeCell<>() {
              @Override
              public void updateItem(ResourceDirectory resourceDirectory, boolean empty) {
                super.updateItem(resourceDirectory, empty);
                if (empty) {
                  setText(null);
                } else {
                  setText(resourceDirectory.getName());
                }
              }
            });

    resourceDirectory
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (ob, oldv, newv) -> {
              getResourceDirectoryImages(newv.getValue().getPath());
            });
  }

  /**
   * Creates a tree of the specified directory with each directory as a node.
   *
   * @param dir The {@link File} that points to the directory.
   * @return a tree containing nodes that contain the subdirectories.
   */
  private TreeItem<ResourceDirectory> buildDirectoryTree(File dir) {
    var root = new TreeItem<>(new ResourceDirectory(dir.getName(), dir.toPath()));

    for (File file : dir.listFiles(File::isDirectory)) {
      root.getChildren().add(buildDirectoryTree(file));
    }

    return root;
  }

  /**
   * Updates the images for the selected directory
   *
   * @param dir The directory that contains the images.
   */
  private void getResourceDirectoryImages(Path dir) {
    var images = new ArrayList<ImageView>();
    for (File file : dir.toFile().listFiles(this::isImageFIleFilter)) {
      images.add(getImageView(file));
    }

    if (images.size() > 0) {
      resourceImagePanel.getChildren().setAll(images.toArray(new ImageView[images.size()]));
    }
  }

  private ImageView getImageView(File file) {
    Image img = new Image("file://" + file.getAbsolutePath(), true);
    ImageView imageView = new ImageView(img);
    imageView.setPreserveRatio(true);
    imageView.setFitWidth(50);

    imageView.setOnDragDetected(
        event -> {
          Dragboard dboard = imageView.startDragAndDrop(TransferMode.ANY);

          ClipboardContent clipboardContent = new ClipboardContent();

          clipboardContent.putImage(imageView.snapshot(dndSnapshotParameters, null));

          dboard.setContent(clipboardContent);

          event.consume();
        });

    imageView.setOnDragDone(Event::consume);

    return imageView;
  }

  /**
   * Returns <code>true</code> if the passed in {@link File} matches one that can be loaded as an
   * image.
   *
   * @param file the {@link File} to check.
   * @return <code>true</code> if the can be loaded as an image.
   */
  private boolean isImageFIleFilter(File file) {
    // TODO: This is a bit naive but will do for our purposes at the moment.
    if (file.isFile()) {
      String fn = file.getName().toLowerCase();
      if (fn.endsWith(".png")
          || fn.endsWith("jpg")
          || fn.endsWith("jpeg")
          || fn.endsWith("gif")
          || fn.endsWith("mpg")) {
        return true;
      }
    }

    return false;
  }
}
