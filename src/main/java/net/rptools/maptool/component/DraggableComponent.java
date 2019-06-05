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

public class DraggableComponent implements Component {

  /** The id of the <code>DraggableComponent</code>. */
  private UUID id = UUID.randomUUID();

  private double fromX;
  private double fromY;
  private double toX;
  private double toY;

  private boolean beingDragged = false;

  public static boolean isBeingDragged(Entity entity) {
    return entity.hasComponent(DraggableComponent.class)
        && entity.getComponent(DraggableComponent.class).get().beingDragged;
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
