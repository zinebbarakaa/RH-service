package com.giantLink.RH.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApprovedLeave extends Holiday {

    @ManyToOne
    @JsonManagedReference
    private Employee employee;
    private String Reason;
}
