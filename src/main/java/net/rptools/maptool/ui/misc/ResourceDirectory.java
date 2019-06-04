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
package net.rptools.maptool.ui.misc;

import java.nio.file.Path;

public class ResourceDirectory {

  /** The name of the file. */
  private final String name;

  /** The path of the file. */
  private final Path path;


  public ResourceDirectory(String name, Path path) {
    this.name = name;
    this.path = path;
  }

  public String getName() {
    return name;
  }

  public Path getPath() {
    return path;
  }

  @Override
  public String toString() {
    return "ResourceDirectory{" + "name='" + name + '\'' + ", path=" + path + '}';
  }
}
