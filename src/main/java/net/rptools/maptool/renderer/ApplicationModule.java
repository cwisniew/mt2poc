package net.rptools.maptool.renderer;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import net.rptools.maptool.renderer.map.GameMap;
import net.rptools.maptool.renderer.map.GameMapImpl;
import net.rptools.maptool.renderer.map.MapView;
import net.rptools.maptool.renderer.map.MapViewImpl;

/**
 * <code>ApplicationModule</code> used for Google Guice injection bindings.
 * */
public class ApplicationModule extends AbstractModule {
  private final EventBus eventBus = new EventBus("render-poc Event Bus");

  @Override
  protected void configure() {
    bind(EventBus.class).toInstance(eventBus);
    bind(GameMap.class).to(GameMapImpl.class);
    bind(MapView.class).to(MapViewImpl.class);
  }
}
