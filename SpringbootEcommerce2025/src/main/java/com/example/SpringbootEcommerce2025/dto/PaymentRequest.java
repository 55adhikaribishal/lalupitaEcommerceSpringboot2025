package com.example.SpringbootEcommerce2025.dto;

import lombok.Data;

import java.math.BigDecimal;

//@Data
public class PaymentRequest {
    private String productName;
    private Long amount;
    private Long orderId;
    private String customerEmail;


    //convert the bigdecimal value to long as stripes takes cents value only
    public void setAmount(BigDecimal amountInDollars){
        this.amount=amountInDollars.multiply(new BigDecimal(100)).longValue();
    }

    public PaymentRequest() {
    }

    public PaymentRequest(String productName, Long amount, Long orderId, String customerEmail) {
        this.productName = productName;
        this.amount = amount;
        this.orderId = orderId;
        this.customerEmail = customerEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getAmount() {
        return amount;
    }

//    public void setAmount(Long amount) {
//        this.amount = amount;
//    }


//    public void setAmount(Long amount) {
//        this.amount = amount;
//    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Override
    public String toString() {
        return "PaymentRequest{" +
                "productName='" + productName + '\'' +
                ", amount=" + amount +
                ", orderId=" + orderId +
                ", customerEmail='" + customerEmail + '\'' +
                '}';
    }
}

