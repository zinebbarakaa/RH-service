package com.giantLink.RH.models.request;

import com.giantLink.RH.entities.Request;
import com.giantLink.RH.enums.State;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestStatusUpdateRequest {
    @NotNull(message = "Id cannot be Null")
    private Long id;
    @NotBlank(message = "Type cannot be blank")
    @Enumerated(EnumType.STRING)
    private State type;
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String messageDetails;

}
