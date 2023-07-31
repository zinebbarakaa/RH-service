package com.giantLink.RH.models.request;
import com.giantLink.RH.entities.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private Long id_employee;
    private List<Role> roles;
}


