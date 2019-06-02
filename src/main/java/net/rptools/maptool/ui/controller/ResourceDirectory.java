package net.rptools.maptool.ui.controller;

import java.nio.file.Path;

public class ResourceDirectory {

  private final String name;

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
    return "ResourceDirectory{" +
        "name='" + name + '\'' +
        ", path=" + path +
        '}';
  }
}
