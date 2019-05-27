package net.rptools.maptool.renderer.map.grid;

/**
 * Interface implemented by all grids for {@link net.rptools.maptool.renderer.map.GameMap}s.
 */
public interface Grid {

  /**
   * Returns the name of the grid.
   *
   * @return the name of the grid.
   */
  String getName();

  /**
   * Returns the width at the widest point of a grid cell.
   * @return
   */
  double getWidth();

  /**
   * Returns the height at the tallest point of the grid cell.
   * @return
   */
  double getHeight();


  /**
   * Sets the width of the widest point of the grid cell.
   *
   * @param w the width of the widest point.
   */
  void setWidth(double w);


  /**
   * Sets the height of the tallest point of the grid cell.
   *
   * @param h The height of the tallest point.
   */
  void setHeight(double h);
}
