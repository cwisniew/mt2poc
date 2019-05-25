package net.rptools.maptool.renderer.map;

import javafx.scene.Parent;

public interface MapView {

  Parent getParentComponent();

  GameMap getGameMap();

}
