package com.example.SpringbootEcommerce2025.dto;

import lombok.Data;

@Data
public class UserProfileDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private String returnedImg; // For display on frontend

}
