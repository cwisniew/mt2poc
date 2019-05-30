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
package net.rptools.maptool.renderer.map.grid;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.w3c.dom.css.Rect;

/** Interface implemented by all grids for {@link net.rptools.maptool.renderer.map.GameMap}s. */
public interface Grid {

  /**
   * Returns the name of the grid.
   *
   * @return the name of the grid.
   */
  String getName();

  /**
   * Returns the width at the widest point of a grid cell.
   *
   * @return
   */
  double getWidth();

  /**
   * Returns the height at the tallest point of the grid cell.
   *
   * @return
   */
  double getHeight();

  /**
   * Sets the width of the widest point of the grid cell.
   *
   * @param w the width of the widest point.
   */
  void setWidth(double w);

  /**
   * Sets the height of the tallest point of the grid cell.
   *
   * @param h The height of the tallest point.
   */
  void setHeight(double h);

  /**
   * Returns the point in the center of the grid cell that contains the specified map co-ordinate.
   * Both the point to test and the returned center of the grid are in map co-ordinates.
   *
   * @param mapPoint the map point to find the cell for.
   *
   * @return the center of the grid cell.
   */
  Point2D getGridCenter(Point2D mapPoint);


  /**
   * Returns the bounds for the grid cell that contains the specified map co-ordinate.
   * Both the point to test and returned bounds are in map co-ordinates.
   *
   * @param mapPoint the map point to find the cell for.
   *
   * @return the bounds of the grid cell.
   */
  Rectangle2D getGridBounds(Point2D mapPoint);
}
