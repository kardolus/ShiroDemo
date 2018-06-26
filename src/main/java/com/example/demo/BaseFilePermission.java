package com.example.demo;

import org.apache.shiro.authz.Permission;

import java.nio.file.Path;

abstract class BaseFilePermission implements Permission {

  protected final Path ownFile;
  protected final com.example.demo.Permission permission;

  BaseFilePermission(Path file, com.example.demo.Permission permission) {
    this.ownFile = file;
    this.permission = permission;
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
    if(requestedCredentialAccess.getOperation() == FileOperation.READ){
      return permission.getReadPermission();
    }
    if(requestedCredentialAccess.getOperation() == FileOperation.WRITE){
      return permission.getWritePermission();
    }
    if(requestedCredentialAccess.getOperation() == FileOperation.DELETE){
      return permission.getDeletePermission();
    }
    if(requestedCredentialAccess.getOperation() == FileOperation.READ_ACL){
      return permission.getReadAclPermission();
    }
    if(requestedCredentialAccess.getOperation() == FileOperation.WRITE_ACL){
      return permission.getWriteAclPermission();
    }

    return false;
  }

  /**
   * Template method called during permission check that allows subclasses to check access permissions.
   *
   * @param requestedFile Path
   * @return true if the RequestedFileAccess is granted
   */
  protected abstract boolean accessIsAllowed(Path requestedFile);
}

