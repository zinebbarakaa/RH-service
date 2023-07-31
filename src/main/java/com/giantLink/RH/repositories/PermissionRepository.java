package com.giantLink.RH.repositories;

import com.giantLink.RH.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission,Long> {
    Permission findByNamePermission(String namePermission);
}