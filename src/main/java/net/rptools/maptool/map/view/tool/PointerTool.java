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
package net.rptools.maptool.map.view.tool;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import net.rptools.maptool.component.DraggableComponent;
import net.rptools.maptool.component.MapFigureComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.events.MapFigureDragEnd;
import net.rptools.maptool.map.events.MapFigureDragStart;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.map.view.MapViewPort;
import net.rptools.maptool.map.view.mappable.figures.MapFigure;

/** The type Pointer tool. */
public class PointerTool extends MapViewTool {

  /** The mouse X c-ordinates. */
  private double mouseX;
  /** The mouse Y c-ordinates. */
  private double mouseY;
  /** The mouse X c-ordinates where the mouse button was pressed.. */
  private double mousePressedX;
  /** The mouse Y c-ordinates where the mouse button was pressed.. */
  private double mousePressedY;

  /** The {@link EventBus} to post events to */
  @Inject private EventBus eventBus;

  /**
   * Number of milliseconds delay before triggering successive map movements/redraws when dragging
   * the mouse to move the map. This stops the movement from being too "touchy"
   */
  private static final int MAP_MOVEMENT_REDRAW_DELAY = 5;

  /** the last time a drag event moved and redrew the mapp. */
  private long lastMapMovementRedraw = 0;

  /**
   * Creates a new <code>PointerTool</code>.
   *
   * @param view the {@link MapView}.
   * @param backgroundCanvas a {@link Canvas} that the tool can render to behind most other
   *     controls.
   * @param foregroundCanvas a {@link Canvas} that the tool can render to behind most other
   *     controls.
   */
  @Inject
  public PointerTool(
      @Assisted MapView view,
      @Assisted("backgroundCanvas") Canvas backgroundCanvas,
      @Assisted("foregroundCanvas") Canvas foregroundCanvas) {
    super(view, backgroundCanvas, foregroundCanvas);
  }

  @Override
  public void mouseMoved(MouseEvent event) {
    mouseX = event.getX();
    mouseY = event.getY();
  }

  @Override
  public void mousePressed(MouseEvent event) {
    if (event.isPrimaryButtonDown()) {
      mousePressedX = event.getX();
      mousePressedY = event.getY();
      event.consume();
    }
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    if (event.isPrimaryButtonDown()) {
      if (System.currentTimeMillis() - lastMapMovementRedraw > MAP_MOVEMENT_REDRAW_DELAY) {

        mouseX = event.getX();
        mouseY = event.getY();

        final MapViewPort mapViewPort = getMapView().getMapViewPort();
        mapViewPort.panView((mousePressedX - mouseX) / 5, (mousePressedY - mouseY) / 5);

        getMapView().rerender();
        lastMapMovementRedraw = System.currentTimeMillis();
      }

      event.consume();
    }
  }

  @Override
  public void scroll(ScrollEvent event) {
    if (event.getDeltaY() > 0) {
      getMapView().getMapViewPort().zoomOut();
    } else {
      getMapView().getMapViewPort().zoomIn();
    }
    event.consume();
    getMapView().rerender();
  }

  @Override
  public void childNodeEntered(MapFigure figure, Entity entity, MouseEvent event) {
    figure.getNode().getScene().setCursor(Cursor.HAND);
    event.consume();
  }

  @Override
  public void childNodeExited(MapFigure figure, Entity entity, MouseEvent event) {
    figure.getNode().getScene().setCursor(Cursor.DEFAULT);
    event.consume();
  }

  @Override
  public void childNodeDragged(MapFigure figure, Entity entity, MouseEvent event) {
    if (DraggableComponent.isDraggable(entity)) {
      MapFigureComponent pc = entity.getComponent(MapFigureComponent.class).get();
      DraggableComponent dc = entity.getComponent(DraggableComponent.class).get();

      if (MapFigureComponent.isSnapToGrid(entity)) {
        Point2D mapPoint =
            getMapView().getMapViewPort().convertDisplayToMapGridCenter(event.getX(), event.getY());
        dc.setToX(mapPoint.getX());
        dc.setToY(mapPoint.getY());
      } else {
        dc.setToX(event.getX());
        dc.setToY(event.getY());
      }

      if (!DraggableComponent.isBeingDragged(entity)) {
        dc.setFromX(pc.getX());
        dc.setFromY(pc.getY());
        dc.setBeingDragged(true);
      }

      figure.update();

      eventBus.post(new MapFigureDragStart(getMapView().getGameMap(), figure));
    }
    event.consume();
  }

  @Override
  public void childNodeReleased(MapFigure figure, Entity entity, MouseEvent event) {
    if (DraggableComponent.isBeingDragged(entity)) {
      Node node = figure.getNode();

      MapFigureComponent pc = entity.getComponent(MapFigureComponent.class).get();
      DraggableComponent dc = entity.getComponent(DraggableComponent.class).get();

      node.getScene().setCursor(Cursor.DEFAULT);
      Point2D mapPoint =
          getMapView().getMapViewPort().convertDisplayToMapGridCenter(event.getX(), event.getY());
      pc.setX(mapPoint.getX());
      pc.setY(mapPoint.getY());

      dc.setFromX(0);
      dc.setFromY(0);
      dc.setToX(0);
      dc.setToY(0);
      dc.setBeingDragged(false);

      figure.update();
      eventBus.post(new MapFigureDragEnd(getMapView().getGameMap(), figure));
    }
    event.consume();
  }
}
