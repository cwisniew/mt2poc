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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

class ImageComponentTest {

  @Test
  void getImage() {
    Image img = mock(Image.class);
    ImageComponent imageComponent = new ImageComponent(img);

    assertEquals(img, imageComponent.getImage());
  }

  @Test
  void setImage() {
    Image img1 = mock(Image.class);
    ImageComponent imageComponent = new ImageComponent(img1);

    Image img2 = mock(Image.class);
    imageComponent.setImage(img2);
    assertEquals(img2, imageComponent.getImage());
    assertNotEquals(img1, imageComponent.getImage());
  }

  @Test
  void equals1() {
    Image img1 = mock(Image.class);
    ImageComponent ic1 = new ImageComponent(img1);

    Image img2 = mock(Image.class);
    ImageComponent ic2 = new ImageComponent(img2);

    ImageComponent ic3 = new ImageComponent(img1);

    assertTrue(ic1.equals(ic1));
    assertTrue(ic1.equals(ic3));
    assertFalse(ic1.equals(ic2));
  }

  @Test
  void hashCode1() {
    Image img1 = mock(Image.class);
    ImageComponent ic1 = new ImageComponent(img1);

    Image img2 = mock(Image.class);
    ImageComponent ic2 = new ImageComponent(img2);

    ImageComponent ic3 = new ImageComponent(img1);

    assertEquals(ic1.hashCode(), ic1.hashCode());
    assertEquals(ic1.hashCode(), ic3.hashCode());
    assertNotEquals(ic1.hashCode(), ic2.hashCode());
  }
}
