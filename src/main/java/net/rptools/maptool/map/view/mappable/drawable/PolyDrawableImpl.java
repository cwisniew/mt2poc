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
package net.rptools.maptool.map.view.mappable.drawable;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import net.rptools.maptool.component.PolygonDrawableComponent;
import net.rptools.maptool.entity.Entity;
import net.rptools.maptool.map.GameMap;
import net.rptools.maptool.map.view.MapViewPort;

/** Main implementation of the {@link PolyDrawable} class. */
public class PolyDrawableImpl implements PolyDrawable {

  /** The default opacity to use for marking the start position of the drag. */
  private static double DEFAULT_DRAG_START_OPACITY = 0.6;

  /** The node for the drawable. */
  private Group drawable = new Group();

  /** The polygon to render. */
  private Polygon polygon = new Polygon();

  /** The node for the beginning drag location. */
  private Group draggingDrawable;

  /** The {@link GameMap} that this drawable drawable is for. */
  private GameMap gameMap;

  /** The {@link MapViewPort} used to convert co-ordinates for this drawable. */
  private MapViewPort mapViewPort;

  /** The {@link Entity} that this drawable is for. */
  private Entity entity;

  /**
   * Creates a new <code>PolyDrawableImpl</code> object.
   *
   * @param map The game map that the drawable is on.
   * @param viewPort The viewport for translating between map and view co-ordinates.
   * @param ent The entity that contains the {@link PolygonDrawableComponent}.
   */
  public PolyDrawableImpl(GameMap map, MapViewPort viewPort, Entity ent) {
    gameMap = map;
    mapViewPort = viewPort;
    entity = ent;

    drawable.getChildren().add(polygon);
  }

  @Override
  public void update() {
    PolygonDrawableComponent pd = entity.getComponent(PolygonDrawableComponent.class).get();
    polygon.getPoints().setAll(mapViewPort.convertMapDoublesToDisplay(pd.getPolygon().getVerticesDoubleList()));
    polygon.setStroke(pd.getStroke());
    polygon.setFill(pd.getFill());
  }

  @Override
  public Node getNode() {
    return drawable;
  }

  @Override
  public Node getDraggedNode() {
    return draggingDrawable;
  }
}
