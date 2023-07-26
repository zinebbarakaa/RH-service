package com.giantLink.RH.models.request;

import com.giantLink.RH.entities.Request;
import com.giantLink.RH.enums.State;
import jakarta.persistence.*;

public class RequestStatusRequest {
    private State statusName;
    private String messageDetails;
    private int requestId;
}
