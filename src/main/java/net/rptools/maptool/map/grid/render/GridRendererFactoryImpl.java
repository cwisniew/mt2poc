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

import com.google.inject.Inject;
import java.util.Map;
import java.util.Optional;
import net.rptools.maptool.map.grid.Grid;

/**
 * The default implementation of the {@link GridRendererFactory} interface used to return a {@link
 * GridRenderer} for a specific {@link Grid}.
 */
public class GridRendererFactoryImpl implements GridRendererFactory {

  @Inject private Map<Class, GridRenderer> gridRendererMap;

  @Override
  public Optional<GridRenderer> rendererFor(Grid grid) {
    return Optional.ofNullable(gridRendererMap.get(grid.getClass()));
  }
}
