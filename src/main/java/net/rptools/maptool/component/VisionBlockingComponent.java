package net.rptools.maptool.component;

import java.util.UUID;
import net.rptools.maptool.map.geom.MPolygon;

/**
 * Component for holding data about vision blocking.
 */
public class VisionBlockingComponent implements Component {

  /** The id of the component. */
  private UUID id = UUID.randomUUID();

  /** The polygon that will block vision. */
  private MPolygon polygon;

  /**
   * Creates a new <code>VisionBlockingComponent</code>.
   *
   * @param poly the polygon used to block vision.
   */
  public VisionBlockingComponent(MPolygon poly) {
    polygon = poly;
  }

  @Override
  public UUID getId() {
    return id;
  }

  /**
   * Returns the polygon that blocks vision.
   *
   * @return the polygon that blocks vision.
   */
  public MPolygon getPolygon() {
    return  polygon;
  }
}
