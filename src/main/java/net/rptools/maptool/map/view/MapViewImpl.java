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
package net.rptools.maptool.map.view;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
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
import javafx.scene.shape.Rectangle;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.entity.EntityFactory;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.events.MapEntityAddedEvent;
import net.rptools.maptool.map.events.MapEntityRemovedEvent;
import net.rptools.maptool.map.events.MapFigureDragEnd;
import net.rptools.maptool.map.events.MapFigureDragStart;
import net.rptools.maptool.map.events.MapFigureUpdate;
import net.rptools.maptool.map.events.MapUpdateEvent;
import net.rptools.maptool.map.grid.Grid;
import net.rptools.maptool.map.grid.render.GridLine;
import net.rptools.maptool.map.grid.render.GridRendererFactory;
import net.rptools.maptool.map.view.mappable.MapFigure;
import net.rptools.maptool.map.view.mappable.MapFigureImpl;
import net.rptools.maptool.map.view.tool.MapViewTool;
import net.rptools.maptool.map.view.tool.factory.MapViewToolFactory;
import net.rptools.maptool.ui.controls.ResizableCanvas;

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

  /** The pane that picks up user interactions when there is nothing else in front of it. */
  private Pane tokenLayer = new Pane();
  // private Canvas tokenLayer = new Canvas();

  /** The {@link Canvas} used for drawing the grid. */
  private Canvas gridCanvas = new ResizableCanvas();

  /** The factory class for obtaining a grid renderer. */
  @Inject private GridRendererFactory gridRendererFactory;

  /** Sets the details for drawing the grid line. */
  private GridLine gridLine = new GridLine();

  /** The view port that maps screen to map. */
  private MapViewPort mapViewPort;

  /** The factory class for creating {@link Entity}s */
  @Inject private EntityFactory entityFactory;

  /** The {@link MapFigure}s for {@link Entity}s. */
  private final Map<Entity, MapFigure> mapFigures = new HashMap<>();

  /** {@link Node} for {@link MapFigure}s being dragged. */
  private Map<MapFigure, Node> draggingNodes = new HashMap<>();

  /** Current tool being used. */
  private MapViewTool mapViewTool;

  /** The factory used for creating {@link MapViewTool}s. */
  private MapViewToolFactory mapViewToolFactory;

  /** The {@link Canvas} available for the the tool to render to in the background. */
  private Canvas backgroundToolCanvas = new ResizableCanvas();

  /** The {@link Canvas} available for the the tool to render to in the foreground. */
  private Canvas foregroundToolCanvas = new ResizableCanvas();

  private final Set<Entity> selected = new HashSet<>();

  /**
   * Creates a new <code>MapViewImpl</code> object.
   *
   * @param gMap The map that this <code>GameMapView</code> is a view of.
   * @param eBus The event bus used to subscribe to map changes.
   * @param viewPort The viewport used to create a view into the map.
   * @param mvtFactory The factory used to create new tools.
   */
  @Inject
  public MapViewImpl(
      GameMap gMap, EventBus eBus, MapViewPort viewPort, MapViewToolFactory mvtFactory) {
    gameMap = gMap;
    eventBus = eBus;
    mapViewPort = viewPort;
    mapViewToolFactory = mvtFactory;

    eventBus.register(this);

    anchorPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
    AnchorPane.setTopAnchor(stackPane, 0.0);
    AnchorPane.setBottomAnchor(stackPane, 0.0);
    AnchorPane.setLeftAnchor(stackPane, 0.0);
    AnchorPane.setRightAnchor(stackPane, 0.0);

    anchorPane.getChildren().add(stackPane);

    backgroundCanvas.widthProperty().bind(stackPane.widthProperty());
    backgroundCanvas.heightProperty().bind(stackPane.heightProperty());

    foregroundToolCanvas.widthProperty().bind(stackPane.widthProperty());
    foregroundToolCanvas.heightProperty().bind(stackPane.heightProperty());

    backgroundToolCanvas.widthProperty().bind(stackPane.widthProperty());
    backgroundToolCanvas.heightProperty().bind(stackPane.heightProperty());

    gridCanvas.widthProperty().bind(stackPane.widthProperty());
    gridCanvas.heightProperty().bind(stackPane.heightProperty());

    stackPane.widthProperty().addListener(w -> viewResized());
    stackPane.heightProperty().addListener(h -> viewResized());

    mapViewPort.setGameMap(gMap);

    gridCanvas.setMouseTransparent(true);
    backgroundToolCanvas.setMouseTransparent(true);
    foregroundToolCanvas.setMouseTransparent(true);

    stackPane.getChildren().add(backgroundCanvas);
    stackPane.getChildren().add(backgroundToolCanvas);
    stackPane.getChildren().add(tokenLayer);
    stackPane.getChildren().add(foregroundToolCanvas);
    stackPane.getChildren().add(gridCanvas);

    registerListeners();

    mapViewTool =
        mapViewToolFactory.createPointerTool(this, backgroundCanvas, foregroundToolCanvas);
  }

  private void registerListeners() {
    tokenLayer.addEventHandler(MouseEvent.MOUSE_MOVED, e -> mapViewTool.mouseMoved(e));

    tokenLayer.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> mapViewTool.mousePressed(e));

    tokenLayer.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> mapViewTool.mouseDragged(e));

    tokenLayer.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> mapViewTool.mouseReleased(e));

    tokenLayer.setOnScroll(e -> mapViewTool.scroll(e));

    tokenLayer.prefHeight(Double.MAX_VALUE);
    tokenLayer.prefWidth(Double.MAX_VALUE);

    tokenLayer.setOnDragOver(
        event -> {
          if (event.getGestureSource() != tokenLayer) {
            if (event.getDragboard().hasFiles()) {
              event.acceptTransferModes(TransferMode.ANY);
              event.consume();
            }
          }
        });

    tokenLayer.setOnDragEntered(
        event -> {
          event.consume();
        });

    tokenLayer.setOnDragExited(
        event -> {
          event.consume();
        });

    tokenLayer.setOnDragDropped(
        event -> {
          Dragboard db = event.getDragboard();
          boolean success = false;
          if (db.hasFiles()) {
            success = true;
            File file = event.getDragboard().getFiles().get(0);

            // TODO:
            Point2D mapPoint =
                mapViewPort.convertDisplayToMap(new Point2D(event.getX(), event.getY()));
            Image img = new Image("file://" + file.getAbsolutePath(), true);

            double width;
            double height;
            if (getGameMap().getGrid().isPresent()) {
              Grid grid = getGameMap().getGrid().get();
              width = grid.getWidth();
              height = grid.getHeight();
            } else {
              width = img.getWidth();
              height = img.getHeight();
            }

            Entity entity =
                entityFactory.createSnapToGridMapFigure(
                    mapPoint.getX(), mapPoint.getY(), width, height, 0, img);
            gameMap.putEntity(entity);
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
      render(false);
    }
  }

  /**
   * Adds the mouse listeners to the node that represents the map figure.
   *
   * @param figure The {@link MapFigure} for the {@link Node}.
   * @param entity The {@link Entity} that this map figure represents.
   */
  private void addFigureListeners(MapFigure figure, Entity entity) {
    final Node node = figure.getNode();
    node.setOnMouseEntered(e -> mapViewTool.childNodeEntered(figure, entity, e));
    node.setOnMouseExited(e -> mapViewTool.childNodeExited(figure, entity, e));
    node.setOnMouseDragged(
        e -> {
          mapViewTool.childNodeDragged(figure, entity, e);
          // updateDraggedNode(figure);
        });
    node.setOnMouseReleased(
        e -> {
          mapViewTool.childNodeReleased(figure, entity, e);
          // updateDraggedNode(figure);
        });
  }

  /**
   * Update any nodes that have appeared / disappeared as a result of dragging a node.
   *
   * @param figure The {@link MapFigure} the dragged node is for.
   */
  private void updateDraggedNode(MapFigure figure) {
    Node draggedNode = figure.getDraggedNode();
    if (draggedNode != null) {
      if (!draggingNodes.containsKey(figure)) {
        draggingNodes.put(figure, draggedNode);
        tokenLayer.getChildren().add(draggedNode);
      }
    } else {
      draggedNode = draggingNodes.get(figure);
      if (draggedNode != null) {
        tokenLayer.getChildren().remove(draggedNode);
        draggingNodes.remove(figure);
      }
    }
  }

  @Subscribe
  void handleMapFigureDragStarted(MapFigureDragStart event) {
    if (event.getGameMap() == gameMap) {
      MapFigure figure = event.getMapFigure();
      Node node = figure.getDraggedNode();
      if (node != null) {
        if (!draggingNodes.containsKey(figure)) {
          draggingNodes.put(figure, node);
          tokenLayer.getChildren().add(node);
        }
      }
    }
  }

  @Subscribe
  void handleMapFigureDragEnded(MapFigureDragEnd event) {
    if (event.getGameMap() == gameMap) {
      MapFigure figure = event.getMapFigure();
      Node node = draggingNodes.get(figure);
      if (node != null) {
        tokenLayer.getChildren().remove(node);
        draggingNodes.remove(figure);
      }
    }
  }

  @Subscribe
  void handleEntityAddedEvent(MapEntityAddedEvent mapEntityAddedEvent) {
    Entity entity = mapEntityAddedEvent.getEntity();
    MapFigure mf = new MapFigureImpl(gameMap, mapViewPort, entity);
    mf.update();
    mapFigures.put(entity, mf);

    addFigureListeners(mf, entity);

    tokenLayer.getChildren().add(mf.getNode());

    render(false);
  }

  @Subscribe
  void handleEntityRemovedEvent(MapEntityRemovedEvent mapEntityRemovedEvent) {
    Entity entity = mapEntityRemovedEvent.getEntity();
    MapFigure mf = mapFigures.get(entity);
    if (mf != null) {
      tokenLayer.getChildren().remove(mf.getNode());
      mapFigures.remove(entity);
      draggingNodes.remove(mf);
    }
    render(false);
  }

  @Subscribe
  void handleMapFigureUpdate(MapFigureUpdate mapFigureUpdate) {
    System.out.println("mapFigureUpdate");
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
    render(true);
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

  @Override
  public void rerender() {
    render(true);
  }

  @Override
  public void setRectangleTool() {
    mapViewTool =
        mapViewToolFactory.createRectangleTool(this, backgroundCanvas, foregroundToolCanvas);
  }

  @Override
  public void setPointerTool() {
    mapViewTool =
        mapViewToolFactory.createPointerTool(this, backgroundCanvas, foregroundToolCanvas);
  }

  /** Handle resizing of the view */
  private void viewResized() {
    double width = stackPane.getWidth();
    double height = stackPane.getHeight();

    viewBounds = new Rectangle2D(-width / 2, -height / 2, width, height);

    translation = new Point2D(width / 2, height / 2);

    mapViewPort.adjustDisplaySize(new Rectangle2D(0, 0, width, height));

    stackPane.setClip(new Rectangle(0, 0, width, height));

    render(true);
  }

  /** Renders the content of the Map View. */
  private void render(boolean viewChanged) {
    // Don't render until both width and height are set
    if (stackPane.getWidth() != 0 && stackPane.getHeight() != 0) {
      if (viewChanged) {
        renderBackground();
        renderGrid();
        renderInteractives();
      }
    }
  }

  private void renderInteractives() {
    for (MapFigure mf : mapFigures.values()) {
      mf.update();
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
