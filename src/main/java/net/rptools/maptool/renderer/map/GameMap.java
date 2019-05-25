package net.rptools.maptool.renderer.map;

import java.util.UUID;
import javafx.scene.image.Image;

public interface GameMap {

  UUID getId();
  void setTexturedBackground(Image texture);

}
