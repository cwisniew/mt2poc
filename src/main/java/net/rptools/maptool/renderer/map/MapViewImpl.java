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
package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.Closeable;
import java.io.IOException;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;
import net.rptools.maptool.renderer.ui.controls.ResizableCanvas;

/** Class that implements a view of a {@link GameMap}. */
public class MapViewImpl implements MapView, Closeable {

  /** The {@Link AnchorPane} that will hold the <code>MapView</code> nodes. */
  private AnchorPane anchorPane;

  /** The {@link StackPane} that holds the different rendered layers of the map. */
  private StackPane stackPane;

  /** The {@link GameMap} this view "looks in to". */
  private GameMap gameMap;

  /** The {@link EventBus} used to listen for {@link GameMap} changes. */
  private EventBus eventBus;

  /** The amount the view is translated. */
  private Point2D transation = new Point2D(0.0, 0.0);

  /** The scaling factor for the view. */
  private double scale = 1.0;

  /** The bounds for the view into the {@link GameMap}. */
  private Rectangle2D viewBounds;

  /** The background image rendered from the {@link GameMap}. */
  private Canvas backgroundCanvas;

  /** The x co-ordinate of the mouse. */
  private double mouseX;

  /** The y co-ordinate of the mouse. */
  private double mouseY;


  private boolean dragging = false;


  /** The pane to draw the tokens on */
  Pane tokenPane;

  /**
   * Creates a new <code>MapViewImpl</code> object.
   *
   * @param gameMap The map that this <code>GameMapView</code> is a view of.
   * @param eventBus The event bus that changes to maps are registered on.
   */
  @Inject
  public MapViewImpl(GameMap gameMap, EventBus eventBus) {
    this.gameMap = gameMap;
    this.eventBus = eventBus;
    this.eventBus.register(this);

    stackPane = new StackPane();
    anchorPane = new AnchorPane();
    tokenPane = new Pane();

    anchorPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
    AnchorPane.setTopAnchor(stackPane, 0.0);
    AnchorPane.setBottomAnchor(stackPane, 0.0);
    AnchorPane.setLeftAnchor(stackPane, 0.0);
    AnchorPane.setRightAnchor(stackPane, 0.0);

    anchorPane.getChildren().add(stackPane);

    backgroundCanvas = new ResizableCanvas();
    backgroundCanvas.widthProperty().bind(stackPane.widthProperty());
    backgroundCanvas.heightProperty().bind(stackPane.heightProperty());

    stackPane.widthProperty().addListener(w -> viewResized());
    stackPane.heightProperty().addListener(h -> viewResized());

    stackPane.getChildren().add(backgroundCanvas);

    backgroundCanvas.addEventHandler(
        MouseEvent.MOUSE_MOVED,
        e -> {
          mouseX = e.getX();
          mouseY = e.getY();
          render();
        }
    );

    tokenPane.prefHeight(Double.MAX_VALUE);
    tokenPane.prefWidth(Double.MAX_VALUE);
    tokenPane.getChildren().add(new Circle(50.0, 50.0, 49.0));

    //stackPane.getChildren().add(tokenPane);


    backgroundCanvas.setOnDragOver(event -> {
      System.out.println("Here in drag over");
      if (event.getGestureSource() != tokenPane) {
        if (event.getDragboard().hasImage()) {
          event.acceptTransferModes(TransferMode.ANY);
          dragging = true;
          event.consume();
          render();
        }
      }
    });

    backgroundCanvas.setOnDragEntered(event -> {
      System.out.println("Here in drag entered");
      event.consume();
    });

    backgroundCanvas.setOnDragExited(event -> {
      System.out.println("Here in drag exited");
      dragging = false;
      event.consume();
      render();
    });


    backgroundCanvas.setOnDragDropped(event -> {
      System.out.println("Here in drag dropped");
      Dragboard db = event.getDragboard();
      boolean success = false;
      if (db.hasImage()) {
        System.out.println("Dropped image.");
        success = true;
      }

      event.setDropCompleted(success);
      event.consume();
    });
  }

  @Override
  public Pane getParentNode() {
    return anchorPane;
  }

  /**
   * Handles
   *
   * @param mapUpdateEvent
   */
  @Subscribe
  void handleMapUpdateEvent(MapUpdateEvent mapUpdateEvent) {
    if (gameMap.getId() == mapUpdateEvent.getMapId()) {
      render();
    }
  }

  /**
   * Returns the {@link GameMap} that this is a view of.
   *
   * @return the game map this is a view of.
   */
  public GameMap getGameMap() {
    return gameMap;
  }

  @Override
  public void setScale(double scaleFactor) {
    scale = scaleFactor;
    render();
  }

  /** Handle resizing of the */
  private void viewResized() {
    double width = stackPane.getWidth();
    double height = stackPane.getHeight();

    viewBounds = new Rectangle2D(-width / 2, -height / 2, width, height);

    transation = new Point2D(width / 2, height / 2);
    render();
  }

  /** Renders the content of the Map View. */
  private void render() {
    renderBackground();
  }

  /** Render the view of the map. */
  private void renderBackground() {
    GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
    double width = backgroundCanvas.getWidth();
    double height = backgroundCanvas.getHeight();

    if (gameMap.getBackgroundTexture().isPresent()) {
      gc.save();

      gc.translate(transation.getX(), transation.getY());
      gc.scale(scale, scale);
      Image backgroundTexture = gameMap.getBackgroundTexture().get();

      double textureWidth = backgroundTexture.getWidth();
      double textureHeight = backgroundTexture.getHeight();

      int numXTextures = (int) Math.ceil(width / textureWidth) + 1;
      int numYTextures = (int) Math.ceil(height / textureHeight) + 1;

      double startX = -(numXTextures / 2 * textureWidth);
      double startY = -(numYTextures / 2 * textureHeight);

      for (double x = startX; x <= width; x += textureWidth) {
        for (double y = startY; y <= height; y += textureHeight) {
          gc.drawImage(backgroundTexture, x, y);
        }
      }

      gc.restore();

      if (dragging) {
        gc.setFill(Color.RED);
        gc.fillOval(mouseX - 5, mouseY - 5, 9, 9);
      }
    } else {
      gc.clearRect(0, 0, width, height);
      gc.setStroke(Color.RED);
      gc.strokeLine(0, 0, width, height);
      gc.strokeLine(0, height, width, 0);
    }
  }

  @Override
  public void close() throws IOException {
    eventBus.unregister(this);
  }
}
