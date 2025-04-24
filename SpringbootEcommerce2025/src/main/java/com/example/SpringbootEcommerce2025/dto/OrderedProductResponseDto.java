package com.example.SpringbootEcommerce2025.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderedProductResponseDto {
    private List<ProductDto> productDtoList;

    private BigDecimal orderAmount;

    public OrderedProductResponseDto() {
    }

    public OrderedProductResponseDto(List<ProductDto> productDtoList, BigDecimal orderAmount) {
        this.productDtoList = productDtoList;
        this.orderAmount = orderAmount;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public  void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList=productDtoList;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}
