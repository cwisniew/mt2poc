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

  @Inject Injector injector;

  @Override
  public FXMLLoader get() {
    FXMLLoader fxmlLoader = new FXMLLoader();

    //
    fxmlLoader.setControllerFactory(cl -> injector.getInstance(cl));

    return fxmlLoader;
  }
}
