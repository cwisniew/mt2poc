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