package com.example.demo;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.demo.FileRelations.is;

class PermissionForAllDescendantCredentials extends BaseFilePermission {

  public PermissionForAllDescendantCredentials(String file, String operation) {
    super(Paths.get(file), FileOperation.valueOf(operation));
  }

  @Override
  protected boolean accessIsAllowed(Path requestedFile) {
    return is(requestedFile).descendantOf(ownFile);
  }
}
