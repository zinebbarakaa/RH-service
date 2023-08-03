package com.giantLink.RH.repositories;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.giantLink.RH.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
     Optional<Permission> findByNamePermission(String namePermission);
}