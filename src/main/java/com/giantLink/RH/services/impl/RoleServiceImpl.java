package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.exceptions.ResourceDuplicatedException;
import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.mappers.PermissionMapper;
import com.giantLink.RH.mappers.RoleMapper;
import com.giantLink.RH.models.request.PermissionRequest;
import com.giantLink.RH.models.request.RoleRequest;
import com.giantLink.RH.models.response.PermissionResponse;
import com.giantLink.RH.models.response.RoleResponse;
import com.giantLink.RH.repositories.PermissionRepository;
import com.giantLink.RH.repositories.RoleRepository;
import com.giantLink.RH.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl  implements RoleService {
    private  final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    @Override
    public RoleResponse add(RoleRequest request) {
        Optional<Role> findRole = roleRepository.findByRoleName(request.getRoleName());
        if (findRole.isPresent()) {
            throw new ResourceDuplicatedException("Role", "id", findRole.get().getId().toString());
        }
        Role role = findRole.get();
        role.setRoleName(request.getRoleName());
        role.setPermissions(request.getPermissions());
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }

    @Override
    public List<RoleResponse> get() {
        return RoleMapper.INSTANCE.listToResponseList(roleRepository.findAll());
    }

    @Override
    public RoleResponse update(RoleRequest request, Long id) {
        Optional<Role> findRole = roleRepository.findById(id);
        if(!findRole.isPresent()){
            throw  new ResourceNotFoundException("role","id",id.toString());
        }
        Role role = findRole.get();
        role.setRoleName(request.getRoleName());
        role.setPermissions(request.getPermissions());
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("role", "id", id.toString()));

        roleRepository.delete(role);

    }

    @Override
    public RoleResponse get(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("role", "id", id.toString()));
        return RoleMapper.INSTANCE.entityToResponse(roleRepository.findById(id).get());
    }

    @Override
    public RoleResponse addPermissionToRole(Long role_Id, Long permission_id) {
        Role role = roleRepository.findById(role_Id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", role_Id.toString()));
        Permission permission = permissionRepository.findById(permission_id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", "id", permission_id.toString()));
        role.getPermissions().add(permission);
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }

    @Override
    public RoleResponse deletePermissionToRole(Long role_Id, Long permission_id) {
        Role role = roleRepository.findById(role_Id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", role_Id.toString()));
        Permission permission = permissionRepository.findById(permission_id)
                .orElseThrow(() -> new ResourceNotFoundException("Permission", "id", permission_id.toString()));
        role.getPermissions().remove(permission);
        roleRepository.save(role);
        return RoleMapper.INSTANCE.entityToResponse(role);
    }
}
