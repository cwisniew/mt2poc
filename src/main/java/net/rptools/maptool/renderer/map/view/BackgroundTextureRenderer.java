package net.rptools.maptool.renderer.map.view;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

class BackgroundTextureRenderer {

  public void render(Canvas canvas, Image backgroundTexture, double scale, Point2D translation) {
    double width = canvas.getWidth();
    double height = canvas.getHeight();

    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.save();

    gc.translate(translation.getX(), translation.getY());
    gc.scale(scale, scale);

    double textureWidth = backgroundTexture.getWidth();
    double textureHeight = backgroundTexture.getHeight();

    int numXTextures = (int) (width / textureWidth) + 1;
    int numYTextures = (int) (height / textureHeight) + 1;

    double endX = Math.ceil(numXTextures / 2.0) * textureWidth;
    double startX = -endX;
    double endY = Math.ceil(numYTextures / 2.0) * textureHeight;
    double startY = -endY;

    for (double x = startX; x <= endX; x += textureWidth) {
      for (double y = startY; y <= endX; y += textureHeight) {
        gc.drawImage(backgroundTexture, x, y);
      }
    }

    gc.setFill(Color.RED);
    gc.fillOval(-5, -5, 10, 10);

    gc.restore();
  }

}
