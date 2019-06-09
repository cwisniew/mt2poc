package net.rptools.maptool.map.geom;

import javafx.geometry.Rectangle2D;

public class GeometryHelperImpl implements  GeometryHelper {

  @Override
  public Rectangle2D getRectangle2D(double x1, double y1, double x2, double y2) {
    double minX;
    double minY;
    double rectWidth;
    double rectHeight;

    if (x1 < x2) {
      minX = x1;
      rectWidth = x2 - minX;
    } else {
      minX = x2;
      rectWidth = x1 - minX;
    }

    if (y1 < y2) {
      minY = y1;
      rectHeight = y2 - minY;
    } else {
      minY = y2;
      rectHeight = y1 - minY;
    }

    return new Rectangle2D(minX, minY, rectWidth, rectHeight);
  }

}
