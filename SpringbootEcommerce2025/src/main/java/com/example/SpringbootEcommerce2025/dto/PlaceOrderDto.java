package com.example.SpringbootEcommerce2025.dto;

public class PlaceOrderDto {
    private Long userId;
    private String address;
    private String orderDescription;

    public PlaceOrderDto() {
    }

    public PlaceOrderDto(Long userId, String address, String orderDescription) {
        this.userId = userId;
        this.address = address;
        this.orderDescription = orderDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
}
