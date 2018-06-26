package com.example.demo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Startup implements ApplicationListener<ApplicationReadyEvent> {
  @Autowired
  private Realm realm;

  @Override
  public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
    DefaultSecurityManager securityManager = new DefaultSecurityManager(realm);
    SecurityUtils.setSecurityManager(securityManager);
    DelegatingSubject currentUser = new DelegatingSubject(securityManager);
    currentUser.login(new UsernamePasswordToken("user1", "password1"));

    System.out.println("*** USER 1 ***");
    System.out.println("!!!!!!!!!!" + currentUser.isAuthenticated());
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password", FileOperation.DELETE))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password", FileOperation.READ))); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password", FileOperation.WRITE))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/foo/bar/baz", FileOperation.READ))); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/notauthorized", FileOperation.READ))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/foo/bar/baz", FileOperation.WRITE))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password/", FileOperation.READ))); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/passwordsdfsd", FileOperation.READ))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password/foo", FileOperation.READ))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password////", FileOperation.READ))); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("foo/bar/baz", FileOperation.READ))); // false

    currentUser.login(new UsernamePasswordToken("user2", "password2"));
    System.out.println("** USER 2 **");
    System.out.println("!!!!!!!!!!" + currentUser.isAuthenticated()); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password", FileOperation.READ))); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/foo/bar/baz", FileOperation.READ))); // false
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password", FileOperation.WRITE))); // true
    System.out.println(currentUser.isPermitted(new RequestedCredentialAccess("/password", FileOperation.DELETE))); // false
  }
}
