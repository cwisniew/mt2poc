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

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.map.view.mappable.MapFigure;

/** Interface implemented by tools used by {@link MapViewTool}s. */
public abstract class MapViewTool {

  /** The {@link MapView} this tool is for. */
  private final MapView mapView;

  /** a {@link Canvas} in the background for drawing on. */
  private Canvas backgroundCanvas;

  /** a {@link Canvas} in the foreground for drawing on. */
  private Canvas foregroundCanvas;

  /**
   * Creates a new <code>MapViewTool</code>.
   *
   * @param view the {@link MapView}.
   * @param backgroundCanvas a {@link Canvas} that the tool can render to behind most other
   *     controls.
   * @param foregroundCanvas a {@link Canvas} that the tool can render to behind most other
   *     controls.
   */
  protected MapViewTool(MapView view, Canvas backgroundCanvas, Canvas foregroundCanvas) {
    mapView = view;
  }

  /**
   * Returns the {@link MapView} that this tool is for.
   *
   * @return the {@link MapView}.
   */
  protected MapView getMapView() {
    return mapView;
  }

  /**
   * Returns the background {@link Canvas} that can be used to draw on.
   *
   * @return the {@link Canvas} that can be used to draw on.
   */
  protected Canvas getBackgroundCanvas() {
    return backgroundCanvas;
  }

  /**
   * Returns the foreground {@link Canvas} that can be used to draw on.
   *
   * @return the {@link Canvas} that can be used to draw on.
   */
  protected Canvas getForegroundCanvas() {
    return foregroundCanvas;
  }

  /**
   * Handle mouse moved events.
   *
   * @param event The {@link MouseEvent}.
   */
  public void mouseMoved(MouseEvent event) {}

  /**
   * Handle mouse pressed events.
   *
   * @param event The {@link MouseEvent}.
   */
  public void mousePressed(MouseEvent event) {}

  /**
   * Handle mouse dragged events.
   *
   * @param event The {@link MouseEvent}.
   */
  public void mouseDragged(MouseEvent event) {}

  /**
   * Handle scroll events.
   *
   * @param event The {@link ScrollEvent}.
   */
  public void scroll(ScrollEvent event) {}

  /**
   * Handle mouse pointer entering a child node for a {@link MapFigure}.
   *
   * @param figure The {@link MapFigure} the mouse is over.
   * @param entity The {@link Entity} for the {@link MapFigure}.
   * @param event The {@link MouseEvent}.
   */
  public void childNodeEntered(MapFigure figure, Entity entity, MouseEvent event) {};

  /**
   * Handle mouse pointer exiting a child node for a {@link MapFigure}.
   *
   * @param figure The {@link MapFigure} the mouse moved off of.
   * @param entity The {@link Entity} for the {@link MapFigure}.
   * @param event The {@link MouseEvent}.
   */
  public void childNodeExited(MapFigure figure, Entity entity, MouseEvent event) {};

  /**
   * Handle mouse dragged for a child node for a {@link MapFigure}.
   *
   * @param figure The {@link MapFigure} that was dragged.
   * @param entity The {@link Entity} for the {@link MapFigure}.
   * @param event The {@link MouseEvent}.
   */
  public void childNodeDragged(MapFigure figure, Entity entity, MouseEvent event) {};

  /**
   * Handle mouse released for a child node for a {@link MapFigure}.
   *
   * @param figure The {@link MapFigure} the mouse is released event is for.
   * @param entity The {@link Entity} for the {@link MapFigure}.
   * @param event The {@link MouseEvent}.
   */
  public void childNodeReleased(MapFigure figure, Entity entity, MouseEvent event) {};
}
