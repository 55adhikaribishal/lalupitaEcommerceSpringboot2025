package com.example.SpringbootEcommerce2025.dto;

import java.math.BigDecimal;

public class CartItemsDto {
    private Long id;

    private BigDecimal price;

    private Long quantity;
    private Long productId;
    private Long orderId;
    private String productName;
    private byte[] returnedImg;
    private Long userId;

    public CartItemsDto() {
    }

    public CartItemsDto(Long id, BigDecimal price, Long quantity, Long productId, Long orderId, String productName, byte[] returnedImg, Long userId) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.productId = productId;
        this.orderId = orderId;
        this.productName = productName;
        this.returnedImg = returnedImg;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getReturnedImg() {
        return returnedImg;
    }

    public void setReturnedImg(byte[] returnedImg) {
        this.returnedImg = returnedImg;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
