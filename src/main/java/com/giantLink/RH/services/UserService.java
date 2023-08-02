package com.giantLink.RH.services;

import com.giantLink.RH.entities.Employee;
import com.giantLink.RH.entities.User;
import com.giantLink.RH.models.request.LoginRequest;
import com.giantLink.RH.models.request.RegisterRequest;
import com.giantLink.RH.models.request.UpdateProfileRequest;
import com.giantLink.RH.models.response.LoginResponse;
import com.giantLink.RH.models.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(RegisterRequest request);
    List<LoginResponse> login(LoginRequest request);


    User getAuthenticatedUser();
    //User updateProfile(User updatedUser);
    void updateUserProfile(Employee employee, UpdateProfileRequest updateRequest);
    User findByUserName(String username);
}