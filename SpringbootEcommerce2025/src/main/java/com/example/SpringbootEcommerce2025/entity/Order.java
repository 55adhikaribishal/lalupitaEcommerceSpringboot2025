package com.example.SpringbootEcommerce2025.entity;

import com.example.SpringbootEcommerce2025.dto.OrderDto;
import com.example.SpringbootEcommerce2025.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="orders")
public class Order {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String orderDescription;
    private BigDecimal amount;
    private String address;
    private String payment;

    //@Enumerated(EnumType.STRING) to convert 1,2,3 to string in database
    private OrderStatus orderStatus;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private UUID trackingId;

    private Date date;
    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToMany(fetch=FetchType.LAZY,mappedBy="order")
    @JsonIgnore
    private List<CartItems> cartItems;

    public Order() {
    }

    public Order(Long id, String orderDescription, BigDecimal amount, String address, String payment, OrderStatus orderStatus, BigDecimal totalAmount, BigDecimal discount, UUID trackingId, Date date, User user, Coupon coupon, List<CartItems> cartItems) {
        this.id = id;
        this.orderDescription = orderDescription;
        this.amount = amount;
        this.address = address;
        this.payment = payment;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.trackingId = trackingId;
        this.date = date;
        this.user = user;
        this.coupon = coupon;
        this.cartItems = cartItems;
    }

    public OrderDto getOrderDto(){
        OrderDto orderDto=new OrderDto();

        orderDto.setId(id);
        orderDto.setOrderDescription(orderDescription);
        orderDto.setAddress(address);
        orderDto.setTrackingId(trackingId);
        orderDto.setAmount(amount);
        orderDto.setDate(date);
        orderDto.setOrderStatus(orderStatus);
        orderDto.setUserName(user.getName());

        if(coupon !=null){
            orderDto.setCouponName(coupon.getName());
        }
        return orderDto;
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

    public UUID getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(UUID trackingId) {
        this.trackingId = trackingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}
