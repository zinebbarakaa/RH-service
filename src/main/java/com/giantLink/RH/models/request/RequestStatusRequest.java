package com.giantLink.RH.models.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestStatusRequest
{
    @NotBlank(message = "Type cannot be blank")
    @Pattern(regexp = "^(Pending|Accepted|Refused)$", message = "Invalid type. It must be one of 'Pending', 'Accepted', or 'Refused'")
    private String type;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

}
