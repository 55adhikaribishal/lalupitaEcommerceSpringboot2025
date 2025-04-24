package com.example.SpringbootEcommerce2025.dto;

import com.example.SpringbootEcommerce2025.entity.CartItems;
import com.example.SpringbootEcommerce2025.entity.Coupon;
import com.example.SpringbootEcommerce2025.entity.User;
import com.example.SpringbootEcommerce2025.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderDto {
    private Long id;

    private String orderDescription;
    private BigDecimal amount;
    private String address;
    private String payment;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private UUID trackingId;

    private String userName;

    private Date date;

    private String couponName;
    private List<CartItemsDto> cartItems;

    public OrderDto() {
    }

    public OrderDto(Long id, String orderDescription, BigDecimal amount, String address, String payment, OrderStatus orderStatus, BigDecimal totalAmount, BigDecimal discount, UUID trackingId, String userName, Date date, String couponName, List<CartItemsDto> cartItems) {
        this.id = id;
        this.orderDescription = orderDescription;
        this.amount = amount;
        this.address = address;
        this.payment = payment;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.trackingId = trackingId;
        this.userName = userName;
        this.date = date;
        this.couponName = couponName;
        this.cartItems = cartItems;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }


    public UUID getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CartItemsDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsDto> cartItems) {
        this.cartItems = cartItems;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
