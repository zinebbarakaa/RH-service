package com.giantLink.RH.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Request
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "dd-MM-yyyy")
    protected Date requestDate;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    protected Employee employee;

    @OneToOne
    @JoinColumn(name = "requestStatus_id")
    @JsonBackReference
    protected RequestStatus status;

    @PrePersist
    private void onCreate() { this.requestDate = new Date(); }
}
