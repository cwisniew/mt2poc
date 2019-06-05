/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * MapTool Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.maptool.map.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import net.rptools.maptool.map.GameMap;

/** Class that implements the {@link MapViewPort} for co-ordinate translation. */
public class MapViewPortImpl implements MapViewPort {

  /** The default step size to use for the zooming functions that dont take arguments. */
  private static final double DEFAULT_ZOOM_STEP = 1.01;

  /** The default step size to use for the panning functions that dont take arguments. */
  private static final double DEFAULT_PAN_STEP = 5.0;

  /** The rectangle of the world that we are currently "looking at". */
  private Rectangle2D mapBounds = new Rectangle2D(0, 0, 1, 1);

  /** Pre-calculated minimum x co-ordinate of the view in map co-ordinates. */
  private double minViewX;

  /** Pre-calculated maximum x co-ordinate of the view in map co-ordinates. */
  private double maxViewX;

  /** Pre-calculated minimum y co-ordinate of the view in map co-ordinates. */
  private double minViewY;

  /** Pre-calculated maximum y co-ordinate of the view in map co-ordinates. */
  private double maxViewY;

  /** Pre-calculated width of the view in map co-ordinates. */
  private double viewWidth;

  /** Pre-calculated height of the view in map co-ordinates. */
  private double viewHeight;

  /** Pre-calculated half of the width of the view in map co-ordinates. */
  private double halfViewWidth;

  /** Pre-calculated half of the height of the view in map co-ordinates. */
  private double halfViewHeight;

  /** The display bounds that the view is mapped to. */
  private Rectangle2D displaySize = new Rectangle2D(0, 0, 1, 1);

  /** The display with. */
  private double displayWidth;

  /** The display height. */
  private double displayHeight;

  /** pre-calculated half of the display width. */
  private double halfDisplayWidth;

  /** pre-calculated half of the display height. */
  private double halfDisplayHeight;

  /** The zoom factor used. */
  private double zoomLevel = 1.0;

  /** The point that the map view is centred on. */
  private Point2D centeredOn;

  /** The x offset of the view into the map. */
  private double mapOffsetX;

  /** The y offset of the view into the map. */
  private double mapOffsetY;

  /** The x co-ordinate for the centre of the screen. */
  private double screenCenterX = 0.0;

  /** The y co-ordinate for the centre of the screen. */
  private double screenCenterY = 0.0;

  /** The map that this view port is a view into. */
  private GameMap gameMap;

  /** The value to use for the zoom methods that take no arguments. */
  double zoomStepValue = DEFAULT_ZOOM_STEP;

  /** The value to use for the panView methods that take no arguments. */
  double panViewStepValue = DEFAULT_PAN_STEP;

  /** Creates a new <code>MapViewPortImpl</code> object. */
  public MapViewPortImpl() {
    centeredOn = new Point2D(0, 0);
  }

  @Override
  public void centerOn(Point2D point) {
    Point2D translate = point.subtract(centeredOn);
    centeredOn = point;
    mapBounds =
        new Rectangle2D(
            mapBounds.getMinX() + translate.getX(),
            mapBounds.getMinY() + translate.getY(),
            mapBounds.getWidth(),
            mapBounds.getHeight());
    recalculateBounds();
  }

  @Override
  public void adjustDisplaySize(Rectangle2D size) {
    displaySize = size;

    // Only perform if both dimensions have been set.
    if (size.getWidth() != 0 && size.getHeight() != 0) {
      screenCenterX = size.getWidth() / 2.0;
      screenCenterY = size.getHeight() / 2.0;

      recalculateBounds();
    }
  }

  @Override
  public void setZoomLevel(double level) {
    zoomLevel = level;
    recalculateBounds();
  }

  @Override
  public void addZoomLevel(double delta) {
    setZoomLevel(zoomLevel + delta);
  }

  @Override
  public void zoomIn() {
    setZoomLevel(zoomLevel * zoomStepValue);
  }

  @Override
  public void zoomOut() {
    setZoomLevel(zoomLevel / zoomStepValue);
  }

  @Override
  public void panViewLeft() {
    panView(-panViewStepValue, 0.0);
  }

  @Override
  public void panViewRight() {
    panView(panViewStepValue, 0.0);
  }

  @Override
  public void panViewUp() {
    panView(0.0, panViewStepValue);
  }

  @Override
  public void panViewDown() {
    panView(0.0, -panViewStepValue);
  }

  @Override
  public void panViewLeftUp() {
    panView(-panViewStepValue, panViewStepValue);
  }

  @Override
  public void panViewLeftDown() {
    panView(-panViewStepValue, -panViewStepValue);
  }

  @Override
  public void panViewRightUp() {
    panView(panViewStepValue, panViewStepValue);
  }

  @Override
  public void panViewRightDown() {
    panView(panViewStepValue, -panViewStepValue);
  }

