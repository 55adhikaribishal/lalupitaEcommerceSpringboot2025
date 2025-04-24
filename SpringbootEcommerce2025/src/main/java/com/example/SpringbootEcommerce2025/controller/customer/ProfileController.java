package com.example.SpringbootEcommerce2025.controller.customer;

import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public ResponseEntity<?> getCustomerProfile(){
        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOpt=userRepository.findByEmail(email);

        if(userOpt.isEmpty()){
            return ResponseEntity.badRequest().body("User not found");
        }

        User user= userOpt.get();
        Map<String, Object> profile= new HashMap<>();
        profile.put("id",user.getId());
        profile.put("name", user.getName());
        profile.put("email",user.getEmail());
        profile.put("role",user.getRole().name());//enum to string

        return ResponseEntity.ok(profile);
    }
}
