package com.giantLink.RH.services.impl;

import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.exceptions.ResourceDuplicatedException;
import com.giantLink.RH.exceptions.ResourceNotFoundException;
import com.giantLink.RH.mappers.PermissionMapper;
import com.giantLink.RH.models.request.PermissionRequest;
import com.giantLink.RH.models.response.PermissionResponse;
import com.giantLink.RH.repositories.PermissionRepository;
import com.giantLink.RH.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public PermissionResponse add(PermissionRequest request) {
        Optional<Permission> findPermission = permissionRepository.findByNamePermission(request.getNamePermission());
        if (findPermission.isPresent()) {
            throw new ResourceDuplicatedException("Permission", "id", findPermission.get().getId().toString());
        } else {
            Permission permission = new Permission();
            permission.setNamePermission(request.getNamePermission());
            permissionRepository.save(permission);
            return PermissionMapper.INSTANCE.entityToResponse(permission);
        }
    }

    @Override
    public List<PermissionResponse> get() {
        return PermissionMapper.INSTANCE.listToResponseList(permissionRepository.findAll());
    }

    @Override
    public PermissionResponse update(PermissionRequest request, Long id) {
        Optional<Permission> findPermission = permissionRepository.findById(id);
        if(!findPermission.isPresent()){
            throw  new ResourceNotFoundException("permission","id",id.toString());
        }
        Permission permission = findPermission.get();
        permission.setNamePermission(request.getNamePermission());
        permissionRepository.save(permission);
        return PermissionMapper.INSTANCE.entityToResponse(permission);
    }

    @Override
    public void delete(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("permission", "id", id.toString()));

        permissionRepository.delete(permission);

    }

    @Override
    public PermissionResponse get(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("permission", "id", id.toString()));
        return PermissionMapper.INSTANCE.entityToResponse(permissionRepository.findById(id).get());
    }
}
