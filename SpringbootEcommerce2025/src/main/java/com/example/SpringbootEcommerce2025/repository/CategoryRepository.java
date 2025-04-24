package com.example.SpringbootEcommerce2025.repository;

import com.example.SpringbootEcommerce2025.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
