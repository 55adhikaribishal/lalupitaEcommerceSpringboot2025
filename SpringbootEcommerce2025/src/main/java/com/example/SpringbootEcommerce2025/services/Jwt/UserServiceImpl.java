package com.example.SpringbootEcommerce2025.services.Jwt;

import com.example.SpringbootEcommerce2025.dto.UserProfileDto;
import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public UserProfileDto getProfile(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        UserProfileDto dto = new UserProfileDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole().name());
        if (user.getImg() != null) {
            dto.setReturnedImg(Base64.getEncoder().encodeToString(user.getImg()));
        }

        return dto;
    }

    public UserProfileDto updateProfile(String email, UserProfileDto updatedDto, MultipartFile image) throws IOException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updatedDto.getName());
        user.setPhoneNumber(updatedDto.getPhoneNumber());
        if (image != null && !image.isEmpty()) {
            user.setImg(image.getBytes());
        }

        userRepository.save(user);
        return getProfile(email); // Return updated
    }
}
