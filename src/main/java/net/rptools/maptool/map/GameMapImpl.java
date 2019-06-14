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
package net.rptools.maptool.map;

import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import net.rptools.maptool.component.MapFigureComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.events.MapEntityAddedEvent;
import net.rptools.maptool.map.events.MapEntityRemovedEvent;
import net.rptools.maptool.map.events.MapUpdateEvent;
import net.rptools.maptool.map.grid.Grid;
import net.rptools.maptool.map.view.vision.VisibleArea;
import net.rptools.maptool.map.view.vision.VisionCalculator;

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

  /** The {@link Entity}s on the map. */
  private final Set<Entity> entities = new HashSet<>();

  /** the class used for vision calculations. */
  @Inject private VisionCalculator visionCalculator;

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
    return grid.getGridCenter(mapPoint.getX(), mapPoint.getY());
  }

  @Override
  public Point2D getGridCenter(double mapX, double mapY) {
    return grid.getGridCenter(mapX, mapY);
  }

  @Override
  public void putEntity(Entity entity) {
    if (MapFigureComponent.isSnapToGrid(entity)) {
      var pc = entity.getComponent(MapFigureComponent.class).get();
      Point2D snapCenter = grid.getGridCenter(pc.getX(), pc.getY());
      pc.setX(snapCenter.getX());
      pc.setY(snapCenter.getY());
    }
    entities.add(entity);
    visionCalculator.addEntity(entity);
    eventBus.post(new MapEntityAddedEvent(this, entity));
  }

  @Override
  public void removeEntity(Entity entity) {
    entities.remove(entity);
    visionCalculator.removeEntity(entity);
    eventBus.post(new MapEntityRemovedEvent(this, entity));
  }

  @Override
  public Collection<Entity> getEntities() {
    return Collections.unmodifiableCollection(entities);
  }

  @Override
  public VisibleArea getVisibleArea() {
    visionCalculator.calculate();
    return visionCalculator.getTotalVisibleArea();
  }
}
