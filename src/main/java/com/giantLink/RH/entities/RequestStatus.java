package com.giantLink.RH.entities;

import com.giantLink.RH.enums.State;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private State statusName; // Use the specific enum class here, not 'Enum'
    private String messageDetails;
    @OneToOne
    @JoinColumn(name = "request_id")
    private Request request;
}
