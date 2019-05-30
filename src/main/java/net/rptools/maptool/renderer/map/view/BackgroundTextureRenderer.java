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
package net.rptools.maptool.renderer.map.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import net.rptools.maptool.renderer.map.grid.Grid;
import net.rptools.maptool.renderer.map.grid.RectangleGrid;

/** This class renders a repeating background texture to a {@link Canvas}. */
class BackgroundTextureRenderer {

  /**
   * Render the repeating background texture.
   *
   * @param canvas the {@link Canvas} to render to.
   * @param backgroundTexture the {@link Image} of the background texture to render.
   * @param viewPort the {@link MapViewPort} used to map between co-ordinates.
   */
  public void render(Canvas canvas, Image backgroundTexture, MapViewPort viewPort) {
    /*
     * To keep texture rendering from wandering a texture is laid out as a grid of the size of the
     * texture with the top left corner of one of the grid cells anchored at 0, 0
     */
    Grid textureGrid =
        new RectangleGrid(backgroundTexture.getWidth(), backgroundTexture.getHeight());

    /*
     * Grab the top left and bottom right of the view point in map co-ordinates this will give
     * the minimum and maximum X and Y om the maps that are visible.
     */
    final Point2D topLeft = viewPort.getCornerGridCenter(MapViewCorner.TOP_LEFT);
    final Point2D bottomRight = viewPort.getCornerGridCenter(MapViewCorner.BOTTOM_RIGHT);

    // Get the top and bottom grid cells center for the texture grid
    final Point2D txTopLeftC = textureGrid.getGridCenter(topLeft);
    final Point2D txBottomRightC = textureGrid.getGridCenter(bottomRight);

    // Convert from map co-ordinates to display co-ordinates.
    final Point2D topLeftDisplay = viewPort.convertMapToDisplay(txTopLeftC);
    final Point2D bottomRightDisplay = viewPort.convertMapToDisplay(txBottomRightC);

    // scale the dimensions of the texture to match display co-ordinates.
    final Point2D txDimension =
        viewPort.scaleVector(
            new Point2D(backgroundTexture.getWidth(), backgroundTexture.getHeight()));
    final double txWidth = txDimension.getX();
    final double txHeight = txDimension.getY();

    final double minX = topLeftDisplay.getX() - txWidth;
    final double maxX = bottomRightDisplay.getX() + txWidth;
    final double minY = topLeftDisplay.getY() - txHeight;
    final double maxY = bottomRightDisplay.getY() + txHeight;

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.save();

    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    for (double x = minX; x <= maxX; x += txWidth) {
      for (double y = minY; y <= maxY; y += txHeight) {
        gc.drawImage(backgroundTexture, x, y, txWidth, txHeight);
      }
    }

    gc.restore();
  }
}
