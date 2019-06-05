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
package net.rptools.maptool.map.view.mappable;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.rptools.maptool.component.DraggableComponent;
import net.rptools.maptool.component.ImageComponent;
import net.rptools.maptool.component.PositionComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.events.MapFigureUpdate;
import net.rptools.maptool.map.grid.Grid;
import net.rptools.maptool.map.view.MapViewPort;

public class MapFigureImpl implements MapFigure {

  private final ImageView imageView = new ImageView();
  private final GameMap gameMap;
  private final MapViewPort mapViewPort;
  private final Entity entity;

  @Inject private EventBus eventBus;

  @Inject
  public MapFigureImpl(
      @Assisted GameMap map, @Assisted MapViewPort viewPort, @Assisted Entity ent) {
    gameMap = map;
    mapViewPort = viewPort;
    entity = ent;

    imageView.setOnMouseEntered(
        e -> {
          imageView.getScene().setCursor(Cursor.HAND);
          e.consume();
        });

    imageView.setOnMouseExited(
        e -> {
          imageView.getScene().setCursor(Cursor.DEFAULT);
          e.consume();
        });

    imageView.setOnMouseDragged(
        e -> {
          if (DraggableComponent.isDraggable(entity)) {
            if (!DraggableComponent.isBeingDragged(entity)) {
              PositionComponent pc = entity.getComponent(PositionComponent.class).get();
              DraggableComponent dc = entity.getComponent(DraggableComponent.class).get();
              dc.setFromX(pc.getX());
              dc.setFromY(pc.getY());
              dc.setBeingDragged(true);

              if (PositionComponent.isSnapToGrid(entity)) {
                Point2D mapPoint = mapViewPort.convertDisplayToMapGridCenter(e.getX(), e.getY());
                dc.setToX(mapPoint.getX());
                dc.setToY(mapPoint.getY());
              } else {
                dc.setToX(e.getX());
                dc.setToY(e.getY());
              }
            }

            eventBus.post(new MapFigureUpdate(gameMap, this));
          }
          e.consume();
        });

    imageView.setOnMouseReleased(
        e -> {
          if (DraggableComponent.isBeingDragged(entity)) {
            PositionComponent pc = entity.getComponent(PositionComponent.class).get();
            DraggableComponent dc = entity.getComponent(DraggableComponent.class).get();

            imageView.getScene().setCursor(Cursor.DEFAULT);
            Point2D mapPoint = mapViewPort.convertDisplayToMapGridCenter(e.getX(), e.getY());
            pc.setX(mapPoint.getX());
            pc.setY(mapPoint.getY());

            dc.setFromX(0);
            dc.setFromY(0);
            dc.setToX(0);
            dc.setToY(0);
            dc.setBeingDragged(false);

            update();
          }
          e.consume();
        });
  }

  @Override
  public void update() {
    PositionComponent pc = entity.getComponent(PositionComponent.class).get();
    ImageComponent ic = entity.getComponent(ImageComponent.class).get();
    Image image = ic.getImage();

    double imgWidth;
    double imgHeight;
    if (gameMap.getGrid().isPresent()) {
      Grid grid = gameMap.getGrid().get();
      imgWidth = grid.getWidth();
      imgHeight = grid.getHeight();
    } else {
      imgWidth = image.getWidth();
      imgHeight = image.getHeight();
    }

    imageView.setImage(image);

    var rect =
        mapViewPort.convertCenteredMapRectangleToDisplay(pc.getX(), pc.getY(), imgWidth, imgHeight);

    imageView.setX(rect.getMinX());
    imageView.setY(rect.getMinY());

    imageView.setFitWidth(rect.getWidth());
    imageView.setFitHeight(rect.getHeight());
  }

  @Override
  public ImageView getImageView() {
    return imageView;
  }
}
