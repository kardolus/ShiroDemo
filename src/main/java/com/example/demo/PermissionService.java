package com.example.demo;

import org.apache.shiro.authz.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionService {
  private JPAPermissionRepository jpaPermissionRepository;

  @Autowired
  public PermissionService(JPAPermissionRepository jpaPermissionRepository) {
    this.jpaPermissionRepository = jpaPermissionRepository;
  }

  public List<Permission> forUsername(String username) {
    List<Permission> result = new ArrayList<>();
    for (com.example.demo.Permission permission : jpaPermissionRepository.findByUsername(username)) {
      String path = permission.getPath();
      if (path.endsWith("*")) {
        result.add(new PermissionForAllDescendantCredentials(path.substring(0, path.length()-1), permission.getOperation()));
      } else {
        result.add(new PermissionForTheCredentialItself(path, permission.getOperation()));
      }
    }
    return result;
  }
}
