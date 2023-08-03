package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.models.request.PermissionRequest;
import com.giantLink.RH.models.request.RoleRequest;
import com.giantLink.RH.models.response.PermissionResponse;
import com.giantLink.RH.models.response.RoleResponse;
import com.giantLink.RH.services.PermissionService;
import com.giantLink.RH.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequestMapping("/api/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<PermissionResponse> add (@RequestBody PermissionRequest request){
        return  new ResponseEntity<>(permissionService.add(request), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<List<PermissionResponse>> getAllRoles(){
        return new ResponseEntity<>(permissionService.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<PermissionResponse> update(@RequestBody PermissionRequest request, @PathVariable Long id){
        return  new ResponseEntity<>(permissionService.update(request,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public  ResponseEntity<String> deleteRole(@PathVariable Long id){
        permissionService.delete(id);
        return  new ResponseEntity<>("Permission Deleted",HttpStatus.NO_CONTENT);
    }
}
