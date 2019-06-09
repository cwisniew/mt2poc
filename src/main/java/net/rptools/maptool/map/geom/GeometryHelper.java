package net.rptools.maptool.map.geom;


import javafx.geometry.Rectangle2D;

/**
 * Utility class for geometry based convenience methods.
 */
public interface GeometryHelper {

 /**
  * Returns a {@link Rectangle2D} given two x,y co-ordinates.
  *
  *
  * @param x1 The first x co-ordinate.
  * @param y1 The first y co-ordinate.
  * @param x2 The second x co-ordinate.
  * @param y2 The second y co-ordinate.
  *
  * @return a {@link Rectangle2D} for the given points.
  */
 Rectangle2D getRectangle2D(double x1, double y1, double x2, double y2);

}
