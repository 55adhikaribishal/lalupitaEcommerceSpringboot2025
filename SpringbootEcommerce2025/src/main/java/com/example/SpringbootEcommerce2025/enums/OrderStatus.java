package com.example.SpringbootEcommerce2025.enums;

public enum OrderStatus {
    PENDING,
    PLACED,
    SHIPPED,
    DELIVERED;

    @Override
    public String toString(){
        return name(); //ensure consisten String output
    }
}

