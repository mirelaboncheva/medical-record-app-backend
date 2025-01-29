package com.mirela.medicalrecordapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"national_id"})
        }
)
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String nationalId;

    @Column
    private Boolean isHealthInsurancePaid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "patient")
    private DoctorPatientAssignment DoctorPatientAssignment;
}