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
package net.rptools.maptool.map.view.tool.factory;

import com.google.inject.assistedinject.Assisted;
import javafx.scene.canvas.Canvas;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.map.view.tool.PointerTool;

/** Interface for assisted injection of {@link PointerTool} by google guice. */
public interface PointerToolFactory {

  /**
   * Creates the new {@link PointerTool}.
   *
   * @param view The {@link MapView} this tool is for.
   * @param backgroundCanvas a {@link Canvas} that the tool can render to behind most other
   *     controls.
   * @param foregroundCanvas a {@link Canvas} that the tool can render to behind most other
   *     controls.
   * @return the tool.
   */
  PointerTool create(
      MapView view,
      @Assisted("backgroundCanvas") Canvas backgroundCanvas,
      @Assisted("foregroundCanvas") Canvas foregroundCanvas);
}
