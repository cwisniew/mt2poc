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

class PositionComponentTest {
  private static Random random;

  @BeforeAll
  static void setup() {
    random = new Random();
  }

  @Test
  void getX() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      PositionComponent pc = new PositionComponent(x, y, z);
      assertEquals(x, pc.getX());
    }
  }

  @Test
  void setX() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double x2 = x + 10;

      PositionComponent pc = new PositionComponent(x, y, z);
      pc.setX(x2);
      assertEquals(x2, pc.getX());
    }
  }

  @Test
  void getY() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      PositionComponent pc = new PositionComponent(x, y, z);
      assertEquals(y, pc.getY());
    }
  }

  @Test
  void setY() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double y2 = x + 10;

      PositionComponent pc = new PositionComponent(x, y, z);
      pc.setY(y2);
      assertEquals(y2, pc.getY());
    }
  }

  @Test
  void getZ() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double z2 = x + 10;

      PositionComponent pc = new PositionComponent(x, y, z);
      pc.setZ(z2);
      assertEquals(z2, pc.getZ());
    }
  }

  @Test
  void setZ() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      PositionComponent pc = new PositionComponent(x, y, z);
      assertEquals(z, pc.getZ());
    }
  }

  @Test
  void testEquals() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double x2 = x + 10;
      double y2 = x + 10;
      double z2 = x + 10;

      PositionComponent pc1 = new PositionComponent(x, y, z);
      PositionComponent pc2 = new PositionComponent(x, y, z);

      PositionComponent pcx2 = new PositionComponent(x2, y, z);
      PositionComponent pcy2 = new PositionComponent(x, y2, z);
      PositionComponent pcz2 = new PositionComponent(x, y, z2);

      assertEquals(pc1, pc2);
      assertEquals(pc1, pc1);
      assertNotEquals(pc1, pcx2);
      assertNotEquals(pc1, pcy2);
      assertNotEquals(pc1, pcz2);
      assertFalse(pc1.equals(null));
    }
  }

  @Test
  void testHashCode() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double x2 = x + 10;
      double y2 = x + 10;
      double z2 = x + 10;

      PositionComponent pc1 = new PositionComponent(x, y, z);
      PositionComponent pc2 = new PositionComponent(x, y, z);

      PositionComponent pcx2 = new PositionComponent(x2, y, z);
      PositionComponent pcy2 = new PositionComponent(x, y2, z);
      PositionComponent pcz2 = new PositionComponent(x, y, z2);

      assertEquals(pc1.hashCode(), pc2.hashCode());
      assertEquals(pc1.hashCode(), pc1.hashCode());
      assertNotEquals(pc1.hashCode(), pcx2.hashCode());
      assertNotEquals(pc1.hashCode(), pcy2.hashCode());
      assertNotEquals(pc1.hashCode(), pcz2.hashCode());
    }
  }

  @Test
  void snapToGrid() {
    PositionComponent positionComponent = new PositionComponent(0, 0, 0);
    for (int i = 0; i < 100; i++) {
      boolean snap = random.nextBoolean();
      positionComponent.setSnapToGrid(snap);

      assertEquals(snap, positionComponent.isSnapToGrid());
    }

    Entity entity = mock(Entity.class);
    when(entity.getComponent(PositionComponent.class)).thenReturn(Optional.empty());
    when(entity.hasComponent(PositionComponent.class)).thenReturn(false);

    assertFalse(PositionComponent.isSnapToGrid(entity));

    when(entity.getComponent(PositionComponent.class)).thenReturn(Optional.of(positionComponent));
    when(entity.hasComponent(PositionComponent.class)).thenReturn(true);

    positionComponent.setSnapToGrid(true);
    assertTrue(PositionComponent.isSnapToGrid(entity));

    positionComponent.setSnapToGrid(false);
    assertFalse(PositionComponent.isSnapToGrid(entity));
  }

  @Test
  void getUUID() {
    PositionComponent[] positionComponents = new PositionComponent[100];
    for (int i = 0; i < positionComponents.length; i++) {
      positionComponents[i] = new PositionComponent(0, 0, 0);
    }

    for (int i = 0; i < positionComponents.length; i++) {
      for (int j = 0; j < positionComponents.length; j++) {
        if (i == j) {
          assertEquals(positionComponents[i].getId(), positionComponents[j].getId());
        } else {
          assertNotEquals(positionComponents[i].getId(), positionComponents[j].getId());
        }
      }
    }
  }
}
