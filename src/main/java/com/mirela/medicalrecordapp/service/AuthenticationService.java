package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.auth.*;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.enums.Role;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerPatient (RegisterRequest registerRequest){
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phoneNumber(registerRequest.getPhoneNumber())
                .role(Role.PATIENT)
                .build();

        var patient = Patient.builder()
                .nationalId(registerRequest.getNationalId())
                .user(user)
                .build();

        try {
            patientRepository.save(patient);
        } catch (Exception e) {
            System.err.println("Error saving patient: " + e.getMessage());
            throw new RuntimeException("Failed to register patient");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerDoctor (DoctorRegisterRequest doctorRegisterRequest){
        if (userRepository.existsByEmail(doctorRegisterRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        var user = User.builder()
                .firstName(doctorRegisterRequest.getFirstName())
                .lastName(doctorRegisterRequest.getLastName())
                .email(doctorRegisterRequest.getEmail())
                .password(passwordEncoder.encode(doctorRegisterRequest.getPassword()))
                .phoneNumber(doctorRegisterRequest.getPhoneNumber())
                .role(Role.DOCTOR)
                .build();

        var doctor = Doctor.builder()
                .doctorUid(doctorRegisterRequest.getDoctorUid())
                .specialization(doctorRegisterRequest.getSpecialization())
                .user(user)
                .build();

        try {
            doctorRepository.save(doctor);
        } catch (Exception e) {
            System.err.println("Error saving doctor: " + e.getMessage());
            throw new RuntimeException("Failed to register doctor");
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
