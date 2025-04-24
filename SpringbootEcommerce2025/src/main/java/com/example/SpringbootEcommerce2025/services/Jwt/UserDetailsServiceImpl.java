package com.example.SpringbootEcommerce2025.services.Jwt;

import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.enums.UserRole;
import com.example.SpringbootEcommerce2025.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("Username not found", null);
        User user = optionalUser.get();
        String role = user.getRole() == UserRole.ROLE_CUSTOMER ? "ROLE_CUSTOMER" : "ROLE_ADMIN";
        System.out.println("Authenticated user role: " + role);
        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),
                //optionalUser.get().getPassword(),new ArrayList<>());
                // optionalUser.get().getPassword(), Collections.singletonList(new SimpleGrantedAuthority(role)));
                optionalUser.get().getPassword(), new ArrayList<>(List.of(new SimpleGrantedAuthority(role))));
        //return null;
    }
}
