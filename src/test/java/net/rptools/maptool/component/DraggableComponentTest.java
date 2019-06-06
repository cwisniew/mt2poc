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
package net.rptools.maptool.component;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.Random;
import net.rptools.maptool.entity.Entity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DraggableComponentTest {

  private static Random random;

  @BeforeAll
  static void setup() {
    random = new Random();
  }

  @Test
  void isBeingDragged() {
    DraggableComponent dc = new DraggableComponent();
    for (int i = 0; i < 1000; i++) {
      boolean val = random.nextBoolean();
      dc.setBeingDragged(val);
      assertEquals(val, dc.isBeingDragged());
    }

    Entity entity = mock(Entity.class);
    when(entity.getComponent(DraggableComponent.class)).thenReturn(Optional.empty());
    when(entity.hasComponent(DraggableComponent.class)).thenReturn(false);

    assertFalse(DraggableComponent.isBeingDragged(entity));

    when(entity.getComponent(DraggableComponent.class)).thenReturn(Optional.of(dc));
    when(entity.hasComponent(DraggableComponent.class)).thenReturn(true);

    dc.setBeingDragged(true);
    assertTrue(DraggableComponent.isBeingDragged(entity));

    dc.setBeingDragged(false);
    assertFalse(DraggableComponent.isBeingDragged(entity));
  }

  @Test
  void isDraggable() {
    DraggableComponent dc = new DraggableComponent();

    Entity entity = mock(Entity.class);
    when(entity.hasComponent(DraggableComponent.class)).thenReturn(false);

    assertFalse(DraggableComponent.isDraggable(entity));

    when(entity.getComponent(DraggableComponent.class)).thenReturn(Optional.of(dc));
    when(entity.hasComponent(DraggableComponent.class)).thenReturn(true);

    dc.setBeingDragged(true);
    assertTrue(DraggableComponent.isDraggable(entity));

    dc.setBeingDragged(false);
    assertTrue(DraggableComponent.isDraggable(entity));
  }

  @Test
  void getId() {
    DraggableComponent[] draggable = new DraggableComponent[100];
    for (int i = 0; i < draggable.length; i++) {
      draggable[i] = new DraggableComponent();
    }

    for (int i = 0; i < draggable.length; i++) {
      for (int j = 0; j < draggable.length; j++) {
        if (i == j) {
          assertEquals(draggable[i].getId(), draggable[j].getId());
        } else {
          assertNotEquals(draggable[i].getId(), draggable[j].getId());
        }
      }
    }
  }

  @Test
  void fromX() {
    DraggableComponent dc = new DraggableComponent();
    for (int i = 0; i < 1000; i++) {
      double val = random.nextDouble();
      dc.setFromX(val);
      assertEquals(val, dc.getFromX());
    }
  }

  @Test
  void fromY() {
    DraggableComponent dc = new DraggableComponent();
    for (int i = 0; i < 1000; i++) {
      double val = random.nextDouble();
      dc.setFromY(val);
      assertEquals(val, dc.getFromY());
    }
  }

  @Test
  void toX() {
    DraggableComponent dc = new DraggableComponent();
    for (int i = 0; i < 1000; i++) {
      double val = random.nextDouble();
      dc.setToX(val);
      assertEquals(val, dc.getToX());
    }
  }

  @Test
  void toY() {
    DraggableComponent dc = new DraggableComponent();
    for (int i = 0; i < 1000; i++) {
      double val = random.nextDouble();
      dc.setToY(val);
      assertEquals(val, dc.getToY());
    }
  }
}
