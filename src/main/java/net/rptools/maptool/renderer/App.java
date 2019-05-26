package net.rptools.maptool.renderer;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rptools.maptool.renderer.map.MapView;
import net.rptools.maptool.renderer.ui.controller.MainWindowController;
import net.rptools.maptool.renderer.ui.controller.SidePanelController;

/**
 * Application class for rendering proof of concept.
 */
public class App extends Application {

  /** The {@link MapView} for rendering the {@link net.rptools.maptool.renderer.map.GameMap|.}*/
  private MapView mainMapView;

  /**
   * Entry point.
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
    Pane sidePanel = sideFxmlLoader.load(getClass().getResource("/fxml/SidePanel.fxml"));
    SidePanelController sidePanelController = sideFxmlLoader.getController();


    Scene scene = new Scene(mainWindow ,1024, 800);

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
   * Creates a default {@link MapView} with a default {@link net.rptools.maptool.renderer.map.GameMap}/
   *
   * @param injector The google guice {@link Injector} for dependency injection.
   *
   * @return the {@link Pane} where the view of the map is rendered.
   */
  private Pane createDefaultMapView(Injector injector) {
    mainMapView = injector.getInstance(MapView.class);
    Image image = new Image(getClass().getResourceAsStream("/assets/textures/Grass.png"));
    mainMapView.getGameMap().setTexturedBackground(image);
    return mainMapView.getParentNode();
  }


}
