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
package net.rptools.maptool.map.geom;

import java.util.List;
import javafx.geometry.Point2D;

/** Class that represents a display polygon. */
public class DPolygon {

  /** The x co-ordinates for this polygon. */
  private final double[] x;
  /** The y co-ordinates for this polygon. */
  private final double[] y;

  /** The list of points in this polygon. */
  private final List<Point2D> points;

  /**
   * Creates a new Display polygon.
   *
   * @param p the points that make up this polygon,
   */
  public DPolygon(List<Point2D> p) {
    points = List.copyOf(p);

    x = new double[points.size()];
    y = new double[points.size()];

    for (int i = 0; i < points.size(); i++) {
      var point = points.get(i);
      x[i] = point.getX();
      y[i] = point.getY();
    }
  }

  /**
   * Returns the points that make up this polygon vertices.
   *
   * @return the points that make up the polygon vertices.
   */
  public List<Point2D> getPoints() {
    return points;
  }

  /**
   * Returns the x co-ordinates of the polygons vertices.
   *
   * @return the x co-ordinates of the polygons vertices;.
   */
  public double[] getX() {
    return x;
  }

  /**
   * Returns the y co-ordinates of the polygons vertices.
   *
   * @return the y co-ordinates of the polygons vertices.
   */
  public double[] getY() {
    return y;
  }

  /**
   * Returns the number of vertices for the polygon.
   *
   * @return the number of vertices for the polygon.
   */
  public int getNumberVertices() {
    return points.size();
  }
}
