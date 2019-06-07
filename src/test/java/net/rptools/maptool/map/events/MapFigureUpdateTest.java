package net.rptools.maptool.map.events;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.view.mappable.MapFigure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MapFigureUpdateTest {

  private static GameMap gameMap1;

  private static MapFigure mapFigure1;



  @BeforeAll
  static void setup() {
    gameMap1 = mock(GameMap.class);

    mapFigure1 = mock(MapFigure.class);
  }

  @Test
  void getGameMap() {
    var event = new MapFigureUpdate(gameMap1, mapFigure1);
    assertEquals(gameMap1, event.getGameMap());
  }

  @Test
  void getMapFigureImpl() {
    var event = new MapFigureUpdate(gameMap1, mapFigure1);
    assertEquals(mapFigure1, event.getMapFigure());
  }
}