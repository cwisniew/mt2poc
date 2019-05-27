package net.rptools.maptool.renderer.map.view;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Class that implements the {@link MapViewPort} for co-ordinate translation.
 */
public class MapViewPortImpl implements MapViewPort {

  /** The rectangle of the world that we are currently "looking at". */
  private Rectangle2D mapBounds;

  /** The display bounds that the view is mapped to. */
  private Rectangle2D displaySize;

  /** The zoom factor used. */
  private double zoomLevel = 1.0;

  /** The point that the map view is centred on. */
  private Point2D centeredOn;

  /** The co-ordinate for the centre of the screen. */
  private Point2D screenCentre = Point2D.ZERO;

  /**
   * Creates a new <code>MapViewPortImpl</code> object.
   */
  public MapViewPortImpl() {
    centeredOn = new Point2D(0,0);
  }

  @Override
  public void centerOn(Point2D point) {
    Point2D translate = point.subtract(centeredOn);
    centeredOn = point;
    mapBounds = new Rectangle2D(
        mapBounds.getMinX() + translate.getX(),
        mapBounds.getMinY() + translate.getY(),
        mapBounds.getWidth(),
        mapBounds.getHeight()
    );
  }

  @Override
  public void adjustDisplaySize(Rectangle2D size) {
    displaySize = size;
    screenCentre = new Point2D(size.getWidth()/2, size.getHeight()/2);
    recalculateMapBounds();
  }

  @Override
  public void setZoomLevel(double level) {
    zoomLevel = level;
    recalculateMapBounds();
  }


  @Override
  public Rectangle2D getMapBounds() {
    return mapBounds;
  }

  @Override
  public Rectangle2D getDisplaySize() {
    return displaySize;
  }

  @Override
  public double getZoomLevel() {
    return zoomLevel;
  }

  @Override
  public Point2D getCenteredOn() {
    return centeredOn;
  }


  @Override
  public Point2D convertToScreen(Point2D point) {
    return screenCentre.add(point.getX() * zoomLevel, point.getY() * zoomLevel);
  }

  @Override
  public Point2D getCentreScreenTranslate() {
    return screenCentre.subtract(centeredOn);
  }

  private void recalculateMapBounds() {
    final double width = displaySize.getWidth() * zoomLevel;
    final double height = displaySize.getHeight() * zoomLevel;

    final double minX = centeredOn.getX() - width/2;
    final double minY = centeredOn.getY() - height/2;

    mapBounds = new Rectangle2D(minX, minY, width, height);
  }


  @Override
  public Point2D getScreenCentre() {
    return screenCentre;
  }
}
