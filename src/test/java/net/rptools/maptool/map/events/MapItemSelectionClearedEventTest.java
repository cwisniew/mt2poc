package net.rptools.maptool.map.events;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MapItemSelectionClearedEventTest {
  private static GameMap gameMap1;

  @BeforeAll
  static void setup() {
    gameMap1 = mock(GameMap.class);
  }

  @Test
  void getGameMap() {
    var event = new MapItemSelectionClearedEvent(gameMap1);
    assertEquals(gameMap1, event.getGameMap());
  }
}