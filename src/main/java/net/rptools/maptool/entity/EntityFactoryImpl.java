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
package net.rptools.maptool.entity;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import net.rptools.maptool.component.DraggableComponent;
import net.rptools.maptool.component.ImageComponent;
import net.rptools.maptool.component.MapFigureComponent;
import net.rptools.maptool.component.PolygonDrawableComponent;
import net.rptools.maptool.component.ViewerComponent;
import net.rptools.maptool.component.VisionBlockingComponent;
import net.rptools.maptool.map.geom.MRectangle;

/** Factory class for creating {@link Entity}s. */
public class EntityFactoryImpl implements EntityFactory {

  @Override
  public EntityBuilder createEntity() {
    return new EntityBuilder();
  }

  @Override
  public Entity createMapFigure(double x, double y, double w, double h, double z, Image image) {
    return createEntity()
        .with(new MapFigureComponent(x, y, w, h, z))
        .with(new ImageComponent(image))
        .with(new DraggableComponent())
        .with(new ViewerComponent()) // TODO: This wont always be the case but POC :)
        .build();
  }

  @Override
  public Entity createSnapToGridMapFigure(
      double x, double y, double h, double w, double z, Image image) {
    return createEntity()
        .with(new MapFigureComponent(x, y, w, h, z, true))
        .with(new ImageComponent(image))
        .with(new DraggableComponent())
        .with(new ViewerComponent()) // TODO: This wont always be the case but POC :)
        .build();
  }

  @Override
  public Entity createDrawableRectangle(double x1, double y1, double x2, double y2, double z) {
    var rect = MRectangle.createRectangle(x1, y1, x2, y2);
    var poly = rect.asPolygon();

    return createEntity()
        .with(new PolygonDrawableComponent(poly, Color.RED, Color.TRANSPARENT)) // TODO: Wont always be this colour but  pox ;)
        .with(new VisionBlockingComponent(poly)) // TODO: This wont always be the case but POC :)
        .build();
  }
}
