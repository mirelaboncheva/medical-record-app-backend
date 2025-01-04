package com.mirela.medicalrecordapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirela.medicalrecordapp.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(
        name = "doctor",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_uid", "phone_number", "email"})
        }
)
@Getter
@Setter
public class Doctor extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private String doctorUid;

    private String phoneNumber;

    private String specialization;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<GeneralPractitioner> personalPatients;
}