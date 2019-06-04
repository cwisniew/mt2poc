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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import net.rptools.maptool.component.Component;

/** Entities used within MapTool. */
public class Entity {

  /** The id of the <code>Entity</code>. */
  private UUID id;

  /** The {@link Component}s for this <code>Entity</code> */
  private final Map<Class<? extends Component>, Component> componentMap = new HashMap<>();

  /**
   * Creates a new <code>Entity</code> object.
   *
   * @param components The {@link Component}s the <code>Entity</code> has.
   */
  public Entity(Set<Component> components) {
    id = UUID.randomUUID();
    for (Component component : components) {
      componentMap.put(component.getClass(), component);
    }
  }

  /**
   * Returns the ide of this <code>Entity</code>.
   *
   * @return the id.
   */
  public UUID getId() {
    return id;
  }

  /**
   * Returns <code>true</code> if this <code>Entity</code> contains the specified {@link Component}.
   *
   * @param component the component to check for.
   * @return <code>true</code> if this <code>Entity</code> contains the {@link Component}.
   */
  public boolean hasComponent(Class<? extends Component> component) {
    return componentMap.containsKey(component);
  }

  /**
   * Returns the {@link Component} for the specified component class.
   *
   * @param component the class of the {@link Component}.
   * @return the {@link Component} for the class.
   */
  public <T extends Component> Optional<T> getComponent(Class<T> component) {
    return Optional.ofNullable(component.cast(componentMap.get(component)));
  }

  /**
   * Returns the types of {@link Component}s that this {@link Entity} has.
   *
   * @return the types of {@link Component}s.
   */
  public Set<Class<? extends Component>> getComponentTypes() {
    return componentMap.keySet();
  }

  /**
   * Returns the {@link Component}s that this {@link Entity} has.
   *
   * @return the {@link Component}s.
   */
  public Collection<Component> getComponents() {
    return componentMap.values();
  }
}
