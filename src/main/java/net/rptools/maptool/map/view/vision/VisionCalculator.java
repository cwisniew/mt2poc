package net.rptools.maptool.map.view.vision;

import java.util.Collection;
import java.util.Set;
import net.rptools.maptool.entity.Entity;


/**
 * Interface implemented by classes that perform vision calculations,
 */
public interface VisionCalculator {

  /**
   * Adds an entity to the vision calculator.
   *
   * @param entity the entity to add.
   */
  void addEntity(Entity entity);

  /**
   * Removes an entity from the vision calculator.
   *
   * @param entity the entity to be removed.
   */
  void removeEntity(Entity entity);

  /**
   * Adds several entities to the vision calculator.
   *
   * @param entities The entities to be added.
   */
  void addEntities(Collection<Entity> entities);

  /**
   * Removes several entities from the vision calculator.
   *
   * @param entities the entities that were removed.
   */
  void removeEntities(Collection<Entity> entities);

  /**
   * Returns the total visible area for all the viewers. This area is cached and is only updated
   * where the {@link #calculate()} method is called.
   *
   * @return the total visible area for all the viewers.
   */
  VisibleArea getTotalVisibleArea();

  /**
   * Returns the visible area for the specified entity. This area is cached and is only updated*
   * where the {@link #calculate()} method is called.
   *
   * @param entity the entity to return the visible area for.
   *
   * @return the visible area.
   */
  VisibleArea getVisibleArea(Entity entity);

  /**
   * Returns the visible entities (not yet implemented).
   *
   * @return the visible entities.
   */
  Set<Entity> getVisibleEntities();

  /**
   * Performs and caches visibility calculations.
   */
  void calculate();

}
