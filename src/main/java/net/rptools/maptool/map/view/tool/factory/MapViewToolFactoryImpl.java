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

import com.google.inject.Inject;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.map.view.tool.PointerTool;
import net.rptools.maptool.map.view.tool.RectangleTool;

/** Factory class for creating different {@link MapViewTool]s.} */
public class MapViewToolFactoryImpl implements MapViewToolFactory {

  @Inject private PointerToolFactory pointerToolFactory;

  @Inject private RectangleToolFactory rectangleToolFactory;

  @Override
  public PointerTool createPointerTool(MapView view) {
    return pointerToolFactory.create(view);
  }

  @Override
  public RectangleTool createRectangleTool(MapView view) {
    return rectangleToolFactory.create(view);
  }
}
