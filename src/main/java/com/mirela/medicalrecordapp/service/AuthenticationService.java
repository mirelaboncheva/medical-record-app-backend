package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.auth.AuthenticationRequest;
import com.mirela.medicalrecordapp.auth.AuthenticationResponse;
import com.mirela.medicalrecordapp.auth.RegisterRequest;
import com.mirela.medicalrecordapp.model.Role;
import com.mirela.medicalrecordapp.model.User;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Role role;
        if (
                request.getRole() == null
                        || (!request.getRole().equalsIgnoreCase("DOCTOR")
                        && !request.getRole().equalsIgnoreCase("PATIENT"))
        ) {
            throw new IllegalArgumentException("Invalid role specified");
        }

        role = Role.valueOf(request.getRole().toUpperCase());
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .build();

        userRepository.save(user);
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
