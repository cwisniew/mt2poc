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

/** Utility class for geometry based convenience methods. */
public interface GeometryHelper {

  /**
   * Returns a {@link Rectangle2D} given two x,y co-ordinates.
   *
   * @param x1 The first x co-ordinate.
   * @param y1 The first y co-ordinate.
   * @param x2 The second x co-ordinate.
   * @param y2 The second y co-ordinate.
   * @return a {@link Rectangle2D} for the given points.
   */
  Rectangle2D getRectangle2D(double x1, double y1, double x2, double y2);
}
