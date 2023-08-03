package com.giantLink.RH.models.response;

import com.giantLink.RH.entities.Permission;
import lombok.Builder;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
@Data
@Builder
public class RoleResponse {

    private Long id;
    private String roleName;

    private List<Permission> permissions ;

}
