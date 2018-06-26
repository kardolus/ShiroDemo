package com.example.demo;

import org.apache.shiro.authz.Permission;

import java.nio.file.Path;

abstract class BaseFilePermission implements Permission {

  protected final Path ownFile;
  protected final FileOperation operation;

  BaseFilePermission(Path file, FileOperation operation) {
    this.ownFile = file;
    this.operation = operation;
  }

  @Override
  final public boolean implies(Permission permission) {
    return isFileAccessRequest(permission) && fileAccessIsImplied((RequestedCredentialAccess) permission);
  }

  private boolean isFileAccessRequest(Permission permission) {
    return permission instanceof RequestedCredentialAccess;
  }

  private boolean fileAccessIsImplied(RequestedCredentialAccess requestedCredentialAccess) {
    return requestedOperationIsImplied(requestedCredentialAccess) && accessIsAllowed(requestedCredentialAccess.getRequestedFile());
  }

  private boolean requestedOperationIsImplied(RequestedCredentialAccess requestedCredentialAccess) {
    return requestsRead(requestedCredentialAccess) || writeIsPermitted();
  }

  private boolean requestsRead(RequestedCredentialAccess requestedCredentialAccess) {
    return requestedCredentialAccess.getOperation() == FileOperation.READ;
  }

  private boolean writeIsPermitted() {
    return this.operation == FileOperation.WRITE;
  }

  /**
   * Template method called during permission check that allows subclasses to check access permissions.
   *
   * @param requestedFile Path
   * @return true if the RequestedFileAccess is granted
   */
  protected abstract boolean accessIsAllowed(Path requestedFile);
}

