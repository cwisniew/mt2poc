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
package net.rptools.maptool.map.view.tool;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.rptools.maptool.map.view.MapView;

public class RectangleTool extends MapViewTool {

  /**
   * Creates a new <code>RectangleTool</code>.
   *
   * @param view the {@link MapView}.
   */
  @Inject
  public RectangleTool(@Assisted MapView view) {
    super(view);
  }
}
