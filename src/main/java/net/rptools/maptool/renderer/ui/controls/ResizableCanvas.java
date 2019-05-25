package net.rptools.maptool.renderer.ui.controls;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Resizable version of {@link Canvas}
 */
public class ResizableCanvas extends Canvas {

  /**
   * Creates a new <code>ResizableCanvas</code>
   */
  public ResizableCanvas() {
    widthProperty().addListener(w -> draw());
    heightProperty().addListener(h -> draw());
  }


  /**
   * Draws a basic plain background.
   */
  protected void draw() {
    double width = getWidth();
    double height = getHeight();

    GraphicsContext gc = getGraphicsContext2D();
    gc.clearRect(0, 0, width, height);
    gc.setStroke(Color.RED);

    gc.strokeLine(0, 0, width, height);
    gc.strokeLine(width, 0, 0, height);
  }

  @Override
  public boolean isResizable() {
    return true;
  }

  @Override
  public double prefWidth(double width) {
    return getWidth();
  }

  @Override
  public double prefHeight(double height) {
    return getHeight();
  }
}
