package net.rptools.maptool.renderer.map.events;

import java.util.UUID;

/**
 * This event is sent when a {@link net.rptools.maptool.renderer.map.GameMap} is updated.
 */
public class MapUpdateEvent {

  /** The id of the updated {@link net.rptools.maptool.renderer.map.GameMap}. */
  private final UUID mapId;

  /**
   * Creates a new <code>MapUpdateEvent</code>
   * @param id The id of the {@link net.rptools.maptool.renderer.map.GameMap} that was updated.
   */
  public MapUpdateEvent(UUID id) {
    mapId = id;
  }

  /**
   * The id of the {@link net.rptools.maptool.renderer.map.GameMap} that was updated.
   * @return the id of the map that was updated.
   */
  public UUID getMapId() {
    return mapId;
  }

}
