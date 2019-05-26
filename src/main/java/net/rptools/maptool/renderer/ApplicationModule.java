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
import net.rptools.maptool.renderer.map.GameMap;
import net.rptools.maptool.renderer.map.GameMapImpl;
import net.rptools.maptool.renderer.map.MapView;
import net.rptools.maptool.renderer.map.MapViewImpl;

/** <code>ApplicationModule</code> used for Google Guice injection bindings. */
public class ApplicationModule extends AbstractModule {

  /** the {@link EventBus} used for sending events. */
  private final EventBus eventBus = new EventBus("render-poc Event Bus");

  @Override
  protected void configure() {
    bind(EventBus.class).toInstance(eventBus);
    bind(GameMap.class).to(GameMapImpl.class);
    bind(MapView.class).to(MapViewImpl.class);
  }
}
