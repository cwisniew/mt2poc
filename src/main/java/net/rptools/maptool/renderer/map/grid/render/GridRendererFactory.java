package net.rptools.maptool.renderer.map.grid.render;


import java.util.Optional;
import net.rptools.maptool.renderer.map.grid.Grid;

/**
 * Interface implemented by factory classes that return a renderer for a grid.
 */
public interface GridRendererFactory {

  /**
   * Returns the {@link GridRenderer} for a given {@Grid}
   *
   * @param grid The grid to return the renderer for.
   *
   * @return The renderer to render the grid.
   */
  Optional<GridRenderer> rendererFor(Grid grid);

}
