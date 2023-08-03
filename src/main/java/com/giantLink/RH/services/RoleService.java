package com.giantLink.RH.services;

import com.giantLink.RH.entities.Permission;
import com.giantLink.RH.entities.Role;
import com.giantLink.RH.models.request.RoleRequest;
import com.giantLink.RH.models.response.RoleResponse;

public interface RoleService extends  CrudService<RoleRequest, RoleResponse, Role,Long>{

    RoleResponse addPermissionToRole(Long role_Id,Long permission_id);
    RoleResponse deletePermissionToRole(Long role_Id,Long permission_id);

}
