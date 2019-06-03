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

/** This event is sent when an {@link Entity} is removed from a {@link GameMap}. */
public class MapEntityRemovedEvent {

  /** The {@link GameMap} that the {@link Entity} was removed from.. */
  private final GameMap gameMap;

  /** The {@link Entity} that was removed from the {@link GameMap}. */
  private final Entity entity;

  /**
   * Creates a new <code>MapEntityRemovedEvent</code>.
   *
   * @param gameMap The map that the entity was removed from.
   * @param entity The entity that was removed.
   */
  public MapEntityRemovedEvent(GameMap gameMap, Entity entity) {
    this.gameMap = gameMap;
    this.entity = entity;
  }

  /**
   * Returns the {@link GameMap} that the {@link Entity} was removed from.
   *
   * @return the game map.
   */
  public GameMap getGameMap() {
    return gameMap;
  }

  /**
   * Returns the {@link Entity} that was removed.
   *
   * @return the entity that was removed.
   */
  public Entity getEntity() {
    return entity;
  }

  /**
   * Returns the id of the game map that the entity was removed from.
   *
   * @return the id of the game map.
   */
  public UUID getMapId() {
    return gameMap.getId();
  }

  /**
   * Returns the id of the entity that was removed.
   *
   * @return the id of the entity that was removed.
   */
  public UUID getEntityId() {
    return entity.getId();
  }
}
