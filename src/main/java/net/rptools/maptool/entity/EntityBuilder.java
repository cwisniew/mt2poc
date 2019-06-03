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

import java.util.HashSet;
import java.util.Set;
import net.rptools.maptool.component.Component;

/** Builder class for building {@link Entity} objects. */
public class EntityBuilder {

  /** The {@link Component}s for the {@link Entity} so far. */
  private Set<Component> components = new HashSet<>();;

  /**
   * Adds a new {@link Component} to the {@link Entity} being built.
   *
   * @param component The {@link Component} to add.
   * @return <code>this</code> to allow chaining of methods.
   */
  public EntityBuilder with(Component component) {
    components.add(component);
    return this;
  }

  /**
   * Builds a {@link Entity} with the details previously provided to the builder.
   *
   * @return the {@link Entity}.
   */
  public Entity build() {
    return new Entity(components);
  }
}
