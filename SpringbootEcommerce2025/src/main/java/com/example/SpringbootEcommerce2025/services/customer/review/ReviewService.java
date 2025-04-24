package com.example.SpringbootEcommerce2025.services.customer.review;

import com.example.SpringbootEcommerce2025.dto.OrderedProductResponseDto;
import com.example.SpringbootEcommerce2025.dto.ReviewDto;
import com.example.SpringbootEcommerce2025.repository.OrderRepository;

import java.io.IOException;

public interface ReviewService {
    OrderedProductResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
    boolean giveReview(ReviewDto reviewDto) throws IOException;
}
