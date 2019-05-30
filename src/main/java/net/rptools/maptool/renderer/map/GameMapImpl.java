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
package net.rptools.maptool.renderer.map;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import net.rptools.maptool.renderer.map.events.MapUpdateEvent;
import net.rptools.maptool.renderer.map.grid.Grid;

/** Class used to represent maps in the game. */
public class GameMapImpl implements GameMap {

  /** The id of the game map. */
  private UUID id;
  /** The {@link Image} to use as a background texture. */
  private Image backgroundTexture;

  /** The {@link EventBus} used to send events when the map is updated. */
  private final EventBus eventBus;

  /** The {@link Grid} for this map. */
  private Grid grid;

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

  @Override
  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  @Override
  public Optional<Grid> getGrid() {
    return Optional.ofNullable(grid);
  }

  @Override
  public Point2D getGridCenter(Point2D mapPoint) {
    return grid.getGridCenter(mapPoint);
  }
}
