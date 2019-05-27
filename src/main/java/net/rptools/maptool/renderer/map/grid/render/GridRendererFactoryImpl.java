package net.rptools.maptool.renderer.map.grid.render;

import com.google.inject.Inject;
import java.util.Map;
import java.util.Optional;
import net.rptools.maptool.renderer.map.grid.Grid;

/**
 * The default implementation of the {@link GridRendererFactory} interface used to return a
 * {@link GridRenderer} for a specific {@link Grid}.
 */
public class GridRendererFactoryImpl implements GridRendererFactory {

  @Inject
  private Map<Class, GridRenderer> gridRendererMap;

  @Override
  public Optional<GridRenderer> rendererFor(Grid grid) {
    return Optional.ofNullable(gridRendererMap.get(grid.getClass()));
  }


}
