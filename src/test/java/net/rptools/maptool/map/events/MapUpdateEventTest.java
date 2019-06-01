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

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MapUpdateEventTest {

  @Test
  @DisplayName("Get ID same as constructor.")
  void testGetId() {
    for (int i = 0; i < 20; i++) {
      UUID id = UUID.randomUUID();
      MapUpdateEvent event = new MapUpdateEvent(id);
      assertEquals(id, event.getMapId());
    }
  }

  @Test
  @DisplayName("toString() as expected.")
  void testToString() {
    for (int i = 0; i < 20; i++) {
      UUID id = UUID.randomUUID();
      MapUpdateEvent event = new MapUpdateEvent(id);
      assertEquals("MapUpdateEvent{" + "mapId=" + id + '}', event.toString());
    }
  }
}
