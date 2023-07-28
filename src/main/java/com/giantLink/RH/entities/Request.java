package com.giantLink.RH.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd-MM-yyyy")
    protected Date requestDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    protected Employee employee;
    private Date updatedAt;
    private Date createdAt;


    @OneToOne
    @JoinColumn(name = "request_status_id")
    @JsonBackReference
    protected RequestStatus status;

    @PrePersist
    private void onCreate() {
        createdAt = new Date();
        this.requestDate = new Date();
    }
    @PreUpdate
    void setUpdatedAtField(){
        updatedAt = new Date();
    }

}
