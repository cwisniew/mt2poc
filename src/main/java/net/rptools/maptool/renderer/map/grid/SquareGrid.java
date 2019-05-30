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

/** An ordinary square grid for {@link net.rptools.maptool.renderer.map.GameMap}s. */
public class SquareGrid implements Grid {

  /** The height and width of the grid. */
  private double dimension;

  /**
   * Creates a new <code>SquareGrid</code>.
   *
   * @param dim the dimensions for the square.
   */
  public SquareGrid(double dim) {
    dimension = dim;
  }

  @Override
  public String getName() {
    return "Square Grid";
  }

  @Override
  public double getWidth() {
    return dimension;
  }

  @Override
  public double getHeight() {
    return dimension;
  }

  @Override
  public void setWidth(double w) {
    dimension = w;
  }

  @Override
  public void setHeight(double h) {
    dimension = h;
  }

  @Override
  public Point2D getGridCenter(Point2D point) {
    // Easiest way to do this for a square grid is to find the upper left corner
    double x = Math.floor(point.getX() / dimension) * dimension;
    double y = Math.floor(point.getY() / dimension) * dimension;

    // Then add on half of the dimension
    return new Point2D(x + dimension / 2, y + dimension / 2);
  }
}
