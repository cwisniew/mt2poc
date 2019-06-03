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
package net.rptools.maptool.map.events;

import java.util.UUID;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;

/** This event is sent when an {@link Entity} is added to a {@link GameMap}. */
public class MapEntityAddedEvent {

  /** The {@link GameMap} that the {@link Entity} was added to. */
  private final GameMap gameMap;

  /** The {@link Entity} that the {@link GameMap} was added to. */
  private final Entity entity;

  /**
   * Creates a new <code>MapEntityAddedEvent</code>.
   *
   * @param gameMap The map that the {@link Entity} was added to.
   * @param entity The entity to add to the {@link GameMap}.
   */
  public MapEntityAddedEvent(GameMap gameMap, Entity entity) {
    this.gameMap = gameMap;
    this.entity = entity;
  }

  /**
   * Returns the {@link GameMap} that the {@link Entity} was added to.
   *
   * @return the map that the {@link Entity} was added to.
   */
  public GameMap getGameMap() {
    return gameMap;
  }

  /**
   * Returns {@link Entity} that was added to the {@link GameMap}.
   *
   * @return the entity that was added to the {@link GameMap}.
   */
  public Entity getEntity() {
    return entity;
  }

  /**
   * Returns the id of the {@link GameMap}.
   *
   * @return the id of the {@link GameMap}.
   */
  public UUID getMapId() {
    return gameMap.getId();
  }

  /**
   * Returns the id of the {@link Entity}.
   *
   * @return the id of the {@link Entity}.
   */
  public UUID getEntityId() {
    return entity.getId();
  }
}
