package net.rptools.maptool.map.events;

import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.view.mappable.MapFigureImpl;

public class MapFigureUpdate {

  private final GameMap gameMap;
  private final MapFigureImpl mapFigureImpl;

  public MapFigureUpdate(GameMap gameMap, MapFigureImpl mapFigureImpl) {
    this.gameMap = gameMap;
    this.mapFigureImpl = mapFigureImpl;
  }

  public GameMap getGameMap() {
    return gameMap;
  }

  public MapFigureImpl getMapFigureImpl() {
    return mapFigureImpl;
  }

}
