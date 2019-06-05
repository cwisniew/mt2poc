package net.rptools.maptool.map.view.mappable;

import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.view.MapViewPort;

public interface MapFigureFactory {
  MapFigureImpl create(GameMap map, MapViewPort viewPort, Entity en);
}
