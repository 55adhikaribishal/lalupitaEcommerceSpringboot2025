package com.example.SpringbootEcommerce2025.services.customer.order;

import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.entity.Order;

import java.util.Optional;

public interface CustomerOrderService {
    OrderDto findById(Long orderId);
    Order confirmOrderPayment(Long orderId);

}
