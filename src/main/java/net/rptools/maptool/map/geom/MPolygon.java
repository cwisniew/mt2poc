package net.rptools.maptool.map.geom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.geometry.Point2D;

/**
 * Class that represents polygons on the {@link net.rptools.maptool.map.GameMap}.
 */
public class MPolygon {
  /** A list of the vertices that make up the polygon. */
  private final List<Point2D> vertices;

  /** Vertices as a list of {@link Double}s. */
  private final List<Double> verticesDoubleList;

  /** The line segments that make up this polygon. */
  private final Set<MLineSegment> lineSegments;


  /**
   * Creates a polygon that represents the triangle denotes by three points.
   * @param p1 The first point of the triangle.
   * @param p2 The second point of the triangle.
   * @param p3 The third point of the triangle.
   *
   * @return The polygon representing the triangle.
   */
  public static MPolygon createTriangle(Point2D p1, Point2D p2, Point2D p3) {
    return new MPolygon(List.of(p1, p2, p3));
  }


  /**
   * Creates a new <code>MPolygon</code> for the given vertices.
   *
   * @param vert The vertices for the polygon.
   */
  public MPolygon(List<Point2D> vert) {
    vertices = Collections.unmodifiableList(List.copyOf(vert));

    List<Double> vlist = new ArrayList<>(vertices.size()*2);

    for (var vertex : vertices) {
      vlist.add(vertex.getX());
      vlist.add(vertex.getY());
    }

    verticesDoubleList = Collections.unmodifiableList(vlist);

    // Create the line segments that trace out the polygon
    var lseg = new HashSet<MLineSegment>();
    Point2D prevPoint = null;
    for (Point2D p : vertices) {
      if (prevPoint != null) {
        lseg.add(new MLineSegment(prevPoint, p));
      }
      prevPoint = p;
    }

    // Close the polygon.
    lseg.add(new MLineSegment(prevPoint,vertices.get(0)));

    lineSegments =  Collections.unmodifiableSet(lseg);
  }


  /**
   * Creates a new <code>MPolygon</code> for the given vertices.
   *
   * @param vert The vertices for the polygon.
   */
  public MPolygon(Point2D[] vert) {
    this(List.of(vert));
  }


  /**
   * Returns the number of vertices for the polygon.
   *
   * @return the number of vertices for the polygon,.
   */
  public int getNumberofVertices() {
    return vertices.size();
  }

  /**
   * Returns a list of the vertices for this polygon.
   *
   * @return a list of the vertices for this polygon.
   */
  public List<Point2D> getVertices() {
    return vertices;
  }


  /**
   * Returns a list of {@link Double}s representing the vertices for the polygon with x,y co-ordinates interleaved
   *
   * @return the list of vertices.
   */
  public List<Double> getVerticesDoubleList() {
    return verticesDoubleList;
  }

  /**
   * Returns the {@link MLineSegment}s that make up this polygon.
   *
   * @return the line segments that make up this polygon.
   */
  public Set<MLineSegment> getLineSegments() {
    return lineSegments;
  }

}