  @Override
  public Rectangle2D getViewBounds() {
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
  public Point2D getViewCenteredOn() {
    return centeredOn;
  }

  @Override
  public Point2D convertMapToDisplay(Point2D point) {
    return convertMapToDisplay(point.getX(), point.getY());
  }

  @Override
  public Point2D convertDisplayToMap(Point2D point) {
    return convertDisplayToMap(point.getX(), point.getY());
  }

  /**
   * Recalculates the bound and other often used values for conversion when the display changes size
   * or view port changes size/location/zoom level.
   */
  private void recalculateBounds() {
    // First update the pre calculated display values.
    displayWidth = displaySize.getWidth();
    displayHeight = displaySize.getHeight();

    halfDisplayWidth = displayWidth / 2.0;
    halfDisplayHeight = displayHeight / 2.0;

    mapOffsetX = centeredOn.getX();
    mapOffsetY = centeredOn.getY();

    Point2D topLeft = convertDisplayToMap(0.0, 0.0);
    Point2D bottomRight = convertDisplayToMap(displayWidth, displayHeight);

    minViewX = topLeft.getX();
    maxViewX = bottomRight.getX();
    minViewY = bottomRight.getY();
    maxViewY = topLeft.getY();

    viewWidth = maxViewX - minViewX;
    viewHeight = maxViewY - minViewY;
    halfViewWidth = viewWidth / 2.0;
    halfViewHeight = viewHeight / 2.0;

    mapBounds = new Rectangle2D(minViewX, minViewY, viewWidth, viewHeight);
  }

  @Override
  public Point2D convertDisplayToMap(double displayX, double displayY) {
    double mapX = (displayX - halfDisplayWidth) / zoomLevel + mapOffsetX;
    double mapY = -(displayY - halfDisplayHeight) / zoomLevel + mapOffsetY;
    return new Point2D(mapX, mapY);
  }

  @Override
  public Point2D convertMapToDisplay(double mapX, double mapY) {
    double displayX = halfDisplayWidth + zoomLevel * (mapX - mapOffsetX);
    double displayY = halfDisplayHeight - zoomLevel * (mapY - mapOffsetY);
    return new Point2D(displayX, displayY);
  }

  @Override
  public void panView(Point2D vect) {
    panView(vect.getX(), vect.getY());
  }

  @Override
  public void panView(double deltaX, double deltaY) {
    centeredOn = centeredOn.add(deltaX, deltaY);
    recalculateBounds();
  }

  @Override
  public Point2D scaleVector(Point2D vector) {
    return vector.multiply(zoomLevel);
  }

  @Override
  public void setGameMap(GameMap gameMap) {
    this.gameMap = gameMap;
  }

  @Override
  public GameMap getGameMap() {
    return gameMap;
  }

  @Override
  public Point2D getCorner(MapViewCorner corner) {
    Point2D cornerPoint;
    switch (corner) {
      case TOP_LEFT:
        cornerPoint = new Point2D(mapBounds.getMinX(), mapBounds.getMaxY());
        break;
      case TOP_RIGHT:
        cornerPoint = new Point2D(mapBounds.getMaxX(), mapBounds.getMaxY());
        break;
      case BOTTOM_RIGHT:
        cornerPoint = new Point2D(mapBounds.getMaxX(), mapBounds.getMinY());
        break;
      case BOTTOM_LEFT:
        cornerPoint = new Point2D(mapBounds.getMinX(), mapBounds.getMinY());
        break;
      default:
        cornerPoint = null; // Shouldn't happen
    }

    return cornerPoint;
  }

  @Override
  public Point2D getCornerGridCenter(MapViewCorner corner) {
    return gameMap.getGridCenter(getCorner(corner));
  }

  @Override
  public Point2D getGridCenter(Point2D mapPoint) {
    return gameMap.getGridCenter(mapPoint);
  }

  @Override
  public Point2D getGridCenter(double x, double y) {
    return gameMap.getGridCenter(x, y);
  }

  @Override
  public Point2D convertDisplayToMapGridCenter(double displayX, double displayY) {
    return getGridCenter(convertDisplayToMap(displayX, displayY));
  }

  @Override
  public Point2D getDisplayGridCenter(double displayX, double displayY) {
    return convertMapToDisplay(convertDisplayToMapGridCenter(displayX, displayY));
  }


  @Override
  public void setZoomStep(double value) {
    zoomStepValue = value;
  }

  @Override
  public void setPanViewStep(double value) {
    panViewStepValue = value;
  }

  @Override
  public double getZoomStep() {
    return zoomStepValue;
  }

  @Override
  public double getPanViewStep() {
    return panViewStepValue;
  }

  @Override
  public Rectangle2D convertCenteredMapRectangleToDisplay(
      double x, double y, double width, double height) {
    double halfWidth = width / 2.0;
    double halfHeight = height / 2.0;
    Point2D topLeft = convertMapToDisplay(new Point2D(x - halfWidth, y + halfHeight));
    Point2D bottomRight = convertMapToDisplay(new Point2D(x + halfWidth, y - halfHeight));

    return new Rectangle2D(
        topLeft.getX(),
        topLeft.getY(),
        bottomRight.getX() - topLeft.getX(),
        bottomRight.getY() - topLeft.getY());
  }

  @Override
  public List<Point2D> convertMapToDisplay(List<Point2D> points) {
    var convPoints = new ArrayList<Point2D>(points.size());
    for (var p : points) {
      convPoints.add(convertMapToDisplay(p));
    }

    return convPoints;
  }
}
