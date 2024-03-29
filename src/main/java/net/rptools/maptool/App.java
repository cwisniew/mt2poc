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
package net.rptools.maptool;

import com.google.common.eventbus.EventBus;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.rptools.maptool.general.event.GUIStartupEvent;
import net.rptools.maptool.map.grid.RectangleGrid;
import net.rptools.maptool.map.grid.render.GridLine;
import net.rptools.maptool.map.view.MapView;
import net.rptools.maptool.ui.controller.MainWindowController;
import net.rptools.maptool.ui.controller.SidePanelController;

/** Application class for rendering proof of concept. */
public class App extends Application {

  /** The {@link MapView} for rendering the {@link net.rptools.maptool.map.GameMap|.} */
  private MapView mainMapView;

  /**
   * Entry point.
   *
   * @param args command line arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    Injector injector = Guice.createInjector(new ApplicationModule());

    // FXMLLoader mainFxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
    FXMLLoader mainFxmlLoader = injector.getInstance(FXMLLoader.class);
    mainFxmlLoader.setLocation(getClass().getResource("/fxml/MainWindow.fxml"));

    Pane mainWindow = mainFxmlLoader.load();
    MainWindowController mainWindowController = mainFxmlLoader.getController();

    FXMLLoader sideFxmlLoader = injector.getInstance(FXMLLoader.class);
    sideFxmlLoader.setLocation(getClass().getResource("/fxml/SidePanel.fxml"));
    Pane sidePanel = sideFxmlLoader.load();
    SidePanelController sidePanelController = sideFxmlLoader.getController();

    Scene scene = new Scene(mainWindow, 1024, 800);

    mainWindow.prefWidthProperty().bind(scene.widthProperty());
    mainWindow.prefHeightProperty().bind(scene.heightProperty());

    MapView mapView = createDefaultMapView(injector);
    mainWindowController.setMapView(mapView);

    mainWindowController.setLeft(sidePanel);

    primaryStage.setScene(scene);

    primaryStage.show();
    primaryStage.setTitle("Renderer POC");

    EventBus eventBus = injector.getInstance(EventBus.class);
    eventBus.post(new GUIStartupEvent());

    AppConfig appConfig = injector.getInstance(AppConfig.class);
    appConfig.setRenderTimeDisplayCallback(ms -> sidePanelController.setLastRenderMs(ms));
  }

  /**
   * Creates a default {@link MapView} with a default {@link net.rptools.maptool.map.GameMap}/
   *
   * @param injector The google guice {@link Injector} for dependency injection.
   * @return the {@link MapView} for the map.
   */
  private MapView createDefaultMapView(Injector injector) {
    mainMapView = injector.getInstance(MapView.class);
    Image image = new Image(getClass().getResourceAsStream("/assets/textures/Grass.png"));
    mainMapView.getGameMap().setTexturedBackground(image);
    mainMapView.getGameMap().setGrid(new RectangleGrid(50));
    mainMapView.setGridLine(new GridLine(Color.BLACK, 1, 3));
    return mainMapView;
  }
}
