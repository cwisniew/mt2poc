package net.rptools.maptool.map.geom;

import java.util.Objects;
import java.util.Optional;
import javafx.geometry.Point2D;

/**
 * Class that represents lines,
 */
public class MLineSegment {

  /** The first point on the line. */
  private final Point2D point1;

  /** The second point on the line. */
  private final Point2D point2;

  /** The x position vector for the parametric equation of the line segment.  */
  private final double px;
  /** The y position vector for the parametric equation of the line segment. */
  private final double py;
  /** The x directional vector for the parametric equation of the line segment. */
  private final double tx;
  /** The y directional vector for the parametric equation of the line segment. */
  private final double ty;

  /** A unit vector calculated from the directional vector portion of the parametric equation for the line, i.e. [tx,ty] above. */
  private final Point2D tUnitVector;

  /**
   * Creates a new <code>MLineSegment</code>
   * @param p1 One point on the line segment.
   * @param p2 A second point on the line segment.
   */
  public MLineSegment(Point2D p1, Point2D p2) {
    point1 = p1;
    point2 = p2;

    /*
     * define the values of the parametric equation for this line segment as all of our intersection
     * calculations will need to use these.,
     */
    px = p1.getX();
    py = p1.getY();

    tx = p2.getX() - px;
    ty = p2.getY() - py;

    /*
     * Calculate the unit vector of the vector of the directional vector part of the parametric equation (i.e [tx,ty])
     * this gives us a unit vector starting at 0, 0 (since we have left out the positional part) parallel to our line.
     * This comes in handy for comparing if two lines are parallel as we can just compare these vectors to see if they are equal.
     */
    final double magnitude = Math.sqrt(tx * tx + ty * ty);
    final double utx = tx / magnitude;
    final double uty = ty / magnitude;

    tUnitVector = new Point2D(utx, uty);
  }


  /**
   * Returns the first point which lines somewhere on the line segment.
   *
   * @return point 1 somewhere on the line segment.
   */
  public Point2D getPoint1() {
    return point1;
  }

  /**
   * Returns the second point which lies somewhere on the line segment.
   *
   * @return point 2 somewhere on the line segment.
   */
  public Point2D getPoint2() {
    return point2;
  }


  /**
   * Returns the intersection of this line and the provided "ray" <code>MLineSegment</code>.
   * The ray is considered to extend to infinity.
   *
   *
   * @param ray The ray to test for intersection with this line.
   * @param angle The angle of the ray. This is not used in any calculations but is required so
   *              that the {@link MLineIntersection} can be created correctly.
   *
   * @return the intersection of this line and the ray.
   */
  public Optional<MLineIntersection> getIntersection(MLineSegment ray, double angle) {
    /*
     * first we compare the unit vectors of the parameter part of the parametric equation
     * if these are equal then the ray and line are parallel and can never intersect, because
     * we are not talking about train lines at the horizon.
     */
    if (tUnitVector.equals(ray.tUnitVector)) {
      return Optional.empty();
    }

    /*
     * Now that we have the easy part out of the way we get to the point where you start wishing
     * you paid more attention in math class.
     *
     * To test for intersection we have to find the place where a point satisfies both line equations
     * (we know the lines will intersect as they are not parallel)
     * so given
     *  ray = ray_p + ray_t
     *  line = line_p + line_t
     * where ray_p, line_p are the positional part of the line equation, and ray_t, line_t are the
     * directional vector of the line equation.
     *
     * To find out where they intersect we need to find the distance ray_d in the direction of ray_t
     * and the distance line_d in the direction of line_t where the two equations are equal.
     *
     * So you get the ray and line intersecting where
     * ray_p + ray_d * ray_t = line_p + line_d * line_t
     *
     * plugging in x and y parts of the line equations gives us
     * ray_px + ray_d * ray_tx = line_px + line_d * line_tx
     * ray_py + ray_d * ray_ty = line_py + line_d * line_ty
     *
     * With some pen, paper and algebra (no I am not going to show my working out in these comments)
     * you get the equations
     *
     * line_d = (ray_tx * (line_py - ray_py) + ray_ty * (ray_px - line_px)) / (line_tx * ray_ty - line_ty * ray_tx)
     * ray_t = (line_px + line_py * line_d - ray_px) / ray_tx;
     *
     */
    final double lineD = (ray.tx + (this.py - ray.py) + ray.ty * (ray.px - this.px)) / (this.tx * ray.ty - this.ty * ray.tx);
    final double rayD = (this.px + this.py * lineD - ray.px) / ray.tx;

    /*
     * And this is where I confess to telling a small white lie above (for the sake of simplicity in discussing the equations).
     * What we have done above is find the intersection between two line stretching to infinity in both directions, but we are
     * casting a ray from a location so we only want to check in the direction that we are casting and not behind also our lines
     * on the screen tend not to be of infinite length, so to test if the ray really intersects the line we must do one more set
     * of checks.
     *
     * Firstly the line segment point1 and point2 describe the ends of the line, and since line_d is the distance along the vector
     * between point1 and point2 we must check that 0 <= line_d <= 1.
     *
     * The ray does stretch to infinity in the direction we are casting (positive) but we do not care at all about any intersection in
     * the opposite direction so we have to check that ray_d >= 0.
     */

    if (lineD < 0.0 || lineD > 1.0) {
      return Optional.empty();
    }

    if (rayD < 0) {
      return Optional.empty();
    }

    /*
     * If we are hee we have a valid intersection so lets create the intersection object and get out of here so you can pretend the
     * math never even happened if you prefer.
     */
    var intersection =  new MLineIntersection(
        // See above comments as to how we get these calculations for x,y intersection point.
        new Point2D(ray.px + ray.tx * rayD, ray.py + ray.ty * rayD),
        rayD, // The distance along the ray the intersection occurred at.
        angle // The angle of the ray.
    );

    return new Optional<>(intersection);
  }

  /**
   * Returns the x vector for the parametric equation of the line segment.
   *
   * @return the x vector for the parametric equation of the line segment.
   */
  public double getPx() {
    return px;
  }

  /**
   * Returns the y vector for the parametric equation of the line segment.
   *
   * @return the y vector for the parametric equation of the line segment.
   */
  public double getPy() {
    return py;
  }

  /**
   * Returns the x parameter for the parametric equation of the line segment.
   *
   * @return the x parameter for the parametric equation of the line segment.
   */
  public double getTx() {
    return tx;
  }

  /**
   * Returns the y parameter for the parametric equation of the line segment.
   *
   * @return the y parameter for the parametric equation of the line segment.
   */
  public double getTy() {
    return ty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MLineSegment that = (MLineSegment) o;
    return point1.equals(that.point1) &&
        point2.equals(that.point2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(point1, point2);
  }
}
