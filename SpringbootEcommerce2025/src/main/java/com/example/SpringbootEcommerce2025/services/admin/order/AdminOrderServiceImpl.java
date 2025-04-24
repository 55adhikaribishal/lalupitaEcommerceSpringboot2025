package com.example.SpringbootEcommerce2025.services.admin.order;

import com.example.SpringbootEcommerce2025.dto.AnalyticsResponse;
import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.entity.Order;
import com.example.SpringbootEcommerce2025.enums.OrderStatus;
import com.example.SpringbootEcommerce2025.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {
    private final OrderRepository orderRepository;

    public AdminOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> getAllPlacedOrder(){
        System.out.println("order placed here");
        List<Order> orderList= (List<Order>) orderRepository.findAllByOrderStatusIn(
                List.of(OrderStatus.PLACED,OrderStatus.SHIPPED,OrderStatus.DELIVERED)
        );

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
    }

    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder=orderRepository.findById(orderId);
        System.out.println(orderId+":Order id and status is:"+status);
        if(optionalOrder.isPresent()){
            Order order=optionalOrder.get();

            if(Objects.equals(status,"Shipped")){
                order.setOrderStatus(OrderStatus.SHIPPED);
                System.out.println("order status changed here shipped");
            }else{
                order.setOrderStatus(OrderStatus.DELIVERED);
                System.out.println("order status changed here delivered");
            }
            return orderRepository.save(order).getOrderDto();
        }
        return null;
    }

    public AnalyticsResponse calculateAnalytics(){
        LocalDate currentDate=LocalDate.now();
        LocalDate previousMonthDate= currentDate.minusMonths(1);

        Long currentMonthOrders=getTotalOrdersForMonth(currentDate.getMonthValue(),currentDate.getYear());
        Long previousMonthOrders= getTotalOrdersForMonth(previousMonthDate.getMonthValue(),previousMonthDate.getYear());

        BigDecimal currentMonthEarnings= getTotalEarningsForMonth(currentDate.getMonthValue(),currentDate.getYear());
        BigDecimal previousMonthEarnings=getTotalEarningsForMonth(previousMonthDate.getMonthValue(),previousMonthDate.getYear());

        Long placed= orderRepository.countByOrderStatus(OrderStatus.PLACED);
        Long shipped= orderRepository.countByOrderStatus(OrderStatus.SHIPPED);
        Long delivered=orderRepository.countByOrderStatus(OrderStatus.DELIVERED);

        return new AnalyticsResponse(placed,shipped,delivered, currentMonthOrders,
                previousMonthOrders, currentMonthEarnings, previousMonthEarnings);
    }

    public Long getTotalOrdersForMonth(int month,int year){
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1); //0-11

        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        Date startOfMonth=calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        Date endOfMonth=calendar.getTime();

        List<Order> orders=orderRepository.findByDateBetweenAndOrderStatus(startOfMonth,endOfMonth,OrderStatus.DELIVERED);

        return (long) orders.size();

    }

    public BigDecimal getTotalEarningsForMonth(int month,int year){
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1); //0-11

        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        Date startOfMonth=calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        Date endOfMonth=calendar.getTime();

        List<Order> orders=orderRepository.findByDateBetweenAndOrderStatus(startOfMonth,endOfMonth,OrderStatus.DELIVERED);

        BigDecimal sum=BigDecimal.ZERO;

        for(Order order: orders){
           // sum+=order.getAmount();
            sum=sum.add(order.getAmount());
        }
        return sum;

    }

}
