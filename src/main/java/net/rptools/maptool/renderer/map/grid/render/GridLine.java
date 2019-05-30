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

import javafx.scene.paint.Color;

/** This class contains the information used to render the lines for the grid. */
public class GridLine {
  /** The {@link Color} to used for drawing the grid line. */
  private Color lineColor;

  /** dashes used for drawing the grid line. */
  private double[] lineDashes;

  /** Creates a new <code>GridLine</code> object with default values. */
  public GridLine() {
    lineColor = Color.BLACK;
    lineDashes = new double[1];
    lineDashes[0] = 0;
  }

  /**
   * Creates a new <code>GridLine</code> object.
   *
   * @param color The {@link Color} to draw the grid lines with.
   * @param dashes The dash pattern to use while drawing the grid lines.
   */
  public GridLine(Color color, double... dashes) {
    lineColor = color;
    lineDashes = new double[dashes.length];
    System.arraycopy(lineDashes, 0, dashes, 0, dashes.length);
  }

  /**
   * Returns the color used for drawing the grid line.
   *
   * @return the color used for drawing the grid line.
   */
  public Color getLineColor() {
    return lineColor;
  }

  /**
   * Sets the color used for drawing the grid line.
   *
   * @param lineColor the color used to draw the grid line.
   */
  public void setLineColor(Color lineColor) {
    this.lineColor = lineColor;
  }

  /**
   * Returns the dash pattern to use for drawing the grid line.
   *
   * @return the dash pattern to use while drawing the grid line.
   */
  public double[] getLineDashes() {
    return lineDashes;
  }

  /**
   * Sets the dash pattern to use for drawing the grid line.
   *
   * @param dashes the dash pattern to use for drawing the grid line
   */
  public void setLineDashes(double[] dashes) {
    lineDashes = new double[dashes.length];
    System.arraycopy(lineDashes, 0, dashes, 0, dashes.length);
  }
}
