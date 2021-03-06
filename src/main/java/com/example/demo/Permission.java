package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@IdClass(CompositeKey.class)
public class Permission {
  @Id
  private String path;

  @Id
  private String username;

  private Boolean readPermission;
  private Boolean writePermission;
  private Boolean deletePermission;
  private Boolean readAclPermission;
  private Boolean writeAclPermission;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Boolean getReadPermission() {
    return readPermission;
  }

  public void setReadPermission(Boolean readPermission) {
    this.readPermission = readPermission;
  }

  public Boolean getWritePermission() {
    return writePermission;
  }

  public void setWritePermission(Boolean writePermission) {
    this.writePermission = writePermission;
  }

  public Boolean getDeletePermission() {
    return deletePermission;
  }

  public void setDeletePermission(Boolean deletePermission) {
    this.deletePermission = deletePermission;
  }

  public Boolean getReadAclPermission() {
    return readAclPermission;
  }

  public void setReadAclPermission(Boolean readAclPermission) {
    this.readAclPermission = readAclPermission;
  }

  public Boolean getWriteAclPermission() {
    return writeAclPermission;
  }

  public void setWriteAclPermission(Boolean writeAclPermission) {
    this.writeAclPermission = writeAclPermission;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}

