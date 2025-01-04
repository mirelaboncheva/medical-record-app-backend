package com.mirela.medicalrecordapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirela.medicalrecordapp.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class Patient{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String nationalId;

    @Size(max = 25)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean isHealthInsurancePaid;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "patient")
    private GeneralPractitioner generalPractitioner;
}