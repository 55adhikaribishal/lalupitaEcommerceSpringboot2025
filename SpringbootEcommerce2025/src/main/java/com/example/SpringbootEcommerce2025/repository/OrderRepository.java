package com.example.SpringbootEcommerce2025.repository;

import com.example.SpringbootEcommerce2025.entity.Order;
import com.example.SpringbootEcommerce2025.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);
    List<Order> findAllByUserIdAndOrderStatusIn(Long userId,List<OrderStatus> orderStatusList);

    Optional<Order> findByTrackingId(UUID trackingId);

    Long countByOrderStatus(OrderStatus orderStatus);

    List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);
}
