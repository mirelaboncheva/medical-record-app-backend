package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.UserRequest;
import com.mirela.medicalrecordapp.dto.UserResponse;
import com.mirela.medicalrecordapp.dto.UserUpdateRequest;
import com.mirela.medicalrecordapp.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse getUserById(Long id);
    UserResponse getUserByUsername(String email);
    User saveUser(UserRequest userRequest);
    User updateUser(Long id, UserUpdateRequest userUpdateRequest);
    void deleteUser(Long id);
}
