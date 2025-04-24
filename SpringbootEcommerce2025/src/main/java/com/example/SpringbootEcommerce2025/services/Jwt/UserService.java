package com.example.SpringbootEcommerce2025.services.Jwt;

import com.example.SpringbootEcommerce2025.dto.UserProfileDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserProfileDto getProfile(String email);
    UserProfileDto updateProfile(String email, UserProfileDto updatedDto, MultipartFile image) throws IOException;
}
