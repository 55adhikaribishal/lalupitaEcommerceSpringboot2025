package com.example.SpringbootEcommerce2025.controller.admin;

import com.example.SpringbootEcommerce2025.dto.CatgeoryDto;
import com.example.SpringbootEcommerce2025.entity.Category;
import com.example.SpringbootEcommerce2025.services.admin.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminCategoreyController {

    private final CategoryService categoryService;

    public AdminCategoreyController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody CatgeoryDto categoryDto){
        Category category=categoryService.createCategory(categoryDto);
        System.out.println("User making POST /category: " + SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
