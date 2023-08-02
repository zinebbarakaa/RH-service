package com.giantLink.RH.models.response;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {

    private Long id;
    private String username;
    private Employee employee;
    private List<Role> roles;

}
