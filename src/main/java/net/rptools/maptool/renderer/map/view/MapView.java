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
package net.rptools.maptool.renderer.map.view;

import javafx.scene.layout.Pane;
import net.rptools.maptool.renderer.map.GameMap;
import net.rptools.maptool.renderer.map.grid.render.GridLine;

/** Interface for classes that is a view of a (@link GameMap}. */
public interface MapView {

  /**
   * Gets the {@link Pane} node for view.
   *
   * @return the parent node for the view.
   */
  Pane getParentNode();

  /**
   * Returns the {@link GameMap} this is a view for.
   *
   * @return the game map this view is for.
   */
  GameMap getGameMap();

  /**
   * Sets the scale factor for the map view.
   *
   * @param scale the scale factor.
   */
  void setScale(double scale);


  /**
   * Sets the details for drawing the grid line.
   *
   * @param gridLine The details for drawing the the grid lines.
   */
  void setGridLine(GridLine gridLine);


  /**
   * Returns the details used for drawing the grid lines.
   * @return the details used for drawing the grid lines.
   */
  GridLine getGridLine();


  /**
   * Returns the {@link MapViewPortImpl} that maps between screen and map co-ordinates.
   * @return the {@link MapViewPortImpl} that maps between screen and map co-ordinates.
   */
  MapViewPort getMapViewPort();
}
