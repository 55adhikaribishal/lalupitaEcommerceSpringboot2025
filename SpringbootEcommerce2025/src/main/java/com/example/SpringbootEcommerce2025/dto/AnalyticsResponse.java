package com.example.SpringbootEcommerce2025.dto;

import java.math.BigDecimal;

public class AnalyticsResponse {
    private Long placed;

    private Long shipped;

    private Long delivered;

    private Long currentMonthOrders;

    private Long PreviousMonthOrders;

    private BigDecimal currentMonthEarnings;

    private BigDecimal previousMonthEarnings;

    public AnalyticsResponse() {
    }

    public AnalyticsResponse(Long placed, Long shipped, Long delivered, Long currentMonthOrders, Long previousMonthOrders, BigDecimal currentMonthEarnings, BigDecimal previousMonthEarnings) {
        this.placed = placed;
        this.shipped = shipped;
        this.delivered = delivered;
        this.currentMonthOrders = currentMonthOrders;
        PreviousMonthOrders = previousMonthOrders;
        this.currentMonthEarnings = currentMonthEarnings;
        this.previousMonthEarnings = previousMonthEarnings;
    }

    public Long getPlaced() {
        return placed;
    }

    public void setPlaced(Long placed) {
        this.placed = placed;
    }

    public Long getShipped() {
        return shipped;
    }

    public void setShipped(Long shipped) {
        this.shipped = shipped;
    }

    public Long getDelivered() {
        return delivered;
    }

    public void setDelivered(Long delivered) {
        this.delivered = delivered;
    }

    public Long getCurrentMonthOrders() {
        return currentMonthOrders;
    }

    public void setCurrentMonthOrders(Long currentMonthOrders) {
        this.currentMonthOrders = currentMonthOrders;
    }

    public Long getPreviousMonthOrders() {
        return PreviousMonthOrders;
    }

    public void setPreviousMonthOrders(Long previousMonthOrders) {
        PreviousMonthOrders = previousMonthOrders;
    }

    public BigDecimal getCurrentMonthEarnings() {
        return currentMonthEarnings;
    }

    public void setCurrentMonthEarnings(BigDecimal currentMonthEarnings) {
        this.currentMonthEarnings = currentMonthEarnings;
    }

    public BigDecimal getPreviousMonthEarnings() {
        return previousMonthEarnings;
    }

    public void setPreviousMonthEarnings(BigDecimal previousMonthEarnings) {
        this.previousMonthEarnings = previousMonthEarnings;
    }
}
