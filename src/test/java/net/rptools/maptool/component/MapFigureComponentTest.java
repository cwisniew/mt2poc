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

class MapFigureComponentTest {
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
      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, w, h, z);
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

      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, h, w, z);
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
      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, w, h, w);
      assertEquals(y, pc.getY());
    }
  }

  @Test
  void setY() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;
      double w = z + 10;
      double h = w + 10;

      double y2 = x + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, w, h, z);
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
      double w = z + 10;
      double h = w + 10;

      double z2 = x + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, z, h, z);
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
      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, w, h, z);
      assertEquals(z, pc.getZ());
    }
  }

  @Test
  void getWidth() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;
      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, w, h, z);
      assertEquals(w, pc.getWidth());
    }
  }

  @Test
  void setWidth() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double w2 = x + 10;

      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, h, w, z);
      pc.setWidth(w2);
      assertEquals(w2, pc.getWidth());
    }
  }



  @Test
  void getHeight() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;
      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, w, h, z);
      assertEquals(h, pc.getHeight());
    }
  }

  @Test
  void setHeight() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;

      double h2 = x + 10;

      double w = z + 10;
      double h = w + 10;

      MapFigureComponent pc = new MapFigureComponent(x, y, h, w, z);
      pc.setHeight(h2);
      assertEquals(h2, pc.getHeight());
    }
  }

  @Test
  void testEquals() {
    for (int i = 0; i < 20; i++) {
      double x = random.nextDouble();
      double y = x - 2;
      double z = x + 2;
      double w = z + 10;
      double h = w + 10;

      double x2 = x + 10;
      double y2 = x + 10;
      double z2 = x + 10;

      MapFigureComponent pc1 = new MapFigureComponent(x, y, w, h, z);
      MapFigureComponent pc2 = new MapFigureComponent(x, y, w, h, z);

      MapFigureComponent pcx2 = new MapFigureComponent(x2, y, w, h, z);
      MapFigureComponent pcy2 = new MapFigureComponent(x, y2, w, h, z);
      MapFigureComponent pcz2 = new MapFigureComponent(x, y, w, h, z2);

      assertNotEquals(pc1, pc2);
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
      double w = z + 10;
      double h = w + 10;

      double x2 = x + 10;
      double y2 = x + 10;
      double z2 = x + 10;

      MapFigureComponent pc1 = new MapFigureComponent(x, y, w, h, z);
      MapFigureComponent pc2 = new MapFigureComponent(x, y, w, h, z);

      MapFigureComponent pcx2 = new MapFigureComponent(x2, y, w, h, z);
      MapFigureComponent pcy2 = new MapFigureComponent(x, y2, w, h, z);
      MapFigureComponent pcz2 = new MapFigureComponent(x, y, w, h, z2);

      assertEquals(pc1.hashCode(), pc2.hashCode());
      assertEquals(pc1.hashCode(), pc1.hashCode());
      assertNotEquals(pc1.hashCode(), pcx2.hashCode());
      assertNotEquals(pc1.hashCode(), pcy2.hashCode());
      assertNotEquals(pc1.hashCode(), pcz2.hashCode());
    }
  }

  @Test
  void snapToGrid() {
    MapFigureComponent positionComponent = new MapFigureComponent(0, 0, 0, 0, 0);
    for (int i = 0; i < 100; i++) {
      boolean snap = random.nextBoolean();
      positionComponent.setSnapToGrid(snap);

      assertEquals(snap, positionComponent.isSnapToGrid());
    }

    Entity entity = mock(Entity.class);
    when(entity.getComponent(MapFigureComponent.class)).thenReturn(Optional.empty());
    when(entity.hasComponent(MapFigureComponent.class)).thenReturn(false);

    assertFalse(MapFigureComponent.isSnapToGrid(entity));

    when(entity.getComponent(MapFigureComponent.class)).thenReturn(Optional.of(positionComponent));
    when(entity.hasComponent(MapFigureComponent.class)).thenReturn(true);

    positionComponent.setSnapToGrid(true);
    assertTrue(MapFigureComponent.isSnapToGrid(entity));

    positionComponent.setSnapToGrid(false);
    assertFalse(MapFigureComponent.isSnapToGrid(entity));
  }

  @Test
  void getUUID() {
    MapFigureComponent[] positionComponents = new MapFigureComponent[100];
    for (int i = 0; i < positionComponents.length; i++) {
      positionComponents[i] = new MapFigureComponent(0, 0, 0, 0,0);
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
