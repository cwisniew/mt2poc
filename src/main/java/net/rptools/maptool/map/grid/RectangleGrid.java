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
package net.rptools.maptool.map.grid;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/** An ordinary square grid for {@link net.rptools.maptool.map.GameMap}s. */
public class RectangleGrid implements Grid {

  /** The width of the grid. */
  private double width;

  /** The height of the grid. */
  private double height;

  /**
   * Creates a new <code>RectangleGrid</code>.
   *
   * @param dim the dimensions for a square grid
   */
  public RectangleGrid(double dim) {
    height = dim;
    width = dim;
  }

  /**
   * Creates a new <code>RectangleGrid</code>.
   *
   * @param w the width of the rectangle grid.
   * @param h the height of the rectangle grid.
   */
  public RectangleGrid(double w, double h) {
    height = h;
    width = w;
  }

  @Override
  public String getName() {
    return "Rectangle Grid";
  }

  @Override
  public double getWidth() {
    return width;
  }

  @Override
  public double getHeight() {
    return height;
  }

  @Override
  public void setWidth(double w) {
    width = w;
  }

  @Override
  public void setHeight(double h) {
    height = h;
  }

  @Override
  public Point2D getGridCenter(Point2D point) {
    return getGridCenter(point.getX(), point.getY());
  }

  @Override
  public Point2D getGridCenter(double mapX, double mapY) {
    // Easiest way to do this for a square grid is to find the upper left corner
    double x = Math.floor(mapX / width) * width;
    double y = Math.floor(mapY / height) * height;

    // Then add on half of the dimension
    return new Point2D(x + width / 2.0, y + height / 2.0);
  }

  @Override
  public Rectangle2D getGridBounds(Point2D mapPoint) {
    Point2D center = getGridCenter(mapPoint);

    double halfW = width / 2.0;
    double halfH = height / 2.0;

    return new Rectangle2D(center.getX() - halfW, center.getY() + halfH, width, height);
  }
}
