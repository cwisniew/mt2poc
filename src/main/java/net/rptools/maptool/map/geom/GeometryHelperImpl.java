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
package net.rptools.maptool.map.geom;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Polygon;

public class GeometryHelperImpl implements GeometryHelper {

  @Override
  public Rectangle2D getRectangle2D(double x1, double y1, double x2, double y2) {
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
