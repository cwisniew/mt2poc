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

import java.util.UUID;
import net.rptools.maptool.map.geom.MPolygon;

/** Component for holding data about vision blocking. */
public class VisionBlockingComponent implements Component {

  /** The id of the component. */
  private UUID id = UUID.randomUUID();

  /** The polygon that will block vision. */
  private MPolygon polygon;

  /**
   * Creates a new <code>VisionBlockingComponent</code>.
   *
   * @param poly the polygon used to block vision.
   */
  public VisionBlockingComponent(MPolygon poly) {
    polygon = poly;
  }

  @Override
  public UUID getId() {
    return id;
  }

  /**
   * Returns the polygon that blocks vision.
   *
   * @return the polygon that blocks vision.
   */
  public MPolygon getPolygon() {
    return polygon;
  }
}
