package net.rptools.maptool.renderer.map.grid.render;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import net.rptools.maptool.renderer.map.grid.Grid;

/**
 * Interface implemented by classes that can render a grid.
 */
public interface GridRenderer<T extends Grid> {

  void render(Canvas canvas, T grid, Color color, double scale, Point2D translation);

}
