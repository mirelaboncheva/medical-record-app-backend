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

    @Column(nullable = false)
    @NotNull
    private String firstName;

    @Column(nullable = false)
    @NotNull
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please provide a valid email address")
    private String email;

    private String specialization;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private List<GeneralPractitioner> personalPatients;
}