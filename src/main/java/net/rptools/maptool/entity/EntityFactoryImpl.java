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
import net.rptools.maptool.component.DraggableComponent;
import net.rptools.maptool.component.ImageComponent;
import net.rptools.maptool.component.PositionComponent;

/** Factory class for creating {@link Entity}s. */
public class EntityFactoryImpl implements EntityFactory {

  @Override
  public EntityBuilder createEntity() {
    return new EntityBuilder();
  }

  @Override
  public Entity createMapFigure(double x, double y, double z, Image image) {
    return createEntity()
        .with(new PositionComponent(x, y, z))
        .with(new ImageComponent(image))
        .with(new DraggableComponent())
        .build();
  }

  @Override
  public Entity createSnapToGridMapFigure(double x, double y, double z, Image image) {
    return createEntity()
        .with(new PositionComponent(x, y, z, true))
        .with(new ImageComponent(image))
        .with(new DraggableComponent())
        .build();
  }
}
