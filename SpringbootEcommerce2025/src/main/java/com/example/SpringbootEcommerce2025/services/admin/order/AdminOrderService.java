package com.example.SpringbootEcommerce2025.services.admin.order;

import com.example.SpringbootEcommerce2025.dto.AnalyticsResponse;
import com.example.SpringbootEcommerce2025.dto.OrderDto;

import java.util.List;

public interface AdminOrderService {
    List<OrderDto> getAllPlacedOrder();
    OrderDto changeOrderStatus(Long orderId, String status);
    AnalyticsResponse calculateAnalytics();
}
