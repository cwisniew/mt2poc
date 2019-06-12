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

import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.view.mappable.figures.MapFigure;

/** Event sent when a Map Figure is updated. */
public class MapFigureUpdate {

  /** * The {@link GameMap} that the game {@link MapFigure} is on. */
  private final GameMap gameMap;

  /** The {@link MapFigure} that was updated. */
  private final MapFigure mapFigure;

  /**
   * Creates a new <code>MapFigureUpdate</code> object.
   *
   * @param gameMap The game map that the game figure is on.
   * @param mapFigure The map figure that was updated.
   */
  public MapFigureUpdate(GameMap gameMap, MapFigure mapFigure) {
    this.gameMap = gameMap;
    this.mapFigure = mapFigure;
  }

  /**
   * Returns the game map that the figure that was updated is on.
   *
   * @return the game map.
   */
  public GameMap getGameMap() {
    return gameMap;
  }

  /**
   * Returns the map figure that was updated.
   *
   * @return the map figure that was updated.
   */
  public MapFigure getMapFigure() {
    return mapFigure;
  }
}
