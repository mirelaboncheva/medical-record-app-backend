package com.mirela.medicalrecordapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "doctor",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_uid"})
        }
)
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String doctorUid;

    @Column(nullable = false)
    private String specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<GeneralPractitioner> personalPatients;
}