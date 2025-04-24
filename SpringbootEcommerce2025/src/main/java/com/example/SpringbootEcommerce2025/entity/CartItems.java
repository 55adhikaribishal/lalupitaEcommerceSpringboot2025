package com.example.SpringbootEcommerce2025.entity;

import com.example.SpringbootEcommerce2025.dto.CartItemsDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(
        name="cart_items",
        uniqueConstraints={
                @UniqueConstraint(columnNames={"user_id","product_id","order_id"})
        }
)
public class CartItems {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;

    private Long quantity;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="product_id",nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="user_id",nullable=false)
    @OnDelete(action=OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne (fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="order_id",nullable=false)
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Order order;

    public CartItems() {
    }

    public CartItems(Long id, BigDecimal price, Long quantity, Product product, User user, Order order) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.product = product;
        this.user = user;
        this.order = order;
    }

    public CartItemsDto getCartDto(){
        CartItemsDto cartItemsDto=new CartItemsDto();
        cartItemsDto.setId(id);
        cartItemsDto.setPrice(price);
        cartItemsDto.setProductId(product.getId());
        cartItemsDto.setQuantity(quantity);
        cartItemsDto.setUserId(user.getId());
        cartItemsDto.setProductName(product.getName());
        cartItemsDto.setReturnedImg(product.getImg());

        return cartItemsDto;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
