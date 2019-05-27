package net.rptools.maptool.renderer.map.grid.render;

import javafx.scene.canvas.Canvas;
import net.rptools.maptool.renderer.map.grid.Grid;
import net.rptools.maptool.renderer.map.view.MapViewPort;

/**
 * Interface implemented by classes that can render a grid.
 */
public interface GridRenderer<T extends Grid> {

  /**
   * Renders the grid to a {@link Canvas}.
   *
   * @param canvas the {@link Canvas} to draw the grid to.
   * @param grid the {@link Grid} to be drawn.
   * @param gridLine the {@link GridLine} with the details of how grid lines should be drawn.
   * @param viewPort the {@link MapViewPort} used to convert between co-ordinates.
   */
  void render(Canvas canvas, T grid, GridLine gridLine, MapViewPort viewPort);

}
