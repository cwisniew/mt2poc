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
import javafx.scene.shape.Circle;
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


  private boolean dragging = false;


  /** The pane to draw the tokens on */
  private Pane tokenPane = new Pane();


  /** The {@link Canvas} used for drawing the grid. */
  private Canvas gridCanvas = new ResizableCanvas();


  /** The factory class for obtaining a grid renderer. */
  private GridRendererFactory gridRendererFactory;


  /** Sets the details for drawing the grid line. */
  private GridLine gridLine = new GridLine();


  /** The view port that maps screen to map. */
  @Inject
  private MapViewPort mapViewPort;

  /**
   * Creates a new <code>MapViewImpl</code> object.
   *
   * @param gMap The map that this <code>GameMapView</code> is a view of.
   * @param eBus The event bus that changes to maps are registered on.
   * @param gRendererFactory The factory used for retrieving a grid renderer.
   */
  @Inject
  public MapViewImpl(GameMap gMap, EventBus eBus, GridRendererFactory gRendererFactory) {
    gameMap = gMap;
    eventBus = eBus;
    gridRendererFactory = gRendererFactory;

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
        }
    );


    stackPane.getChildren().add(tokenPane);

    tokenPane.prefHeight(Double.MAX_VALUE);
    tokenPane.prefWidth(Double.MAX_VALUE);
    tokenPane.getChildren().add(new Circle(20.0, 20.0, 19.0));


    stackPane.setOnDragOver(event -> {
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

    stackPane.setOnDragEntered(event -> {
      System.out.println("Here in drag entered");
      event.consume();
    });

    stackPane.setOnDragExited(event -> {
      System.out.println("Here in drag exited");
      dragging = false;
      event.consume();
      render();
    });


    stackPane.setOnDragDropped(event -> {
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


    gridCanvas.widthProperty().bind(stackPane.widthProperty());
    gridCanvas.heightProperty().bind(stackPane.heightProperty());

    stackPane.getChildren().add(gridCanvas);



    stackPane.widthProperty().addListener(w -> viewResized());
    stackPane.heightProperty().addListener(h -> viewResized());
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
    renderBackground();
    renderGrid();
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
