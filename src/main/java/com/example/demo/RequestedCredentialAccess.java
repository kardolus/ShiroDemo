package com.example.demo;

import org.apache.shiro.authz.Permission;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RequestedCredentialAccess implements Permission {

  private final Path requestedFile;
  private final FileOperation operation;

  public RequestedCredentialAccess(String file, FileOperation operation) {
    this.requestedFile = Paths.get(file);
    this.operation = operation;
  }

  public Path getRequestedFile() {
    return requestedFile;
  }

  public FileOperation getOperation() {
    return operation;
  }

  @Override
  public boolean implies(Permission permission) {
    return false;
  }
}
