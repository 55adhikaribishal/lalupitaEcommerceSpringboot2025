package com.example.SpringbootEcommerce2025.controller.customer;

import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.services.customer.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController

public class TrackingController {

    private final CartService cartService;


    public TrackingController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOOrderByTrackingId(@PathVariable UUID trackingId){
        OrderDto orderDto=cartService.searchOrderByTrackingId(trackingId);

        if(orderDto==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDto);
    }
}
