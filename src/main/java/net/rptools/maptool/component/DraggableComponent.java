package net.rptools.maptool.component;

import java.util.UUID;
import net.rptools.maptool.entity.Entity;

public class DraggableComponent implements Component {

  /** The id of the <code>DraggableComponent</code>. */
  private UUID id = UUID.randomUUID();

  private double fromX;
  private double fromY;
  private double toX;
  private double toY;

  private boolean beingDragged = false;

  public static boolean isBeingDragged(Entity entity) {
    return entity.hasComponent(DraggableComponent.class) && entity.getComponent(DraggableComponent.class).get().beingDragged;
  }

  public static boolean isDraggable(Entity entity) {
    return entity.hasComponent(DraggableComponent.class);
  }

  @Override
  public UUID getId() {
    return id;
  }

  public double getFromX() {
    return fromX;
  }

  public void setFromX(double fromX) {
    this.fromX = fromX;
  }

  public double getFromY() {
    return fromY;
  }

  public void setFromY(double fromY) {
    this.fromY = fromY;
  }

  public double getToX() {
    return toX;
  }

  public void setToX(double toX) {
    this.toX = toX;
  }

  public double getToY() {
    return toY;
  }

  public void setToY(double toY) {
    this.toY = toY;
  }

  public boolean isBeingDragged() {
    return beingDragged;
  }

  public void setBeingDragged(boolean beingDragged) {
    this.beingDragged = beingDragged;
  }
}
