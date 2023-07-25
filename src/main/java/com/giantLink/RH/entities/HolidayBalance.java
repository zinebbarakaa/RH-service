package com.giantLink.RH.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolidayBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private int balance = 0;

    @Column(nullable = false)
    @Builder.Default
    private Date timestamp = new Date();

    @Column(nullable = false)
    @Builder.Default
    private int holidayPerMonth = 2;

    private Date updatedAt;
    private Date createdAt;
    @PrePersist
    void setCreatedAtField(){
        createdAt = new Date();
    }
    @PreUpdate
    void setUpdatedAtField(){
        updatedAt = new Date();
    }
}
