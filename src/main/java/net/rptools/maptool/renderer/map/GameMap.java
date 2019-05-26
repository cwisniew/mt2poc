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
package net.rptools.maptool.renderer.map;

import java.util.Optional;
import java.util.UUID;
import javafx.scene.image.Image;

/** Interface implemented by classes that represent maps used in the game. */
public interface GameMap {

  /** The id of the <code>GameMap</code>. */
  UUID getId();

  /**
   * Sets the {@link Image} texture to use as the background of the <code>GameMap</code>.
   *
   * @param texture The texture to use as the background.
   */
  void setTexturedBackground(Image texture);

  /**
   * Returns the {@Image} to be used as the background texture.
   *
   * @return the background texture.
   */
  Optional<Image> getBackgroundTexture();
}
