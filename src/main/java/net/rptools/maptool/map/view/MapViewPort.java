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

import java.util.List;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.geom.DPolygon;
import net.rptools.maptool.map.geom.MPolygon;
import net.rptools.maptool.map.geom.MRectangle;

/**
 * Interface for classes that perform translations between screen and map co-ordinates.
 *
 * <p>Map co-ordinates are like the graphing you did in math at school 0, 0 as the center of the map
 * X is positive to the right of the axis, negative to the left. Y is positive above the axis,
 * negative below.
 *
 * <p>Display co-ordinates match a computer display (but could be an image to be saved etc). 0, 0 is
 * the top left hand corner X increases to the right Y increases in the downwards direction.
 */
public interface MapViewPort {

  /**
   * Centre the map view on a new location.
   *
   * @param point the map co-ordinates to centre the view on.
   */
  void centerOn(Point2D point);

  /**
   * Adjusts the display size and recalculates the map view area.
   *
   * @param size The new size of the display.
   */
  void adjustDisplaySize(Rectangle2D size);

  /**
   * Sets the zoom level for the view and recalculates the map view area.
   *
   * @param level the new zoom level.
   */
  void setZoomLevel(double level);

  /**
   * Adds to the zoom level of the map.
   *
   * @param delta the amount to add.
   */
  void addZoomLevel(double delta);

  /** Zooms in by a single step. */
  void zoomIn();

  /** Zooms out by a single step. */
  void zoomOut();

  /**
   * Returns the viewable bounds in map co-ordinates.
   *
   * @return the viewable bounds in map co-ordinates.
   */
  Rectangle2D getViewBounds();

  /**
   * Returns the viewable bounds in display co-ordinates (with 0, 0 as top left of map view)
   *
   * @return the viewable bounds in display co-ordinates.
   */
  Rectangle2D getDisplaySize();

  /**
   * Returns the zoom level of the view.
   *
   * @return the zoom level of the view.
   */
  double getZoomLevel();

  /**
   * Returns the point in map co-ordinates that the view is centred on.
   *
   * @return the point in the map co-ordinates that the view is centred on.
   */
  Point2D getViewCenteredOn();

  /**
   * Converts the specified map co-ordinate point to display co-ordinates.
   *
   * @param point the map point to convert from.
   * @return the point in display co-ordinates.
   */
  Point2D convertMapToDisplay(Point2D point);

  /**
   * Converts the specified map co-ordinate point to display co-ordinates.
   *
   * @param mapX the x value of the map point to convert.
   * @param mapY the y value of the map point to convert.
   * @return the point converted to display co-ordinates.
   */
  Point2D convertMapToDisplay(double mapX, double mapY);

  /**
   * Converts the specified display co-ordinate to a map co-ordinate.
   *
   * @param displayPoint the point in display co-ordinates to convert.
   * @return the point converted to map co-ordinates.
   */
  Point2D convertDisplayToMap(Point2D displayPoint);

  /**
   * Converts the specified display co-ordinate to a map co-ordinate.
   *
   * @param displayX The x of the display co-ordinate to convert.
   * @param displayY
   * @return the point converted to map co-ordinates.
   */
  Point2D convertDisplayToMap(double displayX, double displayY);

  /**
   * Pans the view in the direction of the specified vector.
   *
   * @param deltaVector the vector to move view in.
   */
  void panView(Point2D deltaVector);

  /**
   * Pans the view in the direction of the specified vector.
   *
   * @param deltaX the value to pan by in the x direction.
   * @param deltaY the value to pan by in the y direction.
   */
  void panView(double deltaX, double deltaY);

  /**
   * Pans the view left.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewLeft();

  /**
   * Pans the view right.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewRight();

  /**
   * Pans the view up.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewUp();

  /**
   * Pans the view down.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewDown();

  /**
   * Pans the view left and up.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewLeftUp();

  /**
   * Pans the view left and down.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewLeftDown();

  /**
   * Pans the view right and up.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewRightUp();

  /**
   * Pans the view right and down.
   *
   * @see #setPanViewStep(double)
   * @see #getPanViewStep()
   */
  void panViewRightDown();

  /**
   * Returns the passed in vector in map co-ordinates scaled to display co-ordinates.
   *
   * @param mapVector the vector in map co-ordinates.
   * @return the vector scaled to display co-ordinates.
   */
  Point2D scaleVector(Point2D mapVector);

  /**
   * Sets the game map that this view port maps to a display.n
   *
   * @param gameMap the game map that this view port is a view into.
   */
  void setGameMap(GameMap gameMap);

  /**
   * Returns the game map that this view port maps to a display.
   *
   * @return game map that the view port maps to a display.
   */
  GameMap getGameMap();

  /**
   * Returns the co-ordinates of the specified corner of the view port in map co-ordinates.
   *
   * @param corner the {@link MapViewCorner} to return.
   * @return the co-ordinates of the corner in map co-ordinates.
   */
  Point2D getCorner(MapViewCorner corner);

