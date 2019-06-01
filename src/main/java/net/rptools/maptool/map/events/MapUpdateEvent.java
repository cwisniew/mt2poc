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

/** This event is sent when a {@link net.rptools.maptool.map.GameMap} is updated. */
public class MapUpdateEvent {

  /** The id of the updated {@link net.rptools.maptool.map.GameMap}. */
  private final UUID mapId;

  /**
   * Creates a new <code>MapUpdateEvent</code>
   *
   * @param id The id of the {@link net.rptools.maptool.map.GameMap} that was updated.
   */
  public MapUpdateEvent(UUID id) {
    mapId = id;
  }

  /**
   * The id of the {@link net.rptools.maptool.map.GameMap} that was updated.
   *
   * @return the id of the map that was updated.
   */
  public UUID getMapId() {
    return mapId;
  }

  @Override
  public String toString() {
    return "MapUpdateEvent{" + "mapId=" + mapId + '}';
  }
}
