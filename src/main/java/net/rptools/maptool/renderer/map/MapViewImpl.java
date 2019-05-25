package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;

public class MapViewImpl implements MapView {

  private AnchorPane anchorPane;
  private StackPane stackPane;
  private GameMap gameMap;
  private EventBus eventBus;
  private Point2D transation = new Point2D(0.0, 0.0);
  private double sacle = 1.0;
  private Rectangle2D viewBounds;


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

    stackPane.widthProperty().addListener(w -> viewResized());
    stackPane.heightProperty().addListener(h -> viewResized());

  }


  public Parent getParentComponent() {
    return anchorPane;
  }

  @Subscribe
  void handleMapUpdateEvent(MapUpdateEvent mapUpdateEvent) {
    System.out.println("Map updated: id = " + mapUpdateEvent.getMapId());
  }

  public GameMap getGameMap() {
    return gameMap;
  }

  private void viewResized() {
    double width = stackPane.getWidth();
    double height = stackPane.getHeight();

    System.out.println("height -> " + height);
    viewBounds = new Rectangle2D(-width/2, -height/2, width, height);

    System.out.println(
        "View " + viewBounds.getMinX() + ", " + viewBounds.getMinY() + " - "
            + viewBounds.getMaxX() + ", " + viewBounds.getMaxY()
    );
  }

}
