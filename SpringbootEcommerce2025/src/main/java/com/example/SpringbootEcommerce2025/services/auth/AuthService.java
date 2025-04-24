package com.example.SpringbootEcommerce2025.services.auth;

import com.example.SpringbootEcommerce2025.dto.SignupRequest;
import com.example.SpringbootEcommerce2025.dto.UserDto;

public interface AuthService {

    boolean hasUserWithEmail(String email);

    UserDto createUser(SignupRequest signupRequest);
}
