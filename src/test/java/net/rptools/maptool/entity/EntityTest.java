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

import java.util.HashSet;
import java.util.Set;
import net.rptools.maptool.component.Component;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntityTest {

  private interface Component1 extends Component {};

  private interface Component2 extends Component {};

  private interface Component3 extends Component {};

  private interface Component4 extends Component {};

  private interface Component5 extends Component {};

  private interface Component6 extends Component {};

  @Test
  void getId() {
    Entity[] entities = new Entity[100];
    Set<Component> components = new HashSet<>();

    components.add(mock(Component.class));

    for (int i = 0; i < entities.length; i++) {
      entities[i] = new Entity(components);
    }

    for (int i = 0; i < entities.length; i++) {
      assertEquals(entities[i].getId(), entities[i].getId());
      for (int j = 0; j < entities.length; j++) {
        if (i != j) {
          assertNotEquals(entities[i].getId(), entities[j].getId());
        }
      }
    }
  }

  @Test
  @DisplayName("Components")
  void hasGetComponent() {
    Component component1 = mock(Component1.class);
    Component component2 = mock(Component2.class);
    Component component3 = mock(Component3.class);
    Component component4 = mock(Component4.class);
    Component component5 = mock(Component5.class);
    Component component6 = mock(Component6.class);

    var addedComponents = new HashSet<Component>();
    addedComponents.add(component1);
    addedComponents.add(component2);
    addedComponents.add(component3);

    var entity = new Entity(addedComponents);

    assertTrue(entity.hasComponent(component1.getClass()));
    assertTrue(entity.hasComponent(component2.getClass()));
    assertTrue(entity.hasComponent(component3.getClass()));
    assertFalse(entity.hasComponent(component4.getClass()));
    assertFalse(entity.hasComponent(component5.getClass()));
    assertFalse(entity.hasComponent(component6.getClass()));

    assertTrue(entity.getComponent(component1.getClass()).isPresent());
    assertEquals(component1, entity.getComponent(component1.getClass()).get());

    assertTrue(entity.getComponent(component2.getClass()).isPresent());
    assertEquals(component2, entity.getComponent(component2.getClass()).get());

    assertTrue(entity.getComponent(component3.getClass()).isPresent());
    assertEquals(component3, entity.getComponent(component3.getClass()).get());

    assertFalse(entity.getComponent(component4.getClass()).isPresent());
    assertFalse(entity.getComponent(component5.getClass()).isPresent());
    assertFalse(entity.getComponent(component6.getClass()).isPresent());

    var componentTypes = entity.getComponentTypes();
    var components = entity.getComponents();

    assertEquals(components.size(), componentTypes.size());

    assertEquals(addedComponents.size(), components.size());
    assertEquals(addedComponents.size(), componentTypes.size());

    assertTrue(components.containsAll(addedComponents));
    for (var c : addedComponents) {
      assertTrue(componentTypes.contains(c.getClass()));
    }
  }
}
