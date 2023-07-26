package com.giantLink.RH.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolidayBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int balance;
    private Date timestamp;
    @PrePersist
    private void onCreate() {
        this.timestamp = new Date();
    }
}
