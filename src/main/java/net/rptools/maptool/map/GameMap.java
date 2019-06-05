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
package net.rptools.maptool.map;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.grid.Grid;

/** Interface implemented by classes that represent maps used in the game. */
public interface GameMap {

  /** The id of the <code>GameMap</code>. */
  UUID getId();

  /**
   * Sets the {@link Image} texture to use as the background of the <code>GameMap</code>.
   *
   * @param texture The texture to use as the background.
   */
  void setTexturedBackground(Image texture);

  /**
   * Returns the {@Image} to be used as the background texture.
   *
   * @return the background texture.
   */
  Optional<Image> getBackgroundTexture();

  /**
   * Sets the {@link Grid} for the map.
   *
   * @param grid the grid for the map.
   */
  void setGrid(Grid grid);

  /**
   * Returns the {@link Grid} for the map.
   *
   * @return the grid for the map.
   */
  Optional<Grid> getGrid();

  /**
   * Returns the center of a grid the contains the passed in point. Both the point returned and the
   * point to test are in map co-ordinates.
   *
   * @param mapPoint the point with in the grid cell you want to get the center of.
   * @return the center of the grid cell.
   */
  Point2D getGridCenter(Point2D mapPoint);

  /**
   * Returns the center of a grid the contains the passed in point. Both the point returned and the
   * point to test are in map co-ordinates.
   *
   * @param mapX the X point with in the grid cell you want to get the center of.
   * @param mapY the Y point with in the grid cell you want to get the center of.
   * @return the center of the grid cell.
   */
  Point2D getGridCenter(double mapX, double mapY);

  /**
   * Puts an {@link Entity} om the map.
   *
   * @param entity {@link Entity} to add.
   */
  void putEntity(Entity entity);

  /**
   * Removes an {@link Entity} from the map.
   *
   * @param entity the {@link Entity} to remove.
   */
  void removeEntity(Entity entity);

  /**
   * Returns the {@link Entity}s on the map.
   *
   * @return the entities on the map.
   */
  public Collection<Entity> getEntities();
}
