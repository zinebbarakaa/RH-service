package com.giantLink.RH.models.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String role;
    @JsonProperty("access_Token")
    private String accessToken;
    @JsonProperty("refresh_Token")
    private String refreshToken;

}