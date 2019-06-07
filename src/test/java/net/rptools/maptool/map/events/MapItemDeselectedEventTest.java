package net.rptools.maptool.map.events;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MapItemDeselectedEventTest {


  private static GameMap gameMap1;
  private static Entity entity1;

  @BeforeAll
  static void setup() {
    gameMap1 = mock(GameMap.class);

    entity1 = mock(Entity.class);
  }

  @Test
  void getGameMap() {
    var event = new MapItemDeselectedEvent(gameMap1, entity1);
    assertEquals(gameMap1, event.getGameMap());
  }

  @Test
  void getEntity() {
    var event = new MapItemDeselectedEvent(gameMap1, entity1);
    assertEquals(entity1, event.getEntity());
  }
}