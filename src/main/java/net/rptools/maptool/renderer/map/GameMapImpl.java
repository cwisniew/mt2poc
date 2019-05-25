package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import javafx.scene.image.Image;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;

public class GameMapImpl implements GameMap {

  private UUID id;
  private Image backgroundTexture;
  private final EventBus eventBus;

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


  public Optional<Image> getBackgroundTexture() {
    return Optional.ofNullable(backgroundTexture);
  }
}
