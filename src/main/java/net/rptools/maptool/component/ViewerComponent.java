package net.rptools.maptool.component;

import java.util.UUID;

/**
 * Component for holding data about viewers.
 */
public class ViewerComponent implements  Component {

  /** The ID for the component. */
  private UUID id = UUID.randomUUID();

  @Override
  public UUID getId() {
    return id;
  }
}
