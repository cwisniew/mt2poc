package net.rptools.maptool.renderer.map.grid.render;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GridLineTest {

  @Test
  void getLineColor() {
    GridLine gridLine = new GridLine(Color.RED, 1.0, 1.0);
    assertEquals(gridLine.getLineColor(), Color.RED);

    gridLine = new GridLine(Color.BLUEVIOLET, 1.0, 1.0);
    assertEquals(gridLine.getLineColor(), Color.BLUEVIOLET);

    gridLine = new GridLine(Color.ALICEBLUE, 1.0, 1.0);
    assertEquals(gridLine.getLineColor(), Color.ALICEBLUE);
  }

  @Test
  void setLineColor() {
    GridLine gridLine = new GridLine(Color.RED, 1.0, 1.0);

    gridLine.setLineColor(Color.GAINSBORO);
    assertEquals(gridLine.getLineColor(), Color.GAINSBORO);

    gridLine.setLineColor(Color.AQUA);
    assertEquals(gridLine.getLineColor(), Color.AQUA);

    gridLine.setLineColor(Color.AZURE);
    assertEquals(gridLine.getLineColor(), Color.AZURE);
  }

  @Test
  void getLineDashes() {
    double dash1[] = { 1.0, 1.0 };
    GridLine gridLine = new GridLine(Color.RED, dash1);
    assertEquals(dash1.length, gridLine.getLineDashes().length);
    assertTrue(Arrays.equals(dash1, gridLine.getLineDashes()));
  }

  @Test
  void setLineDashes() {
    GridLine gridLine = new GridLine(Color.RED, 0.0);

    double dash1[] = { 1.0, 1.0 };
    gridLine.setLineDashes(dash1);
    assertEquals(dash1.length, gridLine.getLineDashes().length);
    assertTrue(Arrays.equals(dash1, gridLine.getLineDashes()));

    double dash2[] = { 3.0, 5.0 };
    gridLine.setLineDashes(dash2);
    assertEquals(dash2.length, gridLine.getLineDashes().length);
    assertTrue(Arrays.equals(dash2, gridLine.getLineDashes()));

    double dash3[] = { 2.1, 4.3, 12.0 };
    gridLine.setLineDashes(dash3);
    assertEquals(dash3.length, gridLine.getLineDashes().length);
    assertTrue(Arrays.equals(dash3, gridLine.getLineDashes()));


    // Test that a defensive copy was made of array
    double dash4[] = { 2.1, 4.3, 12.0, 14.7 };
    gridLine.setLineDashes(dash4);
    double replaced = dash4[2];
    dash4[2] = 22.2;

    double[] result = gridLine.getLineDashes();
    assertEquals(dash4.length, result.length);
    assertFalse(Arrays.equals(dash4, result));

    assertEquals(dash4[0], result[0]);
    assertEquals(dash4[1], result[1]);
    assertEquals(replaced, result[2]);
    assertEquals(dash4[3], result[3]);

  }

  @Test
  @DisplayName("Default Constructor")
  void testDefaultConstructor() {
    GridLine gridLine1 = new GridLine();
    assertNotNull(gridLine1.getLineColor());
    assertNotNull(gridLine1.getLineDashes());

    GridLine gridLine2 = new GridLine();
    assertEquals(gridLine1, gridLine2);
  }


  @Test
  @DisplayName("Equals and Hash")
  void testEqualsHash() {
    GridLine gridLine1 = new GridLine();
    assertNotNull(gridLine1.getLineColor());
    assertNotNull(gridLine1.getLineDashes());

    GridLine gridLine2 = new GridLine();
    assertEquals(gridLine1, gridLine2);
    assertEquals(gridLine1.hashCode(), gridLine2.hashCode());

    GridLine gridLine3 = new GridLine(Color.BLUE, 0);
    assertNotEquals(gridLine1, gridLine3);
    assertNotEquals(gridLine1.hashCode(), gridLine3.hashCode());

    assertFalse(gridLine1.equals(null));
    assertFalse(gridLine1.equals(new Object()));
    assertTrue(gridLine1.equals(gridLine1));
  }
}