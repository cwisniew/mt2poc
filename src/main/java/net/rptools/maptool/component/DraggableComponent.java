/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * MapTool Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.maptool.component;

import java.util.UUID;
import net.rptools.maptool.entity.Entity;

/** Component that stores information related to being dragged across the map. */
public class DraggableComponent implements Component {

  /** The id of the <code>DraggableComponent</code>. */
  private UUID id = UUID.randomUUID();

  /** The x xo-ordinate of where it is being dragged from. */
  private double fromX;
  /** The y xo-ordinate of where it is being dragged from. */
  private double fromY;
  /** The x xo-ordinate of where it is being dragged to. */
  private double toX;
  /** The y xo-ordinate of where it is being dragged to. */
  private double toY;

  /** is it currently being dragged. */
  private boolean beingDragged = false;

  /**
   * Convenience method to determine if an {@link Entity} has a <code>DraggableComponent</code> and
   * if {@link #isBeingDragged()} is <code>true</code>.
   *
   * @param entity The entity to check.
   * @return <code>true</code> if the entity is currently being dragged.
   */
  public static boolean isBeingDragged(Entity entity) {
    return entity.hasComponent(DraggableComponent.class)
        && entity.getComponent(DraggableComponent.class).get().beingDragged;
  }

  /**
   * Convenience method to determine if an {@link Entity} has a <code>DraggableComponent</code>.
   *
   * @param entity The entity being checked.
   * @return <code>true</code> if the entity has the component.
   */
  public static boolean isDraggable(Entity entity) {
    return entity.hasComponent(DraggableComponent.class);
  }

  @Override
  public UUID getId() {
    return id;
  }

  /**
   * Returns the x co-ordinate of where the entity is being dragged from.
   *
   * @return the x co-ordinate of where the entity is being dragged from.
   */
  public double getFromX() {
    return fromX;
  }

  /**
   * Sets the x co-ordinate of where the entity is being dragged from.
   *
   * @param fromX the x co-ordinate of where the entity is being dragged from.
   */
  public void setFromX(double fromX) {
    this.fromX = fromX;
  }

  /**
   * Returns the y co-ordinate of where the entity is being dragged from.
   *
   * @return the y co-ordinate of where the entity is being dragged from.
   */
  public double getFromY() {
    return fromY;
  }

  /**
   * Sets the y co-ordinate of where the entity is being dragged from.
   *
   * @param fromY the y co-ordinate of where the entity is being dragged from.
   */
  public void setFromY(double fromY) {
    this.fromY = fromY;
  }

  /**
   * Returns the x co-ordinate of where the entity is being dragged to.
   *
   * @return the x co-ordinate of where the entity is being dragged to.
   */
  public double getToX() {
    return toX;
  }

  /**
   * Sets the x co-ordinate of where the entity is being dragged to.
   *
   * @param toX the x co-ordinate of where the entity is being dragged to.
   */
  public void setToX(double toX) {
    this.toX = toX;
  }

  /**
   * Returns the y co-ordinate of where the entity is being dragged to.
   *
   * @return the y co-ordinate of where the entity is being dragged to.
   */
  public double getToY() {
    return toY;
  }

  /**
   * Sets the y co-ordinate of where the entity is being dragged from.
   *
   * @param toY the y co-ordinate of where the entity is being dragged from.
   */
  public void setToY(double toY) {
    this.toY = toY;
  }

  /**
   * Returns if the entity is currently being dragged,
   *
   * @return <code>true</code> if the entity is currently being dragged.
   */
  public boolean isBeingDragged() {
    return beingDragged;
  }

  /**
   * Sets if the entity is currently being dragged.
   *
   * @param beingDragged <code>true</code> if the entity is currently being dragged.
   */
  public void setBeingDragged(boolean beingDragged) {
    this.beingDragged = beingDragged;
  }
}
