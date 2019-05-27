package net.rptools.maptool.renderer.map.view;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public interface MapViewPort {

  void centerOn(Point2D point);

  void adjustDisplaySize(Rectangle2D size);

  void setZoomLevel(double level);

  Rectangle2D getMapBounds();

  Rectangle2D getDisplaySize();

  double getZoomLevel();

  Point2D getCenteredOn();

  Point2D convertToScreen(Point2D point);

  Point2D getCentreScreenTranslate();
}
