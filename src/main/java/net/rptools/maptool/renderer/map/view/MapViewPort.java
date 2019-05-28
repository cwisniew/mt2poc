package net.rptools.maptool.renderer.map.view;

import java.awt.Point;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Interface for classes that perform translations between screen and map Co-Ordinates.
 */
public interface MapViewPort {

  /**
   * Centre the map view on a new location.
   *
   * @param point the point to centre the view on.
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
   * Returns the viewable bounds in map co-ordinates.
   *
   * @return the viewable bounds in map co-ordinate.s
   */
  Rectangle2D getMapBounds();

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
  Point2D getCenteredOn();

  /**
   * Returns the specified point converted from map to display co-ordinates.
   *
   * @param point the map point to convert from.
   *
   * @return the point in display co-ordinates.
   */
  Point2D convertToScreen(Point2D point);

  /**
   * Returns the translation required to translate the map co-ordinate that the view iss centred on to the center of the screen.
   *
   * @return the translation required to translate map centred on to centre of screen.
   */
  Point2D getCentreScreenTranslate();


  /**
   * Returns the centre of the display.
   * @return the centre of the display
   */
  Point2D getScreenCentre();


  void translateCentredOn(Point2D vect);

}
