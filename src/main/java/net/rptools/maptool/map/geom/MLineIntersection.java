package net.rptools.maptool.map.geom;


import javafx.geometry.Point2D;

/**
 * Class used to represent the intersection of a ran and a {@link MLineSegment}.
 */
public class MLineIntersection {

  /** The point at which the intersection occurs. */
  private final Point2D point;

  /** the distance along the ray that the intersection occurs. */
  private final double distance;

  /** The angle of the ray. */
  private final double angle;

  /**
   * Creates a new <code>MLineInterSection</code> object.
   *
   * @param iPoint The point at which the intersection occurs.
   * @param iDistance The distance along the ray that the intersection occurs.
   * @param iAngle The angle of the ray.
   */
  public MLineIntersection(Point2D iPoint, double iDistance, double iAngle) {
    point = iPoint;
    distance = iDistance;
    angle = iAngle;
  }

  /**
   * Returns the point at which the intersection occurs.
   *
   * @return the point at which the intersection occurs.
   */
  public Point2D getPoint() {
    return point;
  }

  /**
   * Returns the distance along the ray that the intersection occurs.
   *
   * @return the distance along the ray tha the intersection occurs.
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Returns the angle of the ray.
   *
   * @return the angle of the ray.
   */
  public double getAngle() {
    return angle;
  }
}
