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
package net.rptools.maptool.map.grid.render;

import javafx.scene.canvas.Canvas;
import net.rptools.maptool.map.grid.Grid;
import net.rptools.maptool.map.view.MapViewPort;

/** Interface implemented by classes that can render a grid. */
public interface GridRenderer<T extends Grid> {

  /**
   * Renders the grid to a {@link Canvas}.
   *
   * @param canvas the {@link Canvas} to draw the grid to.
   * @param grid the {@link Grid} to be drawn.
   * @param gridLine the {@link GridLine} with the details of how grid lines should be drawn.
   * @param viewPort the {@link MapViewPort} used to convert between co-ordinates.
   */
  void render(Canvas canvas, T grid, GridLine gridLine, MapViewPort viewPort);
}
