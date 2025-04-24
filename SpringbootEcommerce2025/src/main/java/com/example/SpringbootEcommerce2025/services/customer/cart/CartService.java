package com.example.SpringbootEcommerce2025.services.customer.cart;

import com.example.SpringbootEcommerce2025.dto.AddProductInCartDto;
import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
    OrderDto getCartByUserId(Long userId);
    OrderDto applyCoupon(Long userId, String code);
    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
    OrderDto placeorder(PlaceOrderDto placeOrderDto);
    List<OrderDto> getMyPlacedOrder(Long userId);
    OrderDto searchOrderByTrackingId(UUID trackingId);
    void confirmPayment(Long orderId);
}
