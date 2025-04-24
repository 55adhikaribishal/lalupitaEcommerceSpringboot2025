package com.example.SpringbootEcommerce2025.repository;

import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    User findByRole(UserRole userRole);
}
