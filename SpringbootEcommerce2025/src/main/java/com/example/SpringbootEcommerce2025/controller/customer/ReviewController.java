package com.example.SpringbootEcommerce2025.controller.customer;

import com.example.SpringbootEcommerce2025.dto.OrderedProductResponseDto;
import com.example.SpringbootEcommerce2025.dto.ReviewDto;
import com.example.SpringbootEcommerce2025.services.customer.review.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/customer")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }



    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        Boolean success= reviewService.giveReview(reviewDto);

        if(!success) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong while giving review");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderedProductResponseDto> getOrderedProductsDetailsByOrderId(
            @PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
    }

}
