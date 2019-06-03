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
package net.rptools.maptool.map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.eventbus.EventBus;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.grid.Grid;
import org.junit.jupiter.api.Test;

class GameMapImplTest {

  @Test
  void getId() {
    EventBus eventBus = mock(EventBus.class);
    GameMap gameMap1 = new GameMapImpl(eventBus);
    GameMap gameMap2 = new GameMapImpl(eventBus);

    assertEquals(gameMap1.getId(), gameMap1.getId());
    assertNotEquals(gameMap1.getId(), gameMap2.getId());
  }

  @Test
  void texturedBackground() {
    EventBus eventBus = mock(EventBus.class);
    GameMap gameMap = new GameMapImpl(eventBus);

    Image img = mock(Image.class);

    assertFalse(gameMap.getBackgroundTexture().isPresent());

    gameMap.setTexturedBackground(img);
    assertTrue(gameMap.getBackgroundTexture().isPresent());
    assertEquals(img, gameMap.getBackgroundTexture().get());
  }

  @Test
  void grid() {
    EventBus eventBus = mock(EventBus.class);
    GameMap gameMap = new GameMapImpl(eventBus);

    Grid grid = mock(Grid.class);

    assertFalse(gameMap.getGrid().isPresent());

    gameMap.setGrid(grid);
    assertTrue(gameMap.getGrid().isPresent());
    assertEquals(grid, gameMap.getGrid().get());
  }

  @Test
  void getGridCenter() {
    EventBus eventBus = mock(EventBus.class);
    GameMap gameMap = new GameMapImpl(eventBus);

    Grid grid = mock(Grid.class);
    Point2D point1 = new Point2D(1, 1);
    Point2D point1c = new Point2D(5, 5);
    Point2D point2 = new Point2D(20, 20);
    Point2D point2c = new Point2D(25, 25);

    when(grid.getGridCenter(point1)).thenReturn(point1c);
    when(grid.getGridCenter(point2)).thenReturn(point2c);

    gameMap.setGrid(grid);

    assertEquals(point1c, gameMap.getGridCenter(point1));
    assertEquals(point2c, gameMap.getGridCenter(point2));
  }

  @Test
  void putRemoveEntity() {
    EventBus eventBus = mock(EventBus.class);
    GameMap gameMap = new GameMapImpl(eventBus);

    Entity entity1 = mock(Entity.class);
    Entity entity2 = mock(Entity.class);

    gameMap.putEntity(entity1);
    gameMap.putEntity(entity2);
    assertTrue(gameMap.getEntities().contains(entity1));
    assertTrue(gameMap.getEntities().contains(entity2));

    gameMap.removeEntity(entity1);
    assertTrue(gameMap.getEntities().contains(entity2));
    assertFalse(gameMap.getEntities().contains(entity1));
    gameMap.removeEntity(entity2);
    assertFalse(gameMap.getEntities().contains(entity1));
    assertFalse(gameMap.getEntities().contains(entity2));
  }
}
