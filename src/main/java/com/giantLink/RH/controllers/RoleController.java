package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.RoleRequest;
import com.giantLink.RH.models.response.RoleResponse;
import com.giantLink.RH.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('SUPER_ADMIN')")
@RequestMapping("api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<RoleResponse> add (@RequestBody RoleRequest request){
        return  new ResponseEntity<>(roleService.add(request), HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return new ResponseEntity<>(roleService.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<RoleResponse> update(@RequestBody RoleRequest request,@PathVariable Long id){
        return  new ResponseEntity<>(roleService.update(request,id),HttpStatus.OK);
    }

    @PostMapping("add-permission-to-role/{idRole}/{idPermission}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public ResponseEntity<RoleResponse> addPermissionToRole(@PathVariable Long idRole, @PathVariable Long idPermission){
        return  new ResponseEntity<>(roleService.addPermissionToRole(idRole,idPermission),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public  ResponseEntity<String> deleteRole(@PathVariable Long id){
        roleService.delete(id);
        return  new ResponseEntity<>("Role Deleted",HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("remove-permission-from-role/{idRole}/{idPermission}")
    @PreAuthorize("hasAuthority('SUPER_ADMIN_CREATE')")
    public  ResponseEntity<RoleResponse > deletePermissionFromRole(@PathVariable Long idRole,@PathVariable Long idPermission){
        return  new ResponseEntity<>(roleService.deletePermissionToRole(idRole,idPermission),HttpStatus.OK);
    }
}