  /**
   * Returns the center of the grid cell that is visible in the specified corner in map co-ordinates.
   *
   * This produces the same result as <code>getGridCenter(getCorner(center))code>
   *
   * @param corner the {@link MapViewCorner} to return.
   *
   * @return the co-ordinates center of the grid which contains the corner in map-coordinates.
   *
   * @see #getGridCenter(Point2D)
   */
  Point2D getCornerGridCenter(MapViewCorner corner);

  /**
   * Returns the center of the grid of the specified point, both the passed in and returned points
   * are iu map co-ordinates.
   *
   * @param mapPoint the point on the map to map to a grid cell.
   * @return the center of the grid cell for specified point in map co-ordinates.
   */
  Point2D getGridCenter(Point2D mapPoint);

  /**
   * Returns the center of the grid of the specified point, both the passed in and returned points
   * are iu map co-ordinates.
   *
   * @param mapX the X point on the map to map to a grid cell.
   * @param mapY the X point on the map to map to a grid cell.
   * @return the center of the grid cell for specified point in map co-ordinates.
   */
  Point2D getGridCenter(double mapX, double mapY);

  /**
   * Returns the center of the grid in map co-ordinates for a given display x,y.
   *
   * @param displayX The X display co-ordinate.
   * @param displayY The Y display co-ordinate.
   * @return The point in the center of the grid in map co-ordinates.
   */
  Point2D convertDisplayToMapGridCenter(double displayX, double displayY);

  /**
   * Returns the center of the grid in display co-ordinates for a given display x,y.
   *
   * @param displayX The X display co-ordinate.
   * @param displayY The Y display co-ordinate.
   * @return The point in the center of the grid in display co-ordinates.
   */
  Point2D getDisplayGridCenter(double displayX, double displayY);

  /**
   * Sets increment used by the {@link #zoomIn()} and {@link #zoomOut()} methods.
   *
   * @param value the zoom increment to use.
   */
  void setZoomStep(double value);

  /**
   * Sets the increment used by the panViewXXXX() functions that take no arguments.
   *
   * @param value the amount to pan in the different directions.
   * @see #panViewLeft()
   * @see #panViewLeftUp()
   * @see #panViewUp()
   * @see #panViewRightUp()
   * @see #panViewRight()
   * @see #panViewRightDown()
   * @see #panViewDown()
   * @see #panViewLeftDown()
   */
  void setPanViewStep(double value);

  /**
   * Returns the increment used by the {@link #zoomIn()} and {@link #zoomOut()} methods.
   *
   * @return the zoom increment to use.
   */
  double getZoomStep();

  /**
   * Returns the increment used by the panViewXXXX() functions that take no arguments.
   *
   * @return the increment used.
   * @see #panViewLeft()
   * @see #panViewLeftUp()
   * @see #panViewUp()
   * @see #panViewRightUp()
   * @see #panViewRight()
   * @see #panViewRightDown()
   * @see #panViewDown()
   * @see #panViewLeftDown()
   */
  double getPanViewStep();

  /**
   * Creates a rectangle centered at <code>x, y</code> of <code>width, height</code> in map
   * co-ordinates and converts that to display co-ordinates.
   *
   * @param x The x co-ordinate of the that the rectangle is centred on (map co-ordinates).
   * @param y The x co-ordinate of the that the rectangle is centred on (map co-ordinates).
   * @param width The width of the rectangle (map co-ordinates).
   * @param height The height of the rectangle (map co-ordinates).
   * @return a {@link Rectangle2D} in screen co-ordinates.
   */
  Rectangle2D convertCenteredMapRectangleToDisplay(double x, double y, double width, double height);

  /**
   * Converts a list of map co-ordinates into display co-ordinates.
   *
   * @param points the points in map co-ordinates to convert.
   * @return a {@link List} of points converted into display co-ordinates.
   */
  List<Point2D> convertMapToDisplay(List<Point2D> points);

  /**
   * Returns a list of {@link Double}s taken as x,y pairs from map co-ordinates to display
   * co-ordinates.
   *
   * @param xy The list of x,y co-ordinates.
   * @return list of {@link Double}s x,y display co-ordinates.
   */
  List<Double> convertMapDoublesToDisplay(List<Double> xy);

  /**
   * Returns a rectangle converted from display co-ordinates to map co-ordinates.
   *
   * @param rect the rectangle in display co-ordinates.
   * @return the rectangle in map co-ordinates.
   */
  MRectangle convertDisplayRectangleToMap(Rectangle2D rect);

  /**
   * Returns a rectangle converted from display co-ordinates to map co-ordinates.
   *
   * @param x1 the x co-ordinate of one of the corners of the rectangle.
   * @param y1 the y co-ordinate of one of the corners of the rectangle.
   * @param x2 the x co-ordinate of the opposite corner of the rectangle.
   * @param y2 the y co-ordinate of the opposite corner of the rectangle.
   * @return the rectangle in map co-ordinates.
   */
  MRectangle convertDisplayRectangleToMap(double x1, double y1, double x2, double y2);

  /**
   * Converts a collection of map polygons into display polygons.
   *
   * @param mapPoly the map polygons to convert.
   * @return a collection of converted display polygons,
   */
  Set<DPolygon> convertMapPolygonsToDisplay(List<MPolygon> mapPoly);
}
