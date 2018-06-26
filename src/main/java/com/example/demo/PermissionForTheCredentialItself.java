package com.example.demo;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.example.demo.FileRelations.is;

class PermissionForTheCredentialItself extends BaseFilePermission {

  public PermissionForTheCredentialItself(String file, Permission permission) {
    super(Paths.get(file), permission);
  }

  @Override
  protected boolean accessIsAllowed(Path requestedFile) {
    return is(requestedFile).theSameAs(ownFile);
  }
}
