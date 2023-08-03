package com.giantLink.RH.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PredefinedHoliday extends Holiday {

    private String name;
    private String country;
    private String description;
}
