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

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.save();

    final Point2D translation = viewPort.getCentreScreenTranslate();
    final double scale = viewPort.getZoomLevel();

    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    gc.translate(translation.getX(), translation.getY());
    gc.scale(scale, scale);

    double textureWidth = backgroundTexture.getWidth();
    double textureHeight = backgroundTexture.getHeight();

    double width = viewPort.getViewBounds().getWidth();
    double height = viewPort.getViewBounds().getHeight();

    double centerX = viewPort.getViewCenteredOn().getX();
    double centerY = viewPort.getViewCenteredOn().getY();

    double scaledTextureWidth = textureWidth * scale;
    double scaledTextureHeight = textureHeight * scale;

    // coerce centerX, centerY to a texture multiple
    centerX = Math.floor(centerX / scaledTextureWidth) * scaledTextureWidth;
    centerY = Math.floor(centerY / scaledTextureHeight) * scaledTextureHeight;

    for (double x = 0; x <= width; x += scaledTextureWidth) {
      for (double y = 0; y <= height; y += scaledTextureHeight) {
        gc.drawImage(backgroundTexture, centerX + x, centerY + y);
        gc.drawImage(backgroundTexture, centerX + x, centerY - y);
        gc.drawImage(backgroundTexture, centerX - x, centerY - y);
        gc.drawImage(backgroundTexture, centerX - x, centerY + y);
      }
    }

    gc.setFill(Color.RED);
    gc.fillOval(-5, -5, 10, 10);

    gc.restore();
  }
}
