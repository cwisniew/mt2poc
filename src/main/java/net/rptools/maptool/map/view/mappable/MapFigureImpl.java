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

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.rptools.maptool.component.DraggableComponent;
import net.rptools.maptool.component.ImageComponent;
import net.rptools.maptool.component.PositionComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.grid.Grid;
import net.rptools.maptool.map.view.MapViewPort;

public class MapFigureImpl implements MapFigure {

  private final ImageView imageView = new ImageView();
  private final GameMap gameMap;
  private final MapViewPort mapViewPort;
  private final Entity entity;

  private ImageView draggedImageView;

  public MapFigureImpl(GameMap map, MapViewPort viewPort, Entity ent) {
    gameMap = map;
    mapViewPort = viewPort;
    entity = ent;
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

    if (draggedImageView != null) {
      if (DraggableComponent.isBeingDragged(entity)) {
        DraggableComponent dc = entity.getComponent(DraggableComponent.class).get();

        var rect =
            mapViewPort.convertCenteredMapRectangleToDisplay(
                dc.getToX(), dc.getToY(), imgWidth, imgHeight);

        imageView.setX(rect.getMinX());
        imageView.setY(rect.getMinY());

        imageView.setFitWidth(rect.getWidth());
        imageView.setFitHeight(rect.getHeight());

        var draggedRect =
            mapViewPort.convertCenteredMapRectangleToDisplay(
                dc.getFromX(), dc.getFromY(), imgWidth, imgHeight);

        draggedImageView.setX(draggedRect.getMinX());
        draggedImageView.setY(draggedRect.getMinY());

        draggedImageView.setFitWidth(draggedRect.getWidth());
        draggedImageView.setFitHeight(draggedRect.getHeight());
      } else {
        draggedImageView = null;
      }
    } else {
      var rect =
          mapViewPort.convertCenteredMapRectangleToDisplay(
              pc.getX(), pc.getY(), imgWidth, imgHeight);

      imageView.setX(rect.getMinX());
      imageView.setY(rect.getMinY());

      imageView.setFitWidth(rect.getWidth());
      imageView.setFitHeight(rect.getHeight());
    }
  }

  @Override
  public Node getNode() {
    return imageView;
  }

  @Override
  public Node getDraggedNode() {
    if (draggedImageView == null && DraggableComponent.isBeingDragged(entity)) {
      draggedImageView = new ImageView(imageView.getImage());
      draggedImageView.setOpacity(0.6);
      update();
    }
    return draggedImageView;
  }
}
