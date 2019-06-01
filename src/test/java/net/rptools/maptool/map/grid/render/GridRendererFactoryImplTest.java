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

import static org.junit.jupiter.api.Assertions.*;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.multibindings.MapBinder;
import net.rptools.maptool.map.grid.Grid;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GridRendererFactoryImplTest {

  interface Grid1 extends Grid {};

  interface Grid2 extends Grid {};

  interface Grid3 extends Grid {};

  @Mock Grid1 grid1;

  @Mock GridRenderer<Grid1> grend1;

  @Mock Grid2 grid2;

  @Mock GridRenderer<Grid2> grend2;

  @Mock Grid3 grid3;

  @Mock GridRenderer<Grid3> grend3;

  class TestModule extends AbstractModule {

    @Override
    protected void configure() {

      bind(GridRendererFactory.class).to(GridRendererFactoryImpl.class);

      MapBinder<Class, GridRenderer> grMapBinder =
          MapBinder.newMapBinder(binder(), Class.class, GridRenderer.class);
      grMapBinder.addBinding(grid1.getClass()).toInstance(grend1);
      grMapBinder.addBinding(grid2.getClass()).toInstance(grend2);
    }
  }

  @Test
  void renderFor() {

    Injector injector = Guice.createInjector(new TestModule());
    GridRendererFactory grf = injector.getInstance(GridRendererFactory.class);

    assertTrue(grf.rendererFor(grid1).isPresent());
    assertEquals(grend1, grf.rendererFor(grid1).get());
    assertTrue(grf.rendererFor(grid2).isPresent());
    assertEquals(grend2, grf.rendererFor(grid2).get());
    assertFalse(grf.rendererFor(grid3).isPresent());
  }
}
