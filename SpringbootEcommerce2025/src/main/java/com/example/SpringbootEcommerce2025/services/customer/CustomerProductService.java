package com.example.SpringbootEcommerce2025.services.customer;

import com.example.SpringbootEcommerce2025.dto.ProductDetailDto;
import com.example.SpringbootEcommerce2025.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> getAllProducts();
    List<ProductDto>  searchProductByTitle(String name);
    ProductDetailDto getProductDetailById(Long productId);

}
