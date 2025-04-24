package com.example.SpringbootEcommerce2025.controller.admin;

import com.example.SpringbootEcommerce2025.dto.AnalyticsResponse;
import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.services.admin.order.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
//@RequiredArgsConstructor
public class AdminOrderController {

    @Autowired
    private final AdminOrderService adminOrderService;

    public AdminOrderController(AdminOrderService adminOrderService){
        this.adminOrderService=adminOrderService;
    }
    @GetMapping("/placedOrders")
    public ResponseEntity<List<OrderDto>> getAllPlacedOrders(){
        return ResponseEntity.ok(adminOrderService.getAllPlacedOrder());
    }

    @GetMapping("/order/{orderId}/{status}")
    public ResponseEntity<?> changeOrderStatus(@PathVariable Long orderId, @PathVariable String status){

        OrderDto orderDto=adminOrderService.changeOrderStatus(orderId, status);
        if(orderDto ==null){
            return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/order/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics(){
        return ResponseEntity.ok(adminOrderService.calculateAnalytics());
    }
}
