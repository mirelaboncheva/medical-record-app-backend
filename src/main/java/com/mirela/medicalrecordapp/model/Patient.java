package com.mirela.medicalrecordapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
        name = "patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"national_id", "phone_number", "email"})
        }
)
@Getter
@Setter
public class Patient extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private String nationalId;

    @Column(nullable = false)
    @NotNull
    private String firstName;

    @Column(nullable = false)
    @NotNull
    private String lastName;

    private String phoneNumber;

    @Email(message = "Please provide a valid email address")
    private String email;

    private Boolean isHealthInsurancePaid;

    @JsonIgnore
    @OneToOne(mappedBy = "patient")
    private GeneralPractitioner generalPractitioner;
}
