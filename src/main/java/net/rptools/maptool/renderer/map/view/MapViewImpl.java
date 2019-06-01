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
package net.rptools.maptool.renderer.map.view;

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
import net.rptools.maptool.renderer.map.GameMap;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;
import net.rptools.maptool.renderer.map.grid.Grid;
import net.rptools.maptool.renderer.map.grid.render.GridLine;
import net.rptools.maptool.renderer.map.grid.render.GridRendererFactory;
import net.rptools.maptool.renderer.ui.controls.ResizableCanvas;

/** Class that implements a view of a {@link GameMap}. */
public class MapViewImpl implements MapView, Closeable {

  /** The {@Link AnchorPane} that will hold the <code>MapView</code> nodes. */
  private AnchorPane anchorPane = new AnchorPane();

  /** The {@link StackPane} that holds the different rendered layers of the map. */
  private StackPane stackPane = new StackPane();

  /** The {@link GameMap} this view "looks in to". */
  private GameMap gameMap;

  /** The {@link EventBus} used to listen for {@link GameMap} changes. */
  private EventBus eventBus;

  /** The amount the view is translated. */
  private Point2D translation = new Point2D(0.0, 0.0);

  /** The scaling factor for the view. */
  private double scale = 1.0;

  /** The bounds for the view into the {@link GameMap}. */
  private Rectangle2D viewBounds;

  /** The background image rendered from the {@link GameMap}. */
  private Canvas backgroundCanvas = new ResizableCanvas();

  /** The x co-ordinate of the mouse. */
  private double mouseX;

  /** The y co-ordinate of the mouse. */
  private double mouseY;

  /** The x co-ordinate where the mouse button was pressed. */
  private double mousePressedX;

  /** The y co-ordinate where the mouse button was pressed. */
  private double mousePressedY;

  /** The pane that picks up user interactions when there is nothing else in front of it. */
  private Pane interactivePane = new Pane();

  /** The {@link Canvas} used for drawing the grid. */
  private Canvas gridCanvas = new ResizableCanvas();

  /** The factory class for obtaining a grid renderer. */
  @Inject private GridRendererFactory gridRendererFactory;

  /** Sets the details for drawing the grid line. */
  private GridLine gridLine = new GridLine();

  /** The view port that maps screen to map. */
  private MapViewPort mapViewPort;

