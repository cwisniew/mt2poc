package net.rptools.maptool.renderer.map.view;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import net.rptools.maptool.renderer.map.GameMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MapViewPortImplTest {
  private static Random random;

  @BeforeAll
  static void setup() {
    random = new Random();
  }


  @Test
  void centerOn() {
    MapViewPort viewPort = new MapViewPortImpl();

    for (int i = 0; i < 100; i++) {
      double x = random.nextDouble();
      double y = random.nextDouble();

      Point2D p = new Point2D(x,y);

      viewPort.centerOn(p);

      assertEquals(p, viewPort.getViewCenteredOn());
    }

  }

  @Test
  void setZoomLevel() {
    MapViewPort viewPort = new MapViewPortImpl();

    for (int i = 0; i < 100; i++) {
      double zoom = random.nextDouble();
      viewPort.setZoomLevel(zoom);
      assertEquals(zoom, viewPort.getZoomLevel());
    }
  }

  @Test
  void addZoomLevel() {
    MapViewPort viewPort = new MapViewPortImpl();
    viewPort.adjustDisplaySize(new Rectangle2D(0, 0, 1000, 800));

    for (int i = 0; i < 100; i++) {
      double zoom = random.nextDouble();
      double oldZoom = viewPort.getZoomLevel();
      viewPort.addZoomLevel(zoom);
      double newZoom = viewPort.getZoomLevel();
      assertEquals(oldZoom +  zoom, newZoom, "Positive Zoom");
    }

    for (int i = 0; i < 10; i++) {
      double zoom = -random.nextDouble();
      double oldZoom = viewPort.getZoomLevel();
      viewPort.addZoomLevel(zoom);
      double newZoom = viewPort.getZoomLevel();
      assertEquals(oldZoom +  zoom, newZoom, "Negative Zoom");
    }


  }

  @Test
  @DisplayName("Get and Adjust Display size")
  void adjustDisplaySize() {
    MapViewPort viewPort = new MapViewPortImpl();

    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble() * 100;
      double height = random.nextDouble() * 100;
      Rectangle2D size = new Rectangle2D(0, 0, width, height);
      viewPort.adjustDisplaySize(size);
      assertEquals(size, viewPort.getDisplaySize());
    }

  }

  @Test
  void zoomInOut() {
    MapViewPort viewPort = new MapViewPortImpl();
    viewPort.adjustDisplaySize(new Rectangle2D(0, 0, 1000, 800));

    for (int i = 0; i < 10; i++) {
      double zoom = random.nextDouble();
      viewPort.setZoomStep(zoom);
      for (int j = 0 ; j < 5; j++) {
        double oldZoom = viewPort.getZoomLevel();
        viewPort.zoomOut();
        assertEquals(oldZoom / zoom, viewPort.getZoomLevel());
      }
    }


    for (int i = 0; i < 10; i++) {
      double zoom = random.nextDouble();
      viewPort.setZoomStep(zoom);
      for (int j = 0 ; j < 5; j++) {
        double oldZoom = viewPort.getZoomLevel();
        viewPort.zoomIn();
        assertEquals(oldZoom * zoom, viewPort.getZoomLevel());
      }
    }
  }


  @Test
  void panViewLeft() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort.setPanViewStep(panStep);
      viewPort.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      Rectangle2D current = viewPort.getViewBounds();
      Rectangle2D expected = new Rectangle2D(current.getMinX() - panStep, current.getMinY(), width, height);

      viewPort.panViewLeft();

      Rectangle2D newBounds = viewPort.getViewBounds();

      assertEquals(expected.getMinX(), newBounds.getMinX(), 0.0001, "Min X");
      assertEquals(expected.getMinY(), newBounds.getMinY(), 0.0001, "Min Y");
      assertEquals(expected.getWidth(), newBounds.getWidth(), 0.0001, "Width");
      assertEquals(expected.getHeight(), newBounds.getHeight(), 0.0001, "Height");


      assertEquals(expected.getMinX() + expected.getWidth()/2.0, viewPort.getViewCenteredOn().getX(), 0.001, "Centered X");
      assertEquals(expected.getMinY() + expected.getHeight()/2.0, viewPort.getViewCenteredOn().getY(), 0.001, "Centered Y");

    }
  }

  @Test
  void panViewRight() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort.setPanViewStep(panStep);
      viewPort.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      Rectangle2D current = viewPort.getViewBounds();
      Rectangle2D expected = new Rectangle2D(current.getMinX() + panStep, current.getMinY(), width, height);
      viewPort.panViewRight();

      Rectangle2D newBounds = viewPort.getViewBounds();

      assertEquals(expected.getMinX(), newBounds.getMinX(), 0.0001, "Min X");
      assertEquals(expected.getMinY(), newBounds.getMinY(), 0.0001, "Min Y");
      assertEquals(expected.getWidth(), newBounds.getWidth(), 0.0001, "Width");
      assertEquals(expected.getHeight(), newBounds.getHeight(), 0.0001, "Height");


      assertEquals(expected.getMinX() + expected.getWidth()/2.0, viewPort.getViewCenteredOn().getX(), 0.001, "Centered X");
      assertEquals(expected.getMinY() + expected.getHeight()/2.0, viewPort.getViewCenteredOn().getY(), 0.001, "Centered Y");
    }
  }

  @Test
  void panViewUp() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort.setPanViewStep(panStep);
      viewPort.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      Rectangle2D current = viewPort.getViewBounds();
      Rectangle2D expected = new Rectangle2D(current.getMinX(), current.getMinY() + panStep, width, height);
      viewPort.panViewUp();

      Rectangle2D newBounds = viewPort.getViewBounds();

      assertEquals(expected.getMinX(), newBounds.getMinX(), 0.0001, "Min X");
      assertEquals(expected.getMinY(), newBounds.getMinY(), 0.0001, "Min Y");
      assertEquals(expected.getWidth(), newBounds.getWidth(), 0.0001, "Width");
      assertEquals(expected.getHeight(), newBounds.getHeight(), 0.0001, "Height");



      assertEquals(expected.getMinX() + expected.getWidth()/2.0, viewPort.getViewCenteredOn().getX(), 0.001, "Centered X");
      assertEquals(expected.getMinY() + expected.getHeight()/2.0, viewPort.getViewCenteredOn().getY(), 0.001, "Centered Y");
    }
  }

  @Test
  void panViewDown() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort.setPanViewStep(panStep);
      viewPort.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      Rectangle2D current = viewPort.getViewBounds();
      Rectangle2D expected = new Rectangle2D(current.getMinX(), current.getMinY() - panStep, width, height);
      viewPort.panViewDown();

      Rectangle2D newBounds = viewPort.getViewBounds();

      assertEquals(expected.getMinX(), newBounds.getMinX(), 0.0001, "Min X");
      assertEquals(expected.getMinY(), newBounds.getMinY(), 0.0001, "Min Y");
      assertEquals(expected.getWidth(), newBounds.getWidth(), 0.0001, "Width");
      assertEquals(expected.getHeight(), newBounds.getHeight(), 0.0001, "Height");

      assertEquals(expected.getMinX() + expected.getWidth()/2.0, viewPort.getViewCenteredOn().getX(), 0.001, "Centered X");
      assertEquals(expected.getMinY() + expected.getHeight()/2.0, viewPort.getViewCenteredOn().getY(), 0.001, "Centered Y");
    }
  }

  @Test
  void panViewLeftUp() {
    MapViewPort viewPort1 = new MapViewPortImpl();
    MapViewPort viewPort2 = new MapViewPortImpl();
    MapViewPort viewPort3 = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort1.setPanViewStep(panStep);
      viewPort2.setPanViewStep(panStep);
      viewPort3.setPanViewStep(panStep);

      viewPort1.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort2.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort3.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));

      viewPort1.panViewLeftUp();

      viewPort2.panViewLeft();
      viewPort2.panViewUp();

      viewPort3.panViewUp();
      viewPort3.panViewLeft();

      assertEquals(viewPort2.getViewBounds(), viewPort1.getViewBounds());
      assertEquals(viewPort3.getViewBounds(), viewPort1.getViewBounds());
    }
  }

  @Test
  void panViewLeftDown() {
    MapViewPort viewPort1 = new MapViewPortImpl();
    MapViewPort viewPort2 = new MapViewPortImpl();
    MapViewPort viewPort3 = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort1.setPanViewStep(panStep);
      viewPort2.setPanViewStep(panStep);
      viewPort3.setPanViewStep(panStep);

      viewPort1.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort2.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort3.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));

      viewPort1.panViewLeftDown();

      viewPort2.panViewLeft();
      viewPort2.panViewDown();

      viewPort3.panViewDown();
      viewPort3.panViewLeft();

      assertEquals(viewPort2.getViewBounds(), viewPort1.getViewBounds());
      assertEquals(viewPort3.getViewBounds(), viewPort1.getViewBounds());

    }
  }

  @Test
  void panViewRightUp() {
    MapViewPort viewPort1 = new MapViewPortImpl();
    MapViewPort viewPort2 = new MapViewPortImpl();
    MapViewPort viewPort3 = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort1.setPanViewStep(panStep);
      viewPort2.setPanViewStep(panStep);
      viewPort3.setPanViewStep(panStep);

      viewPort1.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort2.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort3.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));

      viewPort1.panViewRightUp();

      viewPort2.panViewRight();
      viewPort2.panViewUp();

      viewPort3.panViewUp();
      viewPort3.panViewRight();

      assertEquals(viewPort2.getViewBounds(), viewPort1.getViewBounds());
      assertEquals(viewPort3.getViewBounds(), viewPort1.getViewBounds());
    }
  }

  @Test
  void panViewRightDown() {
    MapViewPort viewPort1 = new MapViewPortImpl();
    MapViewPort viewPort2 = new MapViewPortImpl();
    MapViewPort viewPort3 = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble();
      double height = random.nextDouble();

      double panStep = random.nextDouble();

      viewPort1.setPanViewStep(panStep);
      viewPort2.setPanViewStep(panStep);
      viewPort3.setPanViewStep(panStep);

      viewPort1.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort2.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
      viewPort3.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));

      viewPort1.panViewRightDown();

      viewPort2.panViewRight();
      viewPort2.panViewDown();

      viewPort3.panViewDown();
      viewPort3.panViewRight();

      assertEquals(viewPort2.getViewBounds(), viewPort1.getViewBounds());
    }
  }

  @Test
  void getViewBounds() {
    MapViewPort viewPort = new MapViewPortImpl();

    for (int i = 0; i < 10; i++) {
      double width = random.nextDouble() * 100;
      double height = random.nextDouble() * 100;
      Rectangle2D size = new Rectangle2D(0, 0, width, height);
      Rectangle2D expected = new Rectangle2D(-width/2, -height/2, width, height);
      viewPort.adjustDisplaySize(size);


      assertEquals(expected, viewPort.getViewBounds());
    }
  }

  @Test
  void convertCoordinates() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 100; i++) {
      double zoom = random.nextDouble();
      double panX = random.nextDouble();
      double panY = random.nextDouble();
      double x = random.nextDouble();
      double y = random.nextDouble();

      viewPort.setZoomStep(zoom);
      viewPort.panView(panX, panY);

      Point2D disp = new Point2D(x, y);
      Point2D map = viewPort.convertDisplayToMap(disp);
      Point2D conv = viewPort.convertMapToDisplay(map);
      assertEquals(disp.getX(), conv.getX(), 0.0001, "X Co-Ordinate");
      assertEquals(disp.getY(), conv.getY(), 0.0001, "Y Co-Ordinate");
    }
  }

  @Test
  void panView() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 10; i++) {
      for (int mult = -1; mult <= 1; mult += 2) {
        double width = random.nextDouble();
        double height = random.nextDouble();

        double panX = random.nextDouble() * mult;
        double panY = random.nextDouble() * mult;

        viewPort.adjustDisplaySize(new Rectangle2D(0.0, 0.0, width, height));
        Rectangle2D current = viewPort.getViewBounds();
        Rectangle2D expected =
            new Rectangle2D(current.getMinX() + panX, current.getMinY() + panY, width, height);

        viewPort.panView(new Point2D(panX, panY));

        Rectangle2D newBounds = viewPort.getViewBounds();

        assertEquals(expected.getMinX(), newBounds.getMinX(), 0.0001, "Min X");
        assertEquals(expected.getMinY(), newBounds.getMinY(), 0.0001, "Min Y");
        assertEquals(expected.getWidth(), newBounds.getWidth(), 0.0001, "Width");
        assertEquals(expected.getHeight(), newBounds.getHeight(), 0.0001, "Height");

        assertEquals(
            expected.getMinX() + expected.getWidth() / 2.0,
            viewPort.getViewCenteredOn().getX(),
            0.001,
            "Centered X");
        assertEquals(
            expected.getMinY() + expected.getHeight() / 2.0,
            viewPort.getViewCenteredOn().getY(),
            0.001,
            "Centered Y");
      }
    }
  }

  @Test
  void scaleVector() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 100; i++) {
      double zoom = random.nextDouble();
      double x = random.nextDouble();
      double y = random.nextDouble();

      viewPort.setZoomLevel(zoom);

      Point2D scaled = viewPort.scaleVector(new Point2D(x,y));

      assertEquals(x * zoom, scaled.getX(), 0.0001, "X Co-ordinate");
      assertEquals(y * zoom, scaled.getY(), 0.0001, "Y Co-ordinate");
    }
  }

  @Test
  @DisplayName("Set/Get Game Mao")
  void setGameMap() {
    MapViewPort viewPort = new MapViewPortImpl();
    GameMap gameMap1 = mock(GameMap.class);
    GameMap gameMap2 = mock(GameMap.class);

    viewPort.setGameMap(gameMap1);

    assertEquals(gameMap1, viewPort.getGameMap());


    viewPort.setGameMap(gameMap2);
    assertEquals(gameMap2, viewPort.getGameMap());

  }

  @Test
  @DisplayName("Get/Set zoom step")
  void testZoomStep() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 100; i++) {
      double zoom = random.nextDouble();
      viewPort.setZoomStep(zoom);
      assertEquals(zoom, viewPort.getZoomStep());
    }
  }

  @Test
  @DisplayName("Get/Set pam viewstep")
  void testPanViewStep() {
    MapViewPort viewPort = new MapViewPortImpl();
    for (int i = 0; i < 100; i++) {
      double zoom = random.nextDouble();
      viewPort.setPanViewStep(zoom);
      assertEquals(zoom, viewPort.getPanViewStep());
    }
  }

  @Test
  void getCorner() {
    MapViewPort viewPort = new MapViewPortImpl();

    for (int i = 0; i < 10; i++) {
      double minX = random.nextDouble() * 100;
      double minY = random.nextDouble() * 100;
      double width = random.nextDouble() * 100;
      double height = random.nextDouble() * 100;
      Rectangle2D size = new Rectangle2D(minX, minY, width, height);
      viewPort.adjustDisplaySize(size);

      Rectangle2D bounds = viewPort.getViewBounds();

      assertEquals(new Point2D(bounds.getMinX(), bounds.getMaxY()), viewPort.getCorner(MapViewCorner.TOP_LEFT), "Top Left");
      assertEquals(new Point2D(bounds.getMaxX(), bounds.getMaxY()), viewPort.getCorner(MapViewCorner.TOP_RIGHT), "Top Right");
      assertEquals(new Point2D(bounds.getMaxX(), bounds.getMinY()), viewPort.getCorner(MapViewCorner.BOTTOM_RIGHT), "Bottom Right");
      assertEquals(new Point2D(bounds.getMinX(), bounds.getMinY()), viewPort.getCorner(MapViewCorner.BOTTOM_LEFT), "Bottom Left");
    }

  }

  @Test
  void getGridCenter() {
    MapViewPort viewPort = new MapViewPortImpl();

    GameMap gameMap = mock(GameMap.class);

    Point2D point1 = new Point2D(1.0, 1.0);
    Point2D point1C = new Point2D(10.0, 10.0);
    when(gameMap.getGridCenter(point1)).thenReturn(point1C);

    Point2D point2 = new Point2D(11.0, 11.0);
    Point2D point2C = new Point2D(15.0, 15.0);
    when(gameMap.getGridCenter(point2)).thenReturn(point2C);

    viewPort.setGameMap(gameMap);

    assertEquals(point1C, viewPort.getGridCenter(point1));
    assertEquals(point2C, viewPort.getGridCenter(point2));
  }

  @Test
  void getCornerGridCenter() {
    MapViewPort viewPort = new MapViewPortImpl();

    double minX = 0.0;
    double maxX = 100.0;
    double minY = 0.0;
    double maxY = 100.0;

    double cMinX = 5.0;
    double cMaxX = 95.0;
    double cMinY = 5.0;
    double cMaxY = 95.0;


    GameMap gameMap = mock(GameMap.class);

    Point2D topLeftC = new Point2D(cMinX, cMinY);
    Point2D topRightC = new Point2D(cMaxX, cMinY);
    Point2D bottomRightC = new Point2D(cMaxX, cMaxY);
    Point2D bottomLeftC = new Point2D(cMinX, cMaxY);


    double width = maxX - minX + 1;
    double height = maxX - minX + 1;

    when(gameMap.getGridCenter(new Point2D(-width/2.0, height/2.0))).thenReturn(topLeftC);
    when(gameMap.getGridCenter(new Point2D(width/2.0, height/2.0))).thenReturn(topRightC);
    when(gameMap.getGridCenter(new Point2D(width/2.0, -height/2.0))).thenReturn(bottomRightC);
    when(gameMap.getGridCenter(new Point2D(-width/2.0, -height/2.0))).thenReturn(bottomLeftC);

    viewPort.setGameMap(gameMap);
    viewPort.adjustDisplaySize(new Rectangle2D(minX, minY, width, height));

    assertEquals(topLeftC, viewPort.getCornerGridCenter(MapViewCorner.TOP_LEFT));
    assertEquals(topRightC, viewPort.getCornerGridCenter(MapViewCorner.TOP_RIGHT));
    assertEquals(bottomRightC, viewPort.getCornerGridCenter(MapViewCorner.BOTTOM_RIGHT));
    assertEquals(bottomLeftC, viewPort.getCornerGridCenter(MapViewCorner.BOTTOM_LEFT));
  }
}