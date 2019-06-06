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
package net.rptools.maptool.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import javafx.scene.image.Image;
import net.rptools.maptool.component.Component;
import net.rptools.maptool.component.DraggableComponent;
import net.rptools.maptool.component.ImageComponent;
import net.rptools.maptool.component.PositionComponent;
import org.junit.jupiter.api.Test;

class EntityFactoryImplTest {

  @Test
  void createEntity() {
    EntityFactory ef = new EntityFactoryImpl();
    var entity = ef.createEntity().build();

    assertEquals(0, entity.getComponents().size());
    assertEquals(0, entity.getComponentTypes().size());

    Component component = mock(Component.class);
  }

  @Test
  void createMapFigure() {
    EntityFactory ef = new EntityFactoryImpl();
    var entity = ef.createMapFigure(1, 1, 1, mock(Image.class));

    assertTrue(entity.hasComponent(PositionComponent.class));
    assertTrue(entity.hasComponent(ImageComponent.class));
    assertTrue(entity.hasComponent(DraggableComponent.class));

    assertEquals(3, entity.getComponentTypes().size());

    PositionComponent pc = entity.getComponent(PositionComponent.class).get();
    assertFalse(pc.isSnapToGrid());
  }

  @Test
  void createSnapToGridMapFigure() {
    EntityFactory ef = new EntityFactoryImpl();
    var entity = ef.createSnapToGridMapFigure(1, 1, 1, mock(Image.class));

    assertTrue(entity.hasComponent(PositionComponent.class));
    assertTrue(entity.hasComponent(ImageComponent.class));
    assertTrue(entity.hasComponent(DraggableComponent.class));

    assertEquals(3, entity.getComponentTypes().size());

    PositionComponent pc = entity.getComponent(PositionComponent.class).get();
    assertTrue(pc.isSnapToGrid());
  }
}
