package com.giantLink.RH.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid",strategy = "uuid2")
    private String id;

    private String fileName;
    private String fileType;
    @Column(name="data",columnDefinition = "LONGBLOB")
    private byte[] data;

    public Attachment(String fileName, String contentType, byte[] bytes) {
    }
    @ManyToOne
    @JoinColumn(name = "employeeId")
    @JsonBackReference
    private Employee employee;

}
