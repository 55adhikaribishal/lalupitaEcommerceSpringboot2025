package com.example.SpringbootEcommerce2025.repository;

import com.example.SpringbootEcommerce2025.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByProductId(Long productId);
}
