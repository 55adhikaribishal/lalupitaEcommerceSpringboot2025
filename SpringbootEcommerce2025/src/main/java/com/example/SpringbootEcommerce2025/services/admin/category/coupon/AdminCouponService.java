package com.example.SpringbootEcommerce2025.services.admin.category.coupon;

import com.example.SpringbootEcommerce2025.entity.Coupon;

import java.util.List;

public interface AdminCouponService {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
