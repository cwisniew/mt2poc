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

    /*
     * We add +1 and then take Ceiling below as its always better to draw one too many grid cells
     * in each direction and have them clipped than it is to stop short of the edge of the map.
     */
    double width = canvas.getWidth();
    int numXLines = (int) (width / dimension) + 1;

    double height = canvas.getHeight();
    int numYLines = (int) (height / dimension) + 1;

    double endX = (Math.ceil(numXLines / 2.0)) * dimension;
    double startX = -endX;

    double endY = (Math.ceil(numYLines / 2.0)) * dimension;
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