  /**
   * Creates a new <code>MapViewImpl</code> object.
   *
   * @param gMap The map that this <code>GameMapView</code> is a view of.
   * @param eBus The event bus used to subscribe to map changes.
   * @param viewPort The viewport used to create a view into the map.
   */
  @Inject
  public MapViewImpl(GameMap gMap, EventBus eBus, MapViewPort viewPort) {
    gameMap = gMap;
    eventBus = eBus;
    mapViewPort = viewPort;

    eventBus.register(this);

    anchorPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
    AnchorPane.setTopAnchor(stackPane, 0.0);
    AnchorPane.setBottomAnchor(stackPane, 0.0);
    AnchorPane.setLeftAnchor(stackPane, 0.0);
    AnchorPane.setRightAnchor(stackPane, 0.0);

    anchorPane.getChildren().add(stackPane);

    backgroundCanvas.widthProperty().bind(stackPane.widthProperty());
    backgroundCanvas.heightProperty().bind(stackPane.heightProperty());

    stackPane.getChildren().add(backgroundCanvas);

    backgroundCanvas.addEventHandler(
        MouseEvent.MOUSE_MOVED,
        e -> {
          mouseX = e.getX();
          mouseY = e.getY();
          render();
        });

    stackPane.addEventHandler(
        MouseEvent.MOUSE_PRESSED,
        e -> {
          if (e.isPrimaryButtonDown()) {
            mousePressedX = e.getX();
            mousePressedY = e.getY();
            e.consume();
          }
        });

    stackPane.addEventHandler(
        MouseEvent.MOUSE_DRAGGED,
        e -> {
          if (e.isPrimaryButtonDown()) {

            final double currentMouseX = e.getX();
            final double currentMouseY = e.getY();

            // If the original click has higher X co-ordinate it was right of mouse so mouse is
            // moving left
            final boolean left = mousePressedX > currentMouseX;
            // If the original click has lower X co-ordinate it was left of mouse so mouse is moving
            // right
            final boolean right = mousePressedX < currentMouseX;
            // If the original click has higher Y co-ordinate it was below the mouse os mouse is
            // moving up
            final boolean up = mousePressedY > currentMouseY;
            // If the original click has lower Y co-ordinate it was above the mouse os mouse is
            // moving down
            final boolean down = mousePressedY < currentMouseY;

            if (left && up) {
              viewPort.panViewLeftUp();
            } else if (left && down) {
              viewPort.panViewLeftDown();
            } else if (right && up) {
              viewPort.panViewRightUp();
            } else if (right && down) {
              viewPort.panViewRightDown();
            } else if (left) {
              viewPort.panViewLeft();
            } else if (right) {
              viewPort.panViewRight();
            } else if (down) {
              viewPort.panViewDown();
            } else if (up) {
              viewPort.panViewUp();
            }

            e.consume();
            render();
          }
        });

    stackPane.setOnScroll(
        e -> {
          if (e.getDeltaY() > 0) {
            viewPort.zoomOut();
          } else {
            viewPort.zoomIn();
          }
          e.consume();
          render();
        });

    stackPane.getChildren().add(interactivePane);

    interactivePane.prefHeight(Double.MAX_VALUE);
    interactivePane.prefWidth(Double.MAX_VALUE);

    interactivePane.setOnDragOver(
        event -> {
          System.out.println("onDragOver");
          if (event.getGestureSource() != interactivePane) {
            if (event.getDragboard().hasImage()) {
              event.acceptTransferModes(TransferMode.ANY);
              event.consume();
              render();
            }
          }
        });

    interactivePane.setOnDragEntered(
        event -> {
          System.out.println("onDragEntered");
          event.consume();
        });

    interactivePane.setOnDragExited(
        event -> {
          System.out.println("onDragExited");
          event.consume();
          render();
        });

    interactivePane.setOnDragDropped(
        event -> {
          System.out.println("onDragDropped");
          Dragboard db = event.getDragboard();
          boolean success = false;
          if (db.hasImage()) {
            success = true;
          }

          event.setDropCompleted(success);
          event.consume();
        });

    gridCanvas.setMouseTransparent(true);

    gridCanvas.widthProperty().bind(stackPane.widthProperty());
    gridCanvas.heightProperty().bind(stackPane.heightProperty());

    stackPane.getChildren().add(gridCanvas);

    stackPane.widthProperty().addListener(w -> viewResized());
    stackPane.heightProperty().addListener(h -> viewResized());

    mapViewPort.setGameMap(gMap);
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

  @Override
  public void setGridLine(GridLine gridLine) {
    this.gridLine = gridLine;
  }

  @Override
  public GridLine getGridLine() {
    return gridLine;
  }

  @Override
  public MapViewPort getMapViewPort() {
    return mapViewPort;
  }

  /** Handle resizing of the */
  private void viewResized() {
    double width = stackPane.getWidth();
    double height = stackPane.getHeight();

    viewBounds = new Rectangle2D(-width / 2, -height / 2, width, height);

    translation = new Point2D(width / 2, height / 2);

    mapViewPort.adjustDisplaySize(new Rectangle2D(0, 0, width, height));
    render();
  }

  /** Renders the content of the Map View. */
  private void render() {
    // Don't render until both width and height are set
    if (stackPane.getWidth() != 0 && stackPane.getHeight() != 0) {
      renderBackground();
      renderGrid();
    }
  }

  /** Render the view of the map. */
  private void renderBackground() {
    GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
    double width = backgroundCanvas.getWidth();
    double height = backgroundCanvas.getHeight();

    if (gameMap.getBackgroundTexture().isPresent()) {
      Image backgroundTexture = gameMap.getBackgroundTexture().get();
      BackgroundTextureRenderer backgroundTextureRenderer = new BackgroundTextureRenderer();
      backgroundTextureRenderer.render(backgroundCanvas, backgroundTexture, mapViewPort);
    } else {
      gc.clearRect(0, 0, width, height);
      gc.setStroke(Color.RED);
      gc.strokeLine(0, 0, width, height);
      gc.strokeLine(0, height, width, 0);
    }
  }

  /** Renders the grid for the map. */
  @SuppressWarnings("unchecked")
  private void renderGrid() {
    if (gameMap.getGrid().isPresent()) {
      Grid grid = gameMap.getGrid().get();
      var grOptional = gridRendererFactory.rendererFor(grid);
      if (grOptional.isPresent()) {
        var gridRenderer = grOptional.get();
        gridRenderer.render(gridCanvas, grid, gridLine, mapViewPort);
      }
    }
  }

  @Override
  public void close() throws IOException {
    eventBus.unregister(this);
  }
}
