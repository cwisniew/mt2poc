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

import net.rptools.maptool.component.Component;
import org.junit.jupiter.api.Test;

class EntityBuilderTest {

  private interface Component1 extends Component {};

  private interface Component2 extends Component {};

  private interface Component3 extends Component {};

  private interface Component4 extends Component {};

  private interface Component5 extends Component {};

  private interface Component6 extends Component {};

  @Test
  void build() {

    Component component1 = mock(Component1.class);
    Component component2 = mock(Component2.class);
    Component component3 = mock(Component3.class);
    Component component4 = mock(Component4.class);
    Component component5 = mock(Component5.class);
    Component component6 = mock(Component6.class);

    EntityBuilder builder = new EntityBuilder();

    Entity entity =
        builder
            .with(component1)
            .with(component2)
            .with(component3)
            .with(component4)
            .with(component5)
            .with(component6)
            .build();

    assertTrue(entity.hasComponent(component1.getClass()));
    assertTrue(entity.hasComponent(component2.getClass()));
    assertTrue(entity.hasComponent(component3.getClass()));
    assertTrue(entity.hasComponent(component4.getClass()));
    assertTrue(entity.hasComponent(component5.getClass()));
    assertTrue(entity.hasComponent(component6.getClass()));
  }
}
