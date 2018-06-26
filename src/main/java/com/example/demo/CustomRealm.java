package com.example.demo;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Collections;
import java.util.Set;

@Component
public class CustomRealm extends JdbcRealm {


  private PermissionService permissionRepository;

  public CustomRealm(PermissionService permissionRepository, DataSource dataSource) {
    this.permissionRepository = permissionRepository;
    this.setDataSource(dataSource);
  }

  @Override
  protected Set<String> getRoleNamesForUser(Connection conn, String username) {
    return Collections.singleton(username);
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    if (principals == null) {
      throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
    }

    String username = (String) getAvailablePrincipal(principals);

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    info.addObjectPermissions(permissionRepository.forUsername(username));
    return info;
  }
}
