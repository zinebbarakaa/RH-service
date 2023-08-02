package com.giantLink.RH.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PermissionResponse {

    private Long id;
    private String namePermission;
}
