package net.rptools.maptool.renderer.map.grid.render;


import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.rptools.maptool.renderer.map.grid.SquareGrid;
import net.rptools.maptool.renderer.map.view.MapViewPort;

/**
 * Class used for rendering a {@link SquareGrid}.
 */
public class SquareGridRenderer implements GridRenderer<SquareGrid> {

  @Override
  public void render(Canvas canvas, SquareGrid grid, GridLine gridLine, MapViewPort viewPort) {

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0 , canvas.getWidth(), canvas.getHeight());

    gc.save();

    final Point2D translation = viewPort.getCentreScreenTranslate();
    final double scale = viewPort.getZoomLevel();

    gc.translate(translation.getX(), translation.getY());
    gc.scale(scale, scale);

    gc.setFill(Color.BLUE);
    gc.fillOval(-3, -3, 6, 6);


    gc.setStroke(gridLine.getLineColor());
    gc.setLineDashes(gridLine.getLineDashes());


    double dimension = grid.getWidth();


    double minX = viewPort.getMapBounds().getMinX();
    double maxX = viewPort.getMapBounds().getMaxX();
    double minY = viewPort.getMapBounds().getMinY();
    double maxY = viewPort.getMapBounds().getMaxY();
    double width = viewPort.getMapBounds().getWidth();
    double height = viewPort.getMapBounds().getHeight();

    double scaledGridWidth = dimension * scale;

    System.out.print("\n" + minX + " (" + scaledGridWidth + ") => " + maxX + "  :::   ");
    double centerX = viewPort.getCenteredOn().getX();
    double centerY = viewPort.getCenteredOn().getY();

    // Snap centerX, centerY to a grid lines.
    centerX = Math.floor(centerX/scaledGridWidth) * scaledGridWidth;
    centerY = Math.floor(centerY/scaledGridWidth) * scaledGridWidth;

    for (double x = 0; x <= width; x+= scaledGridWidth) {
      gc.strokeLine(centerX + x, minY, centerX + x, maxY);
      gc.strokeLine(centerX - x, minY, centerX - x, maxY);
    }

    for (double y = 0; y <= height; y+= scaledGridWidth) {
      gc.strokeLine(minX, centerY + y, maxX,  centerY + y);
      gc.strokeLine(minX, centerY - y, maxX, centerY - y);
    }

    gc.restore();

  }
}
