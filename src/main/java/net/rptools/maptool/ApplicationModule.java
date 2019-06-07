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
package net.rptools.maptool;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.MapBinder;
import javafx.fxml.FXMLLoader;
import net.rptools.maptool.entity.EntityFactory;
import net.rptools.maptool.entity.EntityFactoryImpl;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.GameMapImpl;
import net.rptools.maptool.map.grid.RectangleGrid;
import net.rptools.maptool.map.grid.render.GridRenderer;
import net.rptools.maptool.map.grid.render.GridRendererFactory;
import net.rptools.maptool.map.grid.render.GridRendererFactoryImpl;
import net.rptools.maptool.map.grid.render.RectangleGridRenderer;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.map.view.MapViewImpl;
import net.rptools.maptool.map.view.MapViewPort;
import net.rptools.maptool.map.view.MapViewPortImpl;
import net.rptools.maptool.map.view.tool.PointerTool;
import net.rptools.maptool.map.view.tool.RectangleTool;
import net.rptools.maptool.map.view.tool.factory.MapViewToolFactory;
import net.rptools.maptool.map.view.tool.factory.MapViewToolFactoryImpl;
import net.rptools.maptool.map.view.tool.factory.PointerToolFactory;
import net.rptools.maptool.map.view.tool.factory.RectangleToolFactory;
import net.rptools.maptool.ui.FXMLLoaderProvier;

/** <code>ApplicationModule</code> used for Google Guice injection bindings. */
public class ApplicationModule extends AbstractModule {

  /** the {@link EventBus} used for sending events. */
  private final EventBus eventBus = new EventBus("render-poc Event Bus");

  /** The {@link GridRendererFactory} used to obtain renderer for grids. */
  private final GridRendererFactory gridRendererFactory = new GridRendererFactoryImpl();

  private final AppConfig appConfig = new AppConfigImpl();

  @Override
  protected void configure() {
    bind(EventBus.class).toInstance(eventBus);

    // Map Related Bindings
    bind(GameMap.class).to(GameMapImpl.class);
    bind(MapView.class).to(MapViewImpl.class);

    bind(GridRendererFactory.class).toInstance(gridRendererFactory);
    MapBinder<Class, GridRenderer> grMapBinder =
        MapBinder.newMapBinder(binder(), Class.class, GridRenderer.class);
    grMapBinder.addBinding(RectangleGrid.class).to(RectangleGridRenderer.class);

    bind(MapViewPort.class).to(MapViewPortImpl.class);

    // Configuration values
    bind(AppConfig.class).toInstance(appConfig);

    // FXMLLoader
    bind(FXMLLoader.class).toProvider(FXMLLoaderProvier.class);

    // Entity
    bind(EntityFactory.class).to(EntityFactoryImpl.class);

    // Map View Tools
    bind(MapViewToolFactory.class).to(MapViewToolFactoryImpl.class);

    install(
        new FactoryModuleBuilder()
            .implement(PointerTool.class, PointerTool.class)
            .build(PointerToolFactory.class));

    install(
        new FactoryModuleBuilder()
            .implement(RectangleTool.class, RectangleTool.class)
            .build(RectangleToolFactory.class));
  }
}
