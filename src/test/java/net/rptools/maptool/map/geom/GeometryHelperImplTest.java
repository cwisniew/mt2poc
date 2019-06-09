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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GeometryHelperImplTest {

  private static Random random;

  @BeforeAll
  static void setup() {
    random = new Random();
  }

  @Test
  void getRectangle2D() {

    GeometryHelper geometryHelper = new GeometryHelperImpl();

    for (int i = 0; i < 1000; i++) {
      double x1 = random.nextDouble();
      double x2 = random.nextDouble();
      double y1 = random.nextDouble();
      double y2 = random.nextDouble();

      double minX = Math.min(x1, x2);
      double maxX = Math.max(x1, x2);
      double minY = Math.min(y1, y2);
      double maxY = Math.max(y1, y2);

      double width = maxX - minX;
      double height = maxY - minY;

      Rectangle2D rect = geometryHelper.getRectangle2D(x1, y1, x2, y2);

      assertEquals(minX, rect.getMinX(), 0.0001, "rectangle minX");
      assertEquals(minY, rect.getMinY(), 0.0001, "rectangle minY");
      assertEquals(maxX, rect.getMaxX(), 0.0001, "rectangle maxX");
      assertEquals(maxY, rect.getMaxY(), 0.0001, "rectangle maxY");
      assertEquals(width, rect.getWidth(), 0.0001, "rectangle width");
      assertEquals(height, rect.getHeight(), 0.0001, "rectangle height");
    }
  }
}
