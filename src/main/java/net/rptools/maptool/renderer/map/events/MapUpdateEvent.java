package net.rptools.maptool.renderer.map.events;

import java.util.UUID;

public class MapUpdateEvent {
  private final UUID mapId;

  public MapUpdateEvent(UUID id) {
    mapId = id;
  }

  public UUID getMapId() {
    return mapId;
  }

}
