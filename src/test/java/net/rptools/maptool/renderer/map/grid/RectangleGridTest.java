package net.rptools.maptool.renderer.map.grid;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RectangleGridTest {

  private static Random random;

  @BeforeAll
  static void setup() {
    random = new Random();
  }

  @Test
  void getName() {
    assertEquals("Rectangle Grid", new  RectangleGrid(1).getName());
  }

  @Test
  void getWidth() {
    for (int i = 0; i < 50; i++) {
      int dim1 = random.nextInt();
      int dim2 = random.nextInt();
      Grid sqGrid = new RectangleGrid(dim1);
      assertEquals(dim1, sqGrid.getWidth());

      Grid rGrid = new RectangleGrid(dim1, dim2);
      assertEquals(dim1, rGrid.getWidth());
    }
  }

  @Test
  void getHeight() {
    for (int i = 0; i < 50; i++) {
      int dim1 = random.nextInt();
      int dim2 = random.nextInt();
      Grid sqGrid = new RectangleGrid(dim1);
      assertEquals(dim1, sqGrid.getHeight());

      Grid rGrid = new RectangleGrid(dim1, dim2);
      assertEquals(dim2, rGrid.getHeight());
    }
  }

  @Test
  void setWidth() {
    Grid grid = new RectangleGrid(1);
    for (int i = 0; i < 50; i++) {
      int dim1 = random.nextInt();
      grid.setWidth(dim1);
      assertEquals(dim1, grid.getWidth());
    }
  }

  @Test
  void setHeight() {
    Grid grid = new RectangleGrid(1);
    for (int i = 0; i < 50; i++) {
      int dim1 = random.nextInt();
      grid.setHeight(dim1);
      assertEquals(dim1, grid.getHeight());
    }
  }

  @Test
  @DisplayName("getGridCenter (int)")
  void getGridCenterInt() {
    Grid grid = new RectangleGrid(1);
    for (int i = 0; i < 5; i++) {
      int width = random.nextInt(200);
      int height = random.nextInt(200);
      grid.setWidth(width);
      grid.setHeight(height);
      for (double x = 0; x < width * 5; x += width) {
        for (double y = 0; y < height * 5; y += height) {
          // point on the top left most boundary and points just inside either boundary should result in same center
          Point2D c0 = grid.getGridCenter(new Point2D(x, y));
          Point2D c1 = grid.getGridCenter(new Point2D(x + 0.1, y + 0.1));
          Point2D c2 = grid.getGridCenter(new Point2D(x + width - 0.1, y + height - 0.1));
          double midX = x + width / 2.0;
          double midY = y + height / 2.0;
          Point2D expected = new Point2D(midX, midY);
          assertEquals(expected, c0, "Point on boundary does not match.");
          assertEquals(expected, c1, "Point just inside left boundary does not match.");
          assertEquals(expected, c2, "Point just inside right boundary does not match.");
        }
      }
    }
  }

  @Test
  @DisplayName("getGridCenter (double)")
  void getGridCenterDouble() {
    Grid grid = new RectangleGrid(1);
    for (int i = 0; i < 5; i++) {
      double width = 5.0 + random.nextDouble();
      double height = 5.0 + random.nextDouble();
      grid.setWidth(width);
      grid.setHeight(height);
      for (double x = 0; x < width * 5; x += width) {
        for (double y = 0; y < height * 5; y += height) {
          // point on the top left most boundary and points just inside either boundary should result in same center
          // have to add tiny amount as FP numbers cant represent every number and the boundary some times presents rounding issues.
          Point2D c0 = grid.getGridCenter(new Point2D(x + 0.000001, y + 0.000001));
          Point2D c1 = grid.getGridCenter(new Point2D(x + 0.1, y + 0.1));
          Point2D c2 = grid.getGridCenter(new Point2D(x + width - 0.1, y + height - 0.1));
          double midX = x + width / 2.0;
          double midY = y + height / 2.0;
          Point2D expected = new Point2D(midX, midY);

          assertEquals(expected.getX(), c0.getX(), 0.001, "X Point on boundary doesn't match");
          assertEquals(expected.getY(), c0.getY(), 0.001, "Y Point on boundary doesn't match");
          assertEquals(expected.getX(), c1.getX(), 0.001, "X Point inside left boundary doesn't match");
          assertEquals(expected.getY(), c1.getY(), 0.001, "Y Point inside left boundary doesn't match");
          assertEquals(expected.getX(), c2.getX(), 0.001, "X Point inside right boundary doesn't match");
          assertEquals(expected.getY(), c2.getY(), 0.001, "Y Point inside right boundary doesn't match");
        }
      }
    }
  }

  @Test
  @DisplayName("getGridBounds (int)")
  void getGridBoundsInt() {
    Grid grid = new RectangleGrid(1);
    for (int i = 0; i < 5; i++) {
      int width = random.nextInt(200);
      int height = random.nextInt(200);
      grid.setWidth(width);
      grid.setHeight(height);
      for (double x = 0; x < width * 5; x += width) {
        for (double y = 0; y < height * 5; y += height) {
          // point on the top left most boundary and points just inside either boundary should result in same center
          Rectangle2D b0 = grid.getGridBounds(new Point2D(x, y));
          Rectangle2D b1 = grid.getGridBounds(new Point2D(x + 0.1, y + 0.1));
          Rectangle2D b2 = grid.getGridBounds(new Point2D(x + width - 0.1, y + height - 0.1));

          // Expected y is y + height as in game map y co-ordinates higher y goes up
          Rectangle2D expected = new Rectangle2D(x, y + height, width, height);
          assertEquals(expected, b0, "Point on boundary does not match.");
          assertEquals(expected, b1, "Point just inside left boundary does not match.");
          assertEquals(expected, b2, "Point just inside right boundary does not match.");
        }
      }
    }
  }

  @Test
  @DisplayName("getGridBounds (double)")
  void getGridBoundsDouble() {
    Grid grid = new RectangleGrid(1);
    for (int i = 0; i < 5; i++) {
      double width = 5.0 + random.nextDouble();
      double height = 5.0 + random.nextDouble();
      grid.setWidth(width);
      grid.setHeight(height);
      for (double x = 0; x < width * 5; x += width) {
        for (double y = 0; y < height * 5; y += height) {
          // point on the top left most boundary and points just inside either boundary should result in same center
          // have to add tiny amount as FP numbers cant represent every number and the boundary some times presents rounding issues.
          Rectangle2D b0 = grid.getGridBounds(new Point2D(x + 0.000001, y + 0.000001));
          Rectangle2D b1 = grid.getGridBounds(new Point2D(x + 0.1, y + 0.1));
          Rectangle2D b2 = grid.getGridBounds(new Point2D(x + width - 0.1, y + height - 0.1));

          // Expected y is y + height as in game map y co-ordinates higher y goes up
          Rectangle2D expected = new Rectangle2D(x, y + height, width, height);
          assertEquals(expected.getMinX(), b0.getMinX(), 0.001, "Min X Point on boundary doesn't match");
          assertEquals(expected.getMaxX(), b0.getMaxX(), 0.001, "Max X Point on boundary doesn't match");
          assertEquals(expected.getMinY(), b0.getMinY(), 0.001, "Min Y Point on boundary doesn't match");
          assertEquals(expected.getMaxY(), b0.getMaxY(), 0.001, "Max Y Point on boundary doesn't match");
          assertEquals(expected.getMinX(), b1.getMinX(), 0.001, "Min X Point inside left boundary doesn't match");
          assertEquals(expected.getMaxX(), b1.getMaxX(), 0.001, "Max X Point inside left boundary doesn't match");
          assertEquals(expected.getMinY(), b1.getMinY(), 0.001, "Min Y Point inside left boundary doesn't match");
          assertEquals(expected.getMaxY(), b1.getMaxY(), 0.001, "Max Y Point inside left boundary doesn't match");
          assertEquals(expected.getMinX(), b2.getMinX(), 0.001, "Min X Point inside right boundary doesn't match");
          assertEquals(expected.getMaxX(), b2.getMaxX(), 0.001, "Max X Point inside right boundary doesn't match");
          assertEquals(expected.getMinY(), b2.getMinY(), 0.001, "Min Y Point inside right boundary doesn't match");
          assertEquals(expected.getMaxY(), b2.getMaxY(), 0.001, "Max Y Point inside right boundary doesn't match");
        }
      }
    }
  }

}