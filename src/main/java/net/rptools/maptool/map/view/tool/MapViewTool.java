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

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.map.view.mappable.MapFigure;

/** Interface implemented by tools used by {@link MapViewTool}s. */
public abstract class MapViewTool {

  /** The {@link MapView} this tool is for. */
  private final MapView mapView;

  /**
   * Creates a new <code>MapViewTool</code>.
   *
   * @param view the {@link MapView}.
   */
  protected MapViewTool(MapView view) {
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

  public void childNodeEntered(MapFigure figure, Entity entity, MouseEvent event) {};

  public void childNodeExited(MapFigure figure, Entity entity, MouseEvent event) {};

  public void childNodeDragged(MapFigure figure, Entity entity, MouseEvent event) {};

  public void childNodeReleased(MapFigure figure, Entity entity, MouseEvent event) {};
}
