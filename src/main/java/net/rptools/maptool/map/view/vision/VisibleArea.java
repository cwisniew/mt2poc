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
package net.rptools.maptool.map.view.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import javafx.geometry.Point2D;
import net.rptools.maptool.map.geom.MLineSegment;
import net.rptools.maptool.map.geom.MPolygon;

/** Class that represents a series of polygons that define the visible area. */
public class VisibleArea {

  /** Value representing no visible area. */
  public static VisibleArea NO_VISIBLE_AREA = new VisibleArea();

  /** The polygons that make up the visible area. */
  private final Set<MPolygon> polygons;

  /**
   * The "rays" shot out towards vision blocking polygon vertices (only really useful for debugging.
   */
  private final Set<MLineSegment> rays;

  /** The vertices for the vision blocking objects. (only really useful for debugging. */
  private final Set<Point2D> vertices;

  /**
   * Creates a <code>VisibleArea</code> that has all the passed in visible areas merged into one,.
   *
   * @param areas The areas to merge.
   * @return the a merged <code>VisibleArea</code>.
   */
  public static VisibleArea mergeVisibleAreas(Collection<VisibleArea> areas) {
    var polys = new ArrayList<MPolygon>();
    var raylist = new ArrayList<MLineSegment>();
    var vert = new ArrayList<Point2D>();
    for (var area : areas) {
      polys.addAll(area.getPolygons());
      raylist.addAll(area.getRays());
      vert.addAll(area.getVertices());
    }

    return new VisibleArea(polys, raylist, vert);
  }

  /** Creates a new <code>VisibleArea</code> where nothing is visible. */
  private VisibleArea() {
    polygons = Set.of();
    rays = Set.of();
    vertices = Set.of();
  }

  /**
   * Creates a new <code>VisibleArea</code> object.
   *
   * @param polys The polygons that make up the visible area.
   * @param visionRays The "rays" from the viewer to the vertices of the vision blocking polygons.
   * @param vert The vertices of the vision blocking polygons.
   */
  public VisibleArea(
      Collection<MPolygon> polys, Collection<MLineSegment> visionRays, Collection<Point2D> vert) {
    polygons = Set.copyOf(polys);
    rays = Set.copyOf(visionRays);
    vertices = Set.copyOf(vert);
  }

  /**
   * * Returns the polygons that make up the visible area.
   *
   * @return the polygons that make up the visible area.
   */
  public Set<MPolygon> getPolygons() {
    return polygons;
  }

  /**
   * Returns the "rays" from the viewer towards the vertices of the vision blocking polygons. These
   * values are really only useful for debugging purposes.
   *
   * @return the "rays" from the viewer towards the vertices of the vision blocking polygons.
   */
  public Set<MLineSegment> getRays() {
    return rays;
  }

  /**
   * Returns the vertices for the vision blocking polygons. These values are really only useful for
   * debugging purposes.
   *
   * @return the vertices for the vision blocking polygons.
   */
  public Set<Point2D> getVertices() {
    return vertices;
  }
}
