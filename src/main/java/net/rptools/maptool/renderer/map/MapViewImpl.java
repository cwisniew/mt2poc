package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import java.io.Closeable;
import java.io.IOException;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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

  @Override
  public void setScale(double scaleFactor) {
    scale = scaleFactor;
    render();
  }

  /**
   * Handle resizing of the
   */
  private void viewResized() {
    double width = stackPane.getWidth();
    double height = stackPane.getHeight();

    viewBounds = new Rectangle2D(-width/2, -height/2, width, height);

    transation = new Point2D(width/2, height/2);
    render();
  }


  /**
   * Renders the content of the Map View.
   */
  private void render() {
    renderBackground();
  }


  /**
   * Render the view of the map.
   */
  private void renderBackground() {
    GraphicsContext gc = backgroundCanvas.getGraphicsContext2D();
    double width = backgroundCanvas.getWidth();
    double height = backgroundCanvas.getHeight();

    gc.clearRect(0, 0, width, height);
    gc.setStroke(Color.RED);

    gc.strokeLine(0, 0, width, height);
    gc.strokeLine(0, height, width, 0);

    if (gameMap.getBackgroundTexture().isPresent()) {
      gc.save();

      gc.translate(transation.getX(), transation.getY());
      gc.scale(scale, scale);
      Image backgroundTexture = gameMap.getBackgroundTexture().get();

      double textureWidth = backgroundTexture.getWidth();
      double textureHeight = backgroundTexture.getHeight();

      int numXTextures = (int) Math.ceil(width/textureWidth);
      int numYTextures = (int) Math.ceil(height/textureHeight);

      double startX = - (numXTextures / 2 * textureWidth);
      double startY = - (numYTextures / 2 * textureHeight);

      for (double x = startX; x <= width; x+= textureWidth) {
        for (double y = startY; y <= height; y+= textureHeight) {
          gc.drawImage(backgroundTexture, x, y);
        }
      }

      gc.setFill(Color.BLUE);
      gc.fillOval(-transation.getX(),-transation.getY(), 55, 55);
      gc.setFill(Color.RED);
      gc.fillOval(-3,-3, 5, 5);
      gc.restore();
    }


  }

  @Override
  public void close() throws IOException {
    eventBus.unregister(this);
  }
}
