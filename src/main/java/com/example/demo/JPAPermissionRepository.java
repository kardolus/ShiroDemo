package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JPAPermissionRepository extends JpaRepository<Permission, UUID> {
  List<Permission> findByUsername(String username);
}
