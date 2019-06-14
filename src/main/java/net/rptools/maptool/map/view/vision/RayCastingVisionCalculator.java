package net.rptools.maptool.map.view.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.geometry.Point2D;
import net.rptools.maptool.component.MapFigureComponent;
import net.rptools.maptool.component.ViewerComponent;
import net.rptools.maptool.component.VisionBlockingComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.geom.MLineIntersection;
import net.rptools.maptool.map.geom.MLineSegment;
import net.rptools.maptool.map.geom.MPolygon;

/**
 * Class that performs ray casting to determine visibility on the map.
 */
public class RayCastingVisionCalculator implements VisionCalculator {

  /** Small delta angle used for a ray either side of vertex so that vision/light will extend past the vertex. */
  private static final double VERY_SMALL_ANGLE = 0.00001;

  /** The viewers on the map. */
  private Set<Entity> viewers = new HashSet<>();

  /** The vision blockers on the map. */
  private Set<Entity> blockers = new HashSet<>();

  /** The visible {@link Entity}s on the map. */
  private Set<Entity> visibleEntities = new HashSet<>();

  /** The visible areas for each entity that is a viewer, Note this does not depend on lighting. */
  private Map<Entity, VisibleArea> visibleAreas = new HashMap<>();

  /** The total visible area for all viewer entities.  Note this does not depend on lighting. */
  private VisibleArea totalVisibleArea = VisibleArea.NO_VISIBLE_AREA;




  @Override
  public void addEntity(Entity entity) {
    addEntities(Set.of(entity));
  }

  @Override
  public void removeEntity(Entity entity) {
    removeEntities(Set.of(entity));
  }

  @Override
  public void addEntities(Collection<Entity> entities) {

    for (Entity e : entities) {
      if (e.hasComponent(ViewerComponent.class)) {
        viewers.add(e);
      }

      if (e.hasComponent(VisionBlockingComponent.class)) {
        blockers.add(e);
      }
    }
  }

  @Override
  public void removeEntities(Collection<Entity> entities) {

      for (Entity e : entities) {
        if (e.hasComponent(ViewerComponent.class)) {
          viewers.remove(e);
        }

        if (e.hasComponent(VisionBlockingComponent.class)) {
          blockers.remove(e);
        }
      }
  }

  @Override
  public VisibleArea getTotalVisibleArea() {
    return totalVisibleArea;
  }

  @Override
  public VisibleArea getVisibleArea(Entity entity) {
    if (visibleAreas.containsKey(entity)) {
      return visibleAreas.get(entity);
    } else {
      return VisibleArea.NO_VISIBLE_AREA;
    }
  }

  @Override
  public Set<Entity> getVisibleEntities() {
    return Collections.unmodifiableSet(visibleEntities);
  }


  @Override
  public void calculate() {
    var blockingPolys = new VisionBlockingPolygonList(blockers);
    Set<MLineSegment> rays = new HashSet<>();


    // Allocate an array large enough for all the angles to the vertices plus a small delta to each side.
    final int numberOfAngles = blockingPolys.getNumberOfVertices() * 3;
    double[] angles = new double[numberOfAngles];


    // Loop through all our viewers
    for (Entity viewer : viewers) {

      var component = viewer.getComponent(MapFigureComponent.class);
      if (component.isPresent()) {
        var mapFig = component.get();
        var viewerPoint = new Point2D(mapFig.getX(), mapFig.getY());
        rays.clear();

        // Loop through all of the vertices and determine the angle from our viewer.
        int ind = 0;
        for (Point2D vert : blockingPolys.getVertices()) {
          double angle = Math.atan2(vert.getY() - viewerPoint.getY(), vert.getX() - viewerPoint.getX());
          angles[ind++] = angle - VERY_SMALL_ANGLE;
          angles[ind++] = angle;
          angles[ind++] = angle + VERY_SMALL_ANGLE;
        }

        // Get a list of the closest intersection along a ray for each of the angles we derived above.
        var lineIntersections = new ArrayList<MLineIntersection>(numberOfAngles);
        for (double angle : angles) {
          var direction = new Point2D(Math.cos(angle), Math.sin(angle));

          // Create a new ray from the vertex to the viewer.
          MLineSegment ray = new MLineSegment(viewerPoint, new Point2D(viewerPoint.getX() + direction.getX(), viewerPoint.getY() + direction.getY()));
          rays.add(ray);


          MLineIntersection closest = null;
          for (var lineSegment : blockingPolys.getLineSegments()) {
            var interOpt = lineSegment.getIntersection(ray, angle);
            if (interOpt.isPresent()) {
              var inter = interOpt.get();
              if (closest == null || closest.getDistance() > inter.getDistance()) {
                closest = inter;
              }
            }
          }

          if (closest != null) {
            lineIntersections.add(closest);
          }
        }

        // Sort our intersections by angle, this is so we can easily turn them into triangles.
        Collections.sort(lineIntersections, Comparator.comparingDouble(MLineIntersection::getAngle));

        // Create the triangles
        var polyList = new ArrayList<MPolygon>();
        for (int i = 0 ; i < lineIntersections.size() - 1; i++) {
          var p1 = lineIntersections.get(i).getPoint();
          var p2 = lineIntersections.get(i+1).getPoint();
          polyList.add(MPolygon.createTriangle(viewerPoint, p1, p2));
        }
        // Close off the polygon
        var p1 = lineIntersections.get(lineIntersections.size() -1).getPoint();
        var p2 = lineIntersections.get(0).getPoint();
        polyList.add(MPolygon.createTriangle(viewerPoint, p1, p2));

        visibleAreas.put(viewer, new VisibleArea(polyList, rays, blockingPolys.getVertices()));
      }
    }

    totalVisibleArea = VisibleArea.mergeVisibleAreas(visibleAreas.values());

  }

}
