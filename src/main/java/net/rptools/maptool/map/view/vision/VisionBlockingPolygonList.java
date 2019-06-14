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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javafx.geometry.Point2D;
import net.rptools.maptool.component.VisionBlockingComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.geom.MLineSegment;
import net.rptools.maptool.map.geom.MPolygon;

/**
 * Class used to hold onto the list of vision blocking polygons. It doesnt actually do much more
 * than that (yet...)
 */
public class VisionBlockingPolygonList {

  /** The vision blocking polygons. */
  private final List<MPolygon> polygons = new ArrayList<>();

  /** The vertices of the vision blocking polygons. */
  private final Set<Point2D> vertices = new HashSet<>();

  /** The line segments for the vision blocking polygons. */
  private final Set<MLineSegment> lineSegments = new HashSet<>();

  /**
   * Creates a <code>VisionBlockingPolygonList</code>.
   *
   * @param entities the entities that are vision blocking.
   */
  public VisionBlockingPolygonList(Collection<Entity> entities) {
    for (Entity entity : entities) {
      Optional<VisionBlockingComponent> vbc = entity.getComponent(VisionBlockingComponent.class);
      if (vbc.isPresent()) {
        var blockingPoly = vbc.get();
        var poly = blockingPoly.getPolygon();
        polygons.add(poly);
        vertices.addAll(poly.getVertices());
        lineSegments.addAll(poly.getLineSegments());
      }
    }
  }

  /**
   * * Returns the number of vertices associated with the vision blocking polygons.
   *
   * @return the number of vertices.
   */
  public int getNumberOfVertices() {
    return vertices.size();
  }

  /**
   * Returns the vertices associated with the vision blocking polygons.
   *
   * @return the vertices associated with the vision blocking polygons.
   */
  public Set<Point2D> getVertices() {
    return Collections.unmodifiableSet(vertices);
  }

  /**
   * Returns the vision blocking polygons.
   *
   * @return the vision blocking polygons.
   */
  public List<MPolygon> getPolygons() {
    return Collections.unmodifiableList(polygons);
  }

  /**
   * Returns the line segments for the vision blocking polygons.
   *
   * @return the line segments for the vision blocking polygons.
   */
  public Set<MLineSegment> getLineSegments() {
    return Collections.unmodifiableSet(lineSegments);
  }
}
