package com.example.SpringbootEcommerce2025.controller.admin;

import com.example.SpringbootEcommerce2025.dto.FAQDto;
import com.example.SpringbootEcommerce2025.dto.ProductDto;
import com.example.SpringbootEcommerce2025.services.admin.FAQ.FAQService;
import com.example.SpringbootEcommerce2025.services.admin.product.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor
public class AdminProductController {
    private final AdminProductService adminProductService;

    private final FAQService faqService;
    public AdminProductController(AdminProductService adminProductService, FAQService faqService) {
        this.adminProductService = adminProductService;
        this.faqService = faqService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1=adminProductService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos=adminProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> productDtos=adminProductService.getAllProductsByName(name);
        return ResponseEntity.ok(productDtos);
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        boolean deleted= adminProductService.deleteProduct(productId);
        System.out.println("User making delete request: " + SecurityContextHolder.getContext().getAuthentication());
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/faq/{productId}")
    public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId,@RequestBody FAQDto faqDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId,faqDto));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
        ProductDto productDto=adminProductService.getProductById(productId);
        if(productDto != null){
            return ResponseEntity.ok(productDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,
                                                    @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto updatedProduct=adminProductService.updateProduct(productId,productDto);
        if(updatedProduct !=null){
            return ResponseEntity.ok(updatedProduct);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
