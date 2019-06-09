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
package net.rptools.maptool.map.view.tool;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import net.rptools.maptool.map.view.MapView;

public class RectangleTool extends MapViewTool {

  /** The X co-ordinate of location that the mouse button was pressed, */
  private double mousePressedX;

  /** The Y co-ordinate of location that the mouse button was pressed, */
  private double mousePressedY;

  /**
   * Creates a new <code>RectangleTool</code>.
   *
   * @param view the {@link MapView}.
   */
  @Inject
  public RectangleTool(
      @Assisted MapView view,
      @Assisted("backgroundCanvas") Canvas backgroundCanvas,
      @Assisted("foregroundCanvas") Canvas foregroundCanvas) {
    super(view, backgroundCanvas, foregroundCanvas);
  }

  @Override
  public void mousePressed(MouseEvent event) {
    if (event.isPrimaryButtonDown()) {
      mousePressedX = event.getX();
      mousePressedY = event.getY();
    }
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    Canvas canvas = getForegroundCanvas();
    double width = canvas.getWidth();
    double height = canvas.getHeight();

    GraphicsContext gc = canvas.getGraphicsContext2D();

    gc.clearRect(0, 0, width, height);

    gc.save();

    Color rectColor = Color.PURPLE;
    gc.setStroke(rectColor);
    gc.setLineWidth(5.0);

    gc.setFill(new Color(rectColor.getRed(), rectColor.getGreen(), rectColor.getBlue(), 0.4));

    Rectangle2D rect = getRectangle2D(event.getX(), event.getY(), mousePressedX, mousePressedY);
    gc.fillRect(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());

    gc.restore();

  }

  @Override
  public void mouseReleased(MouseEvent event) {
    Canvas canvas = getForegroundCanvas();
    double width = canvas.getWidth();
    double height = canvas.getHeight();

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, width, height);

    Rectangle2D rect = getRectangle2D(event.getX(), event.getY(), mousePressedX, mousePressedY);

  }

  private Rectangle2D getRectangle2D(double x1, double y1, double x2, double y2) {
    double minX;
    double minY;
    double rectWidth;
    double rectHeight;

    if (x1 < x2) {
      minX = x1;
      rectWidth = x2 - minX;
    } else {
      minX = x2;
      rectWidth = x1 - minX;
    }

    if (y1 < y2) {
      minY = y1;
      rectHeight = y2 - minY;
    } else {
      minY = y2;
      rectHeight = y1 - minY;
    }

    return new Rectangle2D(minX, minY, rectWidth, rectHeight);
  }
}
