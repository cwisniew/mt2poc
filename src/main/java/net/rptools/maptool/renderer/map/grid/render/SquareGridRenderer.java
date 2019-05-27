package net.rptools.maptool.renderer.map.grid.render;


import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.rptools.maptool.renderer.map.grid.SquareGrid;

public class SquareGridRenderer implements GridRenderer<SquareGrid> {

  @Override
  public void render(Canvas canvas, SquareGrid grid, Color color, double scale, Point2D translation) {

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0 , canvas.getWidth(), canvas.getHeight());

    gc.save();

    gc.translate(translation.getX(), translation.getY());
    gc.scale(scale, scale);
    gc.setStroke(color);

    gc.setFill(color.BLUE);
    gc.fillOval(-3, -3, 6, 6);



    double dimension = grid.getWidth();

    double width = canvas.getWidth();
    int numXLines = (int) Math.ceil(width / dimension) + 1;

    double height = canvas.getHeight();
    int numYLines = (int) Math.ceil(height / dimension) + 1;

    double endX = numXLines / 2 * dimension;
    double startX = -endX;

    double endY = numYLines / 2 * dimension;
    double startY = -endY;

    for (double x = startX; x <= endX; x+= dimension) {
      gc.strokeLine(x, startY, x, endY);
    }

    for (double y = startY; y <= endY; y+= dimension) {
      gc.strokeLine(startX, y, endX, y);
    }

    gc.restore();

  }
}
