package com.example.SpringbootEcommerce2025.controller.customer;

import com.example.SpringbootEcommerce2025.dto.UserProfileDto;
import com.example.SpringbootEcommerce2025.services.Jwt.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getProfile(Principal principal){
        return ResponseEntity.ok(userService.getProfile(principal.getName()));
    }

    @PutMapping("/update-profile")
    public ResponseEntity<UserProfileDto> updateProfile(@RequestPart("update-profile") UserProfileDto userProfileDto,
          @RequestPart(value="img",required=false) MultipartFile image,
           Principal principal) throws Exception{
        String email=principal.getName();//used to get the current logged in email using spring security/jwt
        return ResponseEntity.ok(userService.updateProfile(principal.getName(),
                userProfileDto,image));
    }
}
