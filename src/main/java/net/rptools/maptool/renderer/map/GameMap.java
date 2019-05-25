package net.rptools.maptool.renderer.map;

import java.util.Optional;
import java.util.UUID;
import javafx.scene.image.Image;

/**
 * Interface implemented by classes that represent maps used in the game.
 */
public interface GameMap {

  /** The id of the <code>GameMap</code>. */
  UUID getId();

  /**
   * Sets the {@link Image} texture to use as the background of the <code>GameMap</code>.
   * @param texture The texture to use as the background.
   */
  void setTexturedBackground(Image texture);


  /**
   * Returns the {@Image} to be used as the background texture.
   * @return the background texture.
   */
  Optional<Image> getBackgroundTexture();

}
