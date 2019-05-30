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
package net.rptools.maptool.renderer;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import net.rptools.maptool.renderer.map.GameMap;
import net.rptools.maptool.renderer.map.GameMapImpl;
import net.rptools.maptool.renderer.map.grid.SquareGrid;
import net.rptools.maptool.renderer.map.grid.render.GridRenderer;
import net.rptools.maptool.renderer.map.grid.render.GridRendererFactory;
import net.rptools.maptool.renderer.map.grid.render.GridRendererFactoryImpl;
import net.rptools.maptool.renderer.map.grid.render.SquareGridRenderer;
import net.rptools.maptool.renderer.map.view.MapView;
import net.rptools.maptool.renderer.map.view.MapViewImpl;
import net.rptools.maptool.renderer.map.view.MapViewPort;
import net.rptools.maptool.renderer.map.view.MapViewPortImpl;

/** <code>ApplicationModule</code> used for Google Guice injection bindings. */
public class ApplicationModule extends AbstractModule {

  /** the {@link EventBus} used for sending events. */
  private final EventBus eventBus = new EventBus("render-poc Event Bus");

  /** The {@link GridRendererFactory} used to obtain renderer for grids. */
  private final GridRendererFactory gridRendererFactory = new GridRendererFactoryImpl();

  @Override
  protected void configure() {
    bind(EventBus.class).toInstance(eventBus);
    bind(GameMap.class).to(GameMapImpl.class);
    bind(MapView.class).to(MapViewImpl.class);

    bind(GridRendererFactory.class).toInstance(gridRendererFactory);
    MapBinder<Class, GridRenderer> grMapBinder =
        MapBinder.newMapBinder(binder(), Class.class, GridRenderer.class);
    grMapBinder.addBinding(SquareGrid.class).to(SquareGridRenderer.class);

    bind(MapViewPort.class).to(MapViewPortImpl.class);
  }
}
