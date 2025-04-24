package com.example.SpringbootEcommerce2025.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WishListDto {
    private Long userId;
    private Long productId;

    private Long id;

    private String productName;

    private String productDescription;

    private byte[] returnedImg;
    private BigDecimal price;

    public WishListDto() {
    }

    public WishListDto(Long userId, Long productId, Long id, String productName, String productDescription, byte[] returnedImg, BigDecimal price) {
        this.userId = userId;
        this.productId = productId;
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.returnedImg = returnedImg;
        this.price = price;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public byte[] getReturnedImg() {
        return returnedImg;
    }

    public void setReturnedImg(byte[] returnedImg) {
        this.returnedImg = returnedImg;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
