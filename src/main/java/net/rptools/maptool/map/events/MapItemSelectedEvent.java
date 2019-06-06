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

import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;

public class MapItemSelectedEvent {

  private final GameMap gameMap;

  private final Entity entity;

  public MapItemSelectedEvent(GameMap map, Entity ent) {
    gameMap = map;
    entity = ent;
  }

  public GameMap getGameMap() {
    return gameMap;
  }

  public Entity getEntity() {
    return entity;
  }
}
