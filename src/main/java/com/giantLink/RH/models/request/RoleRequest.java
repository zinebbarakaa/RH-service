package com.giantLink.RH.models.request;

import com.giantLink.RH.entities.Permission;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RoleRequest {

    @NotBlank
    private String roleName;
    private List<Permission> permissions ;

}
