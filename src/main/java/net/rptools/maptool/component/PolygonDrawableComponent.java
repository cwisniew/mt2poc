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
import java.util.List;
import java.util.UUID;
import javafx.geometry.Point2D;
import javafx.scene.paint.Paint;
import net.rptools.maptool.map.geom.MPolygon;

/** Component for rectangle shaped drawables. */
public class PolygonDrawableComponent implements Component {

  /** The id of this component. */
  private UUID id = UUID.randomUUID();

  /** The {@link Paint} to use for stroking the polygon. */
  private Paint stroke;

  /** The {@link Paint} to use for fillinh the polygon. */
  private Paint fill;

  /** The actual polygon. */
  private MPolygon polygon;

  /**
   * Creates a new <code>PolygonDrawableComponent</code> with the specified x and y co-ordinates.
   *
   * @param xy the alternating x and y co-ordinates for the drawable.
   */
  public PolygonDrawableComponent(Double[] xy, Paint strokePaint, Paint fillPaint) {

    int numPoints = xy.length / 2;
    var points = new ArrayList<Point2D>(numPoints);

    for (int i = 0; i < xy.length; i += 2) {
      points.add(new Point2D(xy[i], xy[i + 1]));
    }

    polygon = new MPolygon(points);

    stroke = strokePaint;
    fill = fillPaint;
  }

  /**
   * Creates a new <code>PolygonDrawableComponent</code> with the specified x and y co-ordinates.
   *
   * @param p the list of points for the polygon.
   * @param strokePaint The {@link Paint) to stroke draw the polygon with.}
   * @param fillPaint The {@link Paint} to fill the polygon with.
   */
  public PolygonDrawableComponent(List<Point2D> p, Paint strokePaint, Paint fillPaint) {
    polygon = new MPolygon(p);
    stroke = strokePaint;
    fill = fillPaint;
  }

  /**
   * @param poly The polygon.
   * @param strokePaint The {@link Paint) to stroke draw the polygon with.}
   * @param fillPaint The {@link Paint} to fill the polygon with.
   */
  public PolygonDrawableComponent(MPolygon poly, Paint strokePaint, Paint fillPaint) {
    polygon = poly;
    stroke = strokePaint;
    fill = fillPaint;
  }

  /**
   * Returns the polygon for this component.
   *
   * @return the polygon for the component.
   */
  public MPolygon getPolygon() {
    return polygon;
  }

  /**
   * Sets the polygon for this component.
   *
   * @param poly the polygon to set for this component.
   */
  public void setPolygon(MPolygon poly) {
    polygon = poly;
  }

  /**
   * Returns the {@link Paint} used to stroke the polygon.
   *
   * @return the {@link Paint} used to stroke the polygon.
   */
  public Paint getStroke() {
    return stroke;
  }

  /**
   * Sets the {@link Paint} used to stroke the polygon,
   *
   * @param stroke the {@link Paint} used to stroke the polygon.
   */
  public void setStroke(Paint stroke) {
    this.stroke = stroke;
  }

  /**
   * Returns the {@link Paint} used to fill the polygon.
   *
   * @return the {@link Paint} used to fill the polygon.
   */
  public Paint getFill() {
    return fill;
  }

  /**
   * Sets the {@link Paint} used to fill the polygon.
   *
   * @param fill the {@link Paint} used to fill the polygon.
   */
  public void setFill(Paint fill) {
    this.fill = fill;
  }

  @Override
  public UUID getId() {
    return id;
  }
}
