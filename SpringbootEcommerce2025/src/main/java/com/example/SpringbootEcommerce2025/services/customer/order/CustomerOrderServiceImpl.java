package com.example.SpringbootEcommerce2025.services.customer.order;

import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.entity.Order;
import com.example.SpringbootEcommerce2025.enums.OrderStatus;
import com.example.SpringbootEcommerce2025.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService{
    private final OrderRepository orderRepository;

    public CustomerOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDto findById(Long orderId){
        Optional<Order> orderOptional=orderRepository.findById(orderId);
        return orderOptional.map(Order::getOrderDto).orElse(null);
    }

    @Override
    public Order confirmOrderPayment(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }

        Order order = optionalOrder.get();

        // Example: set the order status to 'CONFIRMED' or 'PAID'
        order.setOrderStatus(OrderStatus.PLACED);  // You can use Enum if you have it
        return orderRepository.save(order);

       // return order;

    }
}
