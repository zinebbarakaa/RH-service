package com.giantLink.RH.models.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionRequest {

    @NotBlank
    private String namePermission;
}
