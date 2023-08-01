package com.giantLink.RH.controllers;

import com.giantLink.RH.entities.User;
import com.giantLink.RH.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees/")
@RequiredArgsConstructor
public class ProfileController {
    private UserService userService;

    @PutMapping("/update-profile")
    public ResponseEntity<User> updateProfile(@RequestBody User updatedUser) {
        User updatedUser1 = userService.updateProfile(updatedUser);
        return new ResponseEntity<>(updatedUser1, HttpStatus.OK);
    }
}
