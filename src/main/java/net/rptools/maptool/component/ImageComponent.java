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
package net.rptools.maptool.component;

import java.util.Objects;
import java.util.UUID;
import javafx.scene.image.Image;

/** Component that stores image information. */
public class ImageComponent implements Component {

  /** The id of the <code>ImageComponent</code>. */
  private UUID id = UUID.randomUUID();

  /** The image this component represents. */
  private Image image;

  /**
   * Creates a new <code>ImageComponent</code> object.
   *
   * @param img the {@link Image} for the component.
   */
  public ImageComponent(Image img) {
    image = img;
  }

  /**
   * Gets the {@link Image} for the component.
   *
   * @return the {@link Image}.
   */
  public Image getImage() {
    return image;
  }

  /**
   * Sets the {@link Image} for the component.
   *
   * @param image the {@link Image}.
   */
  public void setImage(Image image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ImageComponent that = (ImageComponent) o;
    return Objects.equals(image, that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(image);
  }

  @Override
  public UUID getId() {
    return id;
  }
}
