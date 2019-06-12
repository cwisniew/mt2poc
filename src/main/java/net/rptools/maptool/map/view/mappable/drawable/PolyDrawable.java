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
package net.rptools.maptool.map.view.mappable.drawable;

import javafx.scene.Node;

/** Interface for classes that can draw drawable polygons to the map view */
public interface PolyDrawable {
  /**
   * Called when the map wants to notify the <code>PollyDrawable</code> tht there has been a change.
   */
  void update();

  /**
   * Return the {@link Node} used for rendering the drawable on the view of the map.
   *
   * @return the {@link Node} used for rendering the drawable.
   */
  Node getNode();

  /**
   * Returns the {@link Node} used for rendering where the drawable is being dragged from on the
   * view of the map..
   *
   * @return the {@link Node} used for rendering where the drawable is being dragged from.
   */
  Node getDraggedNode();
}
