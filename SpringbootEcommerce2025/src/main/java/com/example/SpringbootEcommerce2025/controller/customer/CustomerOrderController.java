package com.example.SpringbootEcommerce2025.controller.customer;

import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.entity.Order;
import com.example.SpringbootEcommerce2025.repository.OrderRepository;
import com.example.SpringbootEcommerce2025.services.customer.order.CustomerOrderService;
import com.stripe.service.climate.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerOrderController {
    private final CustomerOrderService customerOrderServiceorderService;

    public CustomerOrderController(CustomerOrderService customerOrderServiceorderService) {
        this.customerOrderServiceorderService = customerOrderServiceorderService;
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId){
        OrderDto orderDto=customerOrderServiceorderService.findById(orderId);
        if(orderDto !=null){
            return ResponseEntity.ok(orderDto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
