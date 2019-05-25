package net.rptools.maptool.renderer.map;

import javafx.scene.layout.Pane;

/**
 * Interface for classes that is a view of a (@link GameMap}.
 */
public interface MapView {

  /**
   * Gets the {@link Pane} node for view.
   * @return the parent node for the view.
   */
  Pane getParentNode();

  /**
   * Returns the {@link GameMap} this is a view for.
   * @return the game map this view is for.
   */
  GameMap getGameMap();

}
