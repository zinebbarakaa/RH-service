package com.giantLink.RH.controllers;

import com.giantLink.RH.models.request.RoleRequest;
import com.giantLink.RH.models.response.RoleResponse;
import com.giantLink.RH.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> add (@RequestBody RoleRequest request){
        return  new ResponseEntity<>(roleService.add(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return new ResponseEntity<>(roleService.get(),HttpStatus.OK);
    }
}
