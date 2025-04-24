package com.example.SpringbootEcommerce2025.dto;

public class AddProductInCartDto {
    private Long userId;
    private Long productId;

    public AddProductInCartDto() {
    }

    public AddProductInCartDto(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
