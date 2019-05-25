package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.Closeable;
import java.io.IOException;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;
import net.rptools.maptool.renderer.ui.controls.ResizableCanvas;

/**
 * Class that implements a view of a {@link GameMap}.
 */
public class MapViewImpl implements MapView, Closeable {

  /** The {@Link AnchorPane} that will hold the <code>MapView</code> nodes. */
  private AnchorPane anchorPane;

  /** The {@link StackPane} that holds the different rendered layers of the map. */
  private StackPane stackPane;

  /** The {@link GameMap} this view "looks in to".  */
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

   /**
    *  Creates a new <code>MapViewImpl</code> object.
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
  }

  @Override
  public Pane getParentNode() {
    return anchorPane;
  }

  /**
   * Handles
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
   * @return the game map this is a view of.
   */
  public GameMap getGameMap() {
    return gameMap;
  }

  /**
   * Handle resizing of the
   */
  private void viewResized() {
    double width = stackPane.getWidth();
    double height = stackPane.getHeight();

    viewBounds = new Rectangle2D(-width/2, -height/2, width, height);

    render();
  }


  /**
   * Renders the content of the Map View.
   */
  private void render() {
  }

  @Override
  public void close() throws IOException {
    eventBus.unregister(this);
  }
}
