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
