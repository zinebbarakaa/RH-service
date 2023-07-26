package com.giantLink.RH.models.response;

import com.giantLink.RH.entities.Request;
import com.giantLink.RH.enums.State;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestStatusResponse {
    private Long id;
    private State statusName;
    private String messageDetails;
    private Request request;
}
