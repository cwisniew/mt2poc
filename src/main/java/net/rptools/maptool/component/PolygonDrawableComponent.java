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
package net.rptools.maptool.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javafx.geometry.Point2D;

/** Component for rectangle shaped drawables. */
public class PolygonDrawableComponent implements Component {

  /** The id of this component. */
  private UUID id = UUID.randomUUID();

  /**
   * The x,y co-ordinates of the points on the drawable stored in a way efficient for optimised
   * calculation
   */
  private Double[] coordinates;

  /** The points of the drawable. */
  private List<Point2D> points;

  /** Creates a new <code>PolygonDrawableComponent</code> with no co-ordinates. */
  public PolygonDrawableComponent() {
    coordinates = new Double[0];
    points = new ArrayList<>();
  }

  /**
   * Creates a new <code>PolygonDrawableComponent</code> with the specified x and y co-ordinates.
   *
   * @param xy the alternating x and y co-ordinates for the drawable.
   */
  public PolygonDrawableComponent(Double[] xy) {
    coordinates = Arrays.copyOf(xy, xy.length);
    int numPoints = xy.length / 2;

    points = new ArrayList<>(numPoints);
    for (int i = 0; i < numPoints; i += 2) {
      points.add(new Point2D(xy[i], xy[i + 1]));
    }
  }

  /**
   * Creates a new <code>PolygonDrawableComponent</code> with the specified x and y co-ordinates.
   *
   * @param p the list of points for the drawable.
   */
  public PolygonDrawableComponent(List<Point2D> p) {
    points = new ArrayList<>(p);

    coordinates = new Double[points.size() * 2];

    int arrayInd = 0;
    for (int i = 0; i < points.size(); i++) {
      Point2D pnt = points.get(i);
      coordinates[arrayInd++] = pnt.getX();
      coordinates[arrayInd++] = pnt.getY();
    }
  }

  /**
   * Returns the x, y co-ordinates of the drawable.
   *
   * @return an array of {@link Double}s with x and y co-ordinate of the points interleaved.
   */
  public Double[] getPointsArray() {
    // TODO: this is not really safe as some one could modify.
    return coordinates;
  }

  public List<Double> getPointsDoubleList() {
    return Arrays.asList(coordinates);
  }

  /**
   * Returns the points of the polygon.
   *
   * @return the points of the polygon.
   */
  public List<Point2D> getPoints() {
    return Collections.unmodifiableList(points);
  }

  @Override
  public UUID getId() {
    return id;
  }
}
