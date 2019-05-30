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
package net.rptools.maptool.renderer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import net.rptools.maptool.renderer.map.grid.RectangleGrid;
import net.rptools.maptool.renderer.map.grid.render.GridLine;
import net.rptools.maptool.renderer.map.view.MapView;
import net.rptools.maptool.renderer.ui.controller.MainWindowController;
import net.rptools.maptool.renderer.ui.controller.SidePanelController;

/** Application class for rendering proof of concept. */
public class App extends Application {

  /** The {@link MapView} for rendering the {@link net.rptools.maptool.renderer.map.GameMap|.} */
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

    FXMLLoader mainFxmlLoader = new FXMLLoader(getClass().getResource("/fxml/MainWindow.fxml"));
    Pane mainWindow = mainFxmlLoader.load();
    MainWindowController mainWindowController = mainFxmlLoader.getController();

    FXMLLoader sideFxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SidePanel.fxml"));
    Pane sidePanel = sideFxmlLoader.load();
    SidePanelController sidePanelController = sideFxmlLoader.getController();

    Scene scene = new Scene(mainWindow, 1024, 800);

    mainWindow.prefWidthProperty().bind(scene.widthProperty());
    mainWindow.prefHeightProperty().bind(scene.heightProperty());

    Pane mapViewPane = createDefaultMapView(injector);
    mainWindowController.setMain(mapViewPane);

    mainWindowController.setLeft(sidePanel);

    primaryStage.setScene(scene);

    primaryStage.show();
    primaryStage.setTitle("Renderer POC");
  }

  /**
   * Creates a default {@link MapView} with a default {@link
   * net.rptools.maptool.renderer.map.GameMap}/
   *
   * @param injector The google guice {@link Injector} for dependency injection.
   * @return the {@link Pane} where the view of the map is rendered.
   */
  private Pane createDefaultMapView(Injector injector) {
    mainMapView = injector.getInstance(MapView.class);
    Image image = new Image(getClass().getResourceAsStream("/assets/textures/Grass.png"));
    mainMapView.getGameMap().setTexturedBackground(image);
    mainMapView.getGameMap().setGrid(new RectangleGrid(50));
    mainMapView.setGridLine(new GridLine(Color.BLACK, 1, 3));
    return mainMapView.getParentNode();
  }
}
