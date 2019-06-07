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
import net.rptools.maptool.map.view.mappable.MapFigure;

/** Event fired when dragging of a {@link MapFigure} starts. */
public class MapFigureDragStart {

  private final GameMap gameMap;
  private final MapFigure mapFigure;

  /**
   * Creates a new <code>MapFigureDragStart</code>.
   *
   * @param gameMap The {@link GameMap} that the represents an entity on.
   * @param mapFigure The {@link MapFigure} that was dragged.
   */
  public MapFigureDragStart(GameMap gameMap, MapFigure mapFigure) {
    this.gameMap = gameMap;
    this.mapFigure = mapFigure;
  }

  /**
   * Returns the {@link GameMap} that the {@link MapFigure} represents an entity on.
   *
   * @return the {@link GameMap}.
   */
  public GameMap getGameMap() {
    return gameMap;
  }

  /**
   * Returns the {@link MapFigure} that is being dragged.
   *
   * @return the {@link MapFigure}.
   */
  public MapFigure getMapFigure() {
    return mapFigure;
  }
}
