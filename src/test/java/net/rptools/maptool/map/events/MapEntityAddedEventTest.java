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
package net.rptools.maptool.map.events;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MapEntityAddedEventTest {

  private static GameMap gameMap1;

  private static UUID gameMap1Id;

  private static Entity entity1;

  private static UUID entry1Id;

  @BeforeAll
  static void setup() {
    gameMap1 = mock(GameMap.class);
    gameMap1Id = UUID.randomUUID();
    when(gameMap1.getId()).thenReturn(gameMap1Id);

    entity1 = mock(Entity.class);
    entry1Id = UUID.randomUUID();
    when(entity1.getId()).thenReturn(entry1Id);
  }

  @Test
  void getGameMap() {
    var event = new MapEntityAddedEvent(gameMap1, entity1);
    assertEquals(gameMap1, event.getGameMap());
  }

  @Test
  void getEntity() {
    var event = new MapEntityAddedEvent(gameMap1, entity1);
    assertEquals(entity1, event.getEntity());
  }

  @Test
  void getMapId() {
    var event = new MapEntityAddedEvent(gameMap1, entity1);
    assertEquals(gameMap1.getId(), event.getGameMap().getId());
  }

  @Test
  void getEntityId() {
    var event = new MapEntityAddedEvent(gameMap1, entity1);
    assertEquals(entity1.getId(), event.getEntity().getId());
  }
}
