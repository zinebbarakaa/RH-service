package com.giantLink.RH.services;

import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.models.request.PermissionRequest;
import com.giantLink.RH.models.response.PermissionResponse;

public interface PermissionService extends CrudService<PermissionRequest, PermissionResponse, Permission,Long>{
}
