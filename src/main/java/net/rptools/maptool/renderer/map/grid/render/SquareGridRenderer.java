/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * MapTool Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.maptool.renderer.map.grid.render;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import net.rptools.maptool.renderer.map.grid.RectangleGrid;
import net.rptools.maptool.renderer.map.view.MapViewCorner;
import net.rptools.maptool.renderer.map.view.MapViewPort;

/** Class used for rendering a {@link RectangleGrid}. */
public class SquareGridRenderer implements GridRenderer<RectangleGrid> {

  @Override
  public void render(Canvas canvas, RectangleGrid grid, GridLine gridLine, MapViewPort viewPort) {

    /*
     * Grab the top left and bottom right of the view point in map co-ordinates this will give
     * the minimum and maximum X and Y om the maps that are visible.
     */
    final Point2D topLeft = viewPort.getCornerGridCenter(MapViewCorner.TOP_LEFT);
    final Point2D bottomRight = viewPort.getCornerGridCenter(MapViewCorner.BOTTOM_RIGHT);

    // Convert from map co-ordinates to display co-ordinates.
    final Point2D topLeftDisplay = viewPort.convertMapToDisplay(topLeft);
    final Point2D bottomRightDisplay = viewPort.convertMapToDisplay(bottomRight);

    // scale the dimensions of the grid to match display co-ordinates.
    final Point2D scaledDimension =
        viewPort.scaleVector(new Point2D(grid.getWidth(), grid.getHeight()));
    final double width = scaledDimension.getX();
    final double height = scaledDimension.getY();

    /*
     * Add/Subtract half the (scaled) width or height to the center point, its better to got past the
     * clip boundary and have the grid lines clipped than it is to stop short.
     */
    final double minX = topLeftDisplay.getX() - width / 2.0;
    final double maxX = bottomRightDisplay.getX() + width / 2.0;
    final double minY = topLeftDisplay.getY() - height / 2.0;
    final double maxY = bottomRightDisplay.getY() + height / 2.0;

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    gc.save();

    gc.setStroke(gridLine.getLineColor());
    gc.setLineDashes(gridLine.getLineDashes());

    for (double x = minX; x <= maxX; x += width) {
      gc.strokeLine(x, minY, x, maxY);
    }

    for (double y = minY; y <= maxY; y += height) {
      gc.strokeLine(minX, y, maxX, y);
    }

    gc.restore();
  }
}
