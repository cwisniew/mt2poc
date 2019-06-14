package net.rptools.maptool.map.geom;


import java.util.ArrayList;
import javafx.geometry.Point2D;

public class MRectangle {

  private final double x1;
  private final double y1;
  private final double x2;
  private final double y2;

  private final double width;
  private final double height;

  private final double centerX;
  private final double centerY;

  private final double minX;
  private final double minY;

  private final double maxX;
  private final double maxY;


  private MRectangle(double rx1, double ry1, double rx2, double ry2) {
    x1 = rx1;
    y1 = ry1;

    x2 = rx2;
    y2 = ry2;

    if (x1 < x2) {
      minX = x1;
      maxX = x2;
    } else {
      minX = x2;
      maxX = x1;
    }

    if (y1 < y2) {
      minY = y1;
      maxY = y2;
    } else {
      minY = y2;
      maxY = y1;
    }

    width = maxX - minX;
    height = maxY - minY;

    centerX = minX + width / 2.0;
    centerY = minY + height / 2.0;
  }

  public static MRectangle createRectangle(double x1, double y1, double x2, double y2) {
    return new MRectangle(x1, y1, x2, y2);
  }

  public static MRectangle createWHRectangle(double minx, double miny, double width, double height) {
    return createRectangle(minx, miny, minx + width, miny + height);
  }

  public static MRectangle createRectangleCenteredAt(double centerX, double centerY, double width, double height) {
    final double halfWidth = width / 2.0;
    final double halfHeight = height / 2.0;

    return new MRectangle(centerX - halfWidth, centerY + halfHeight, centerX + halfWidth, centerY - halfHeight);
  }

  public static MRectangle createRectangle(Point2D p1, Point2D p2) {
    return  createRectangle(p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }

  public double getX1() {
    return x1;
  }

  public double getY1() {
    return y1;
  }

  public double getX2() {
    return x2;
  }

  public double getY2() {
    return y2;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }

  public double getCenterX() {
    return centerX;
  }

  public double getCenterY() {
    return centerY;
  }

  public double getMinX() {
    return minX;
  }

  public double getMinY() {
    return minY;
  }

  public double getMaxX() {
    return maxX;
  }

  public double getMaxY() {
    return maxY;
  }

  /**
   * Returns a {@link MPolygon} object for a rectangle given two x,y co-ordinates. This rectangle is
   * always wound clockwise.
   *
   * @return a {@link MPolygon} object for the specified rectangle.
   */
  public MPolygon asPolygon() {
    var points = new ArrayList<Point2D>(4);

    // This is in the co-ordinate system where y gets larger in the up direction

    // Top left
    points.add(new Point2D(getMinX(), getMaxY()));

    // Top right
    points.add(new Point2D(getMaxX(), getMaxY()));

    // Bottom right
    points.add(new Point2D(getMaxX(), getMinY()));

    // Bottom left
    points.add(new Point2D(getMinX(), getMinY()));

    return new MPolygon(points);
  }
}
