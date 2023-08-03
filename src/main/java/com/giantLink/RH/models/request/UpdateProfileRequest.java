package com.giantLink.RH.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String cin;
}
