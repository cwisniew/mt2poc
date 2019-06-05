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

import java.util.Objects;
import java.util.UUID;

/** The component the denotes position on a {@link net.rptools.maptool.map.GameMap}. */
public class PositionComponent implements Component {

  /** The id of the <code>PositionComponent</code>. */
  private final UUID id = UUID.randomUUID();

  /** The x co-ordinate on the {@link net.rptools.maptool.map.GameMap}. */
  private double x;
  /** The y co-ordinate on the {@link net.rptools.maptool.map.GameMap}. */
  private double y;
  /** The z co-ordinate on the {@link net.rptools.maptool.map.GameMap}. */
  private double z;

  /** should this component snap to grid? */
  private boolean snapToGrid;

  /**
   * Creates a new <code>PositionComponent></code> object.
   *
   * @param x The x co-ordinate on the map.
   * @param y The y co-ordinate on the map
   * @param z The z co-ordinate on the map.
   * @param snap Should this component snap to grid.
   */
  public PositionComponent(double x, double y, double z, boolean snap) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.snapToGrid = snap;
  }

  /**
   * Creates a new <code>PositionComponent></code> object.
   * This will create a component with snap to grid defaulting to <code>false</code>.
   *
   * @param x The x co-ordinate on the map.
   * @param y The y co-ordinate on the map
   * @param z The z co-ordinate on the map.
   */
  public PositionComponent(double x, double y, double z) {
    this(x, y, z, false);
  }

  /**
   * Returns the x co-ordinate on the {@link net.rptools.maptool.map.GameMap}.
   *
   * @return The x co-ordinate on the {@link net.rptools.maptool.map.GameMap}
   */
  public double getX() {
    return x;
  }

  /**
   * Sets the x co-ordinate on the {@link net.rptools.maptool.map.GameMap}.
   *
   * @param x The x co-ordinate on the {@link net.rptools.maptool.map.GameMap}
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Returns the y co-ordinate on the {@link net.rptools.maptool.map.GameMap}.
   *
   * @return The y co-ordinate on the {@link net.rptools.maptool.map.GameMap}
   */
  public double getY() {
    return y;
  }

  /**
   * Sets the y co-ordinate on the {@link net.rptools.maptool.map.GameMap}.
   *
   * @param y The y co-ordinate on the {@link net.rptools.maptool.map.GameMap}
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Returns the z co-ordinate on the {@link net.rptools.maptool.map.GameMap}.
   *
   * @return The z co-ordinate on the {@link net.rptools.maptool.map.GameMap}
   */
  public double getZ() {
    return z;
  }

  /**
   * Sets the z co-ordinate on the {@link net.rptools.maptool.map.GameMap}.
   *
   * @param z The y co-ordinate on the {@link net.rptools.maptool.map.GameMap}
   */
  public void setZ(double z) {
    this.z = z;
  }

  /**
   * Returns if this componen shouldt snap to grid.
   *
   * @return <code>true</code> if this component should snap to grid otherwise <code>false</code>.
   */
  public boolean isSnapToGrid() {
    return snapToGrid;
  }

  /**
   * Sets if this component should snap to grid.
   *
   * @param snapToGrid <code>true</code> if this component should snap to grid.
   */
  public void setSnapToGrid(boolean snapToGrid) {
    this.snapToGrid = snapToGrid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PositionComponent that = (PositionComponent) o;
    return Double.compare(that.x, x) == 0
        && Double.compare(that.y, y) == 0
        && Double.compare(that.z, z) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }

  @Override
  public UUID getId() {
    return id;
  }
}
