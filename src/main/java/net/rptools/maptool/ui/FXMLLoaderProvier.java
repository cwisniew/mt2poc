package net.rptools.maptool.ui;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import javafx.fxml.FXMLLoader;

/**
 * Provider that provides an {@link FXMLLoader} that makes sure the controllers in the FXML file
 * have dependencies injected.
 */
public class FXMLLoaderProvier implements Provider<FXMLLoader> {

  @Inject
  Injector injector;

  @Override
  public FXMLLoader get() {
    FXMLLoader fxmlLoader = new FXMLLoader();

    //
    fxmlLoader.setControllerFactory(cl -> injector.getInstance(cl));

    return fxmlLoader;
  }
}
