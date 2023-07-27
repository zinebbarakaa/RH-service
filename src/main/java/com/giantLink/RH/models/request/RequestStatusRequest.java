package com.giantLink.RH.models.request;

//<<<<<<< HEAD
//import com.giantLink.RH.entities.Request;
//import com.giantLink.RH.enums.State;
//import jakarta.persistence.*;
//
//public class RequestStatusRequest {
//    private State statusName;
//
//    private int requestId;
//=======

import com.giantLink.RH.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RequestStatusRequest {
    @NotBlank(message = "Type cannot be blank")
    private State type;
    @Size(max = 200, message = "Description cannot exceed 200 characters")

    private String messageDetails;
    private int requestId;

}
