package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import javafx.scene.image.Image;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;


/**
 * Class used to represent maps in the game.
 */
public class GameMapImpl implements GameMap {

  /** The id of the game map. */
  private UUID id;
  /** The {@link Image} to use as a background texture. */
  private Image backgroundTexture;

  /** The {@link EventBus} used to send events when the map is updated. */
  private final EventBus eventBus;

  /**
   * Creates a new <code>GameMapImpl</code> object.
   *
   * @param eventBus the event bus to send update events on.
   */
  @Inject
  public GameMapImpl(EventBus eventBus) {
    this.eventBus = eventBus;
    id = UUID.randomUUID();
  }

  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public void setTexturedBackground(Image texture) {
    backgroundTexture = texture;
    eventBus.post(new MapUpdateEvent(id));
  }


  @Override
  public Optional<Image> getBackgroundTexture() {
    return Optional.ofNullable(backgroundTexture);
  }
}
