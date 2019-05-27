package net.rptools.maptool.renderer.map.grid;

/**
 * An ordinary square grid for {@link net.rptools.maptool.renderer.map.GameMap}s.
 */
public class SquareGrid implements Grid {

  /** The height and width of the grid. */
  private double dimension;


  /**
   * Creates a new <code>SquareGrid</code>.
   *
   * @param dim the dimensions for the square.
   */
  public SquareGrid(double dim) {
    dimension = dim;
  }

  @Override
  public String getName() {
    return "Square Grid";
  }

  @Override
  public double getWidth() {
    return dimension;
  }

  @Override
  public double getHeight() {
    return dimension;
  }

  @Override
  public void setWidth(double w) {
    dimension = w;
  }

  @Override
  public void setHeight(double h) {
    dimension = h;
  }
}
