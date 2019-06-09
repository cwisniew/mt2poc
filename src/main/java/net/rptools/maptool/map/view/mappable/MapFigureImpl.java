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
import net.rptools.maptool.component.MapFigureComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.view.MapViewPort;

/** This class implements the {@link MapFigure} */
public class MapFigureImpl implements MapFigure {

  /** The default opacity used for the the image at the starting drag point. */
  private static double DEFAULT_DRAG_START_OPACITY = 0.6;

  /** The {@link ImageView} to be displayed on the map. */
  private final ImageView imageView = new ImageView();
  /** The {@link GameMap} that {@link Entity} is on. */
  private final GameMap gameMap;
  /** The {@link MapViewPort} that is being used to translate co-ordinates. */
  private final MapViewPort mapViewPort;
  /** The {@link Entity} to represent. */
  private final Entity entity;

  /** The {@link ImageView} to be displayed when dragging. */
  private ImageView draggedImageView;

  /**
   * Creates a new <code>MapFigureImpl</code> object.
   *
   * @param map The {@link GameMap} that this <code>MapFigure</code> on.
   * @param viewPort The {@link MapViewPort} used to translate co-ordinates.
   * @param ent The {@link Entity} that this <code>MapFigure</code> represents..
   */
  public MapFigureImpl(GameMap map, MapViewPort viewPort, Entity ent) {
    gameMap = map;
    mapViewPort = viewPort;
    entity = ent;
  }

  @Override
  public void update() {
    MapFigureComponent pc = entity.getComponent(MapFigureComponent.class).get();
    ImageComponent ic = entity.getComponent(ImageComponent.class).get();
    Image image = ic.getImage();

    imageView.setImage(image);

    if (draggedImageView != null) {
      if (DraggableComponent.isBeingDragged(entity)) {
        DraggableComponent dc = entity.getComponent(DraggableComponent.class).get();

        var rect =
            mapViewPort.convertCenteredMapRectangleToDisplay(
                dc.getToX(), dc.getToY(), pc.getWidth(), pc.getHeight());

        imageView.setX(rect.getMinX());
        imageView.setY(rect.getMinY());

        imageView.setFitWidth(rect.getWidth());
        imageView.setFitHeight(rect.getHeight());

        var draggedRect =
            mapViewPort.convertCenteredMapRectangleToDisplay(
                dc.getFromX(), dc.getFromY(), pc.getWidth(), pc.getHeight());

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
              pc.getX(), pc.getY(), pc.getWidth(), pc.getHeight());

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
      draggedImageView.setOpacity(DEFAULT_DRAG_START_OPACITY);
      update();
    }
    return draggedImageView;
  }
}
