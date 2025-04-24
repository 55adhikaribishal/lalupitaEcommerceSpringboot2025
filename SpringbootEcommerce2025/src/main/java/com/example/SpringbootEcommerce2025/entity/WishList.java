package com.example.SpringbootEcommerce2025.entity;

import com.example.SpringbootEcommerce2025.dto.WishListDto;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="product_id", nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY,optional=false)
    @JoinColumn(name="user_id", nullable=false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private User user;

    public WishList() {
    }

    public WishListDto getWishListDto(){
        WishListDto dto= new WishListDto();

        dto.setId(id);
        dto.setProductId(product.getId());
        dto.setProductName(product.getName());
        dto.setReturnedImg(product.getImg());
        dto.setProductDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setUserId(user.getId());

        return dto;
    }
    public WishList(Long id, Product product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
