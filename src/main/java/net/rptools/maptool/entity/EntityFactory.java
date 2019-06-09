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
package net.rptools.maptool.entity;

import javafx.scene.image.Image;

/** Interface used for injection of the factory to create {@link Entity} objects. */
public interface EntityFactory {

  /**
   * Creates an empty {@link EntityBuilder}.
   *
   * @return an empty {@link EntityBuilder}.
   */
  public EntityBuilder createEntity();

  /**
   * Creates a new {@link Entity} which represents a simple figure on a {@link
   * net.rptools.maptool.map.GameMap}.
   *
   * @param x The x co-ordinate of the {@link Entity} on the {@link net.rptools.maptool.map.GameMap}
   * @param y The x co-ordinate of the {@link Entity} on the {@link net.rptools.maptool.map.GameMap}
   * @param w The width.
   * @param h The height.
   * @param z The x co-ordinate of the {@link Entity} on the {@link net.rptools.maptool.map.GameMap}
   * @param image The {@link Image} to display for the simple figure.
   * @return an {@link Entity} that represents a simple figure on the map.
   */
  public Entity createMapFigure(double x, double y, double w, double h, double z, Image image);

  /**
   * Creates a new {@link Entity} which represents a simple figure on a {@link
   * net.rptools.maptool.map.GameMap} that has snap to grid set.
   *
   * @param x The x co-ordinate of the {@link Entity} on the {@link net.rptools.maptool.map.GameMap}
   * @param y The x co-ordinate of the {@link Entity} on the {@link net.rptools.maptool.map.GameMap}
   * @param w The width.
   * @param h The height.
   * @param z The x co-ordinate of the {@link Entity} on the {@link net.rptools.maptool.map.GameMap}
   * @param image The {@link Image} to display for the simple figure.
   * @return an {@link Entity} that represents a simple figure on the map.
   */
  public Entity createSnapToGridMapFigure(
      double x, double y, double w, double h, double z, Image image);

  public Entity createDrawableRectangle(double x, double y, double w, double h, double z);
}
