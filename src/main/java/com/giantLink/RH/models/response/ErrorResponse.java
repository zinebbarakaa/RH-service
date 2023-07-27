package com.giantLink.RH.models.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private int errorCode;
    private String errorMessage;
    private String errorDetails;

   
}
