package com.example.SpringbootEcommerce2025.services.admin.category.coupon;

import com.example.SpringbootEcommerce2025.entity.Coupon;
import com.example.SpringbootEcommerce2025.exceptions.ValidationException;
import com.example.SpringbootEcommerce2025.repository.CouponRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminCouponServiceImpl implements AdminCouponService{
    private final CouponRepository couponRepository;

    public AdminCouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon createCoupon(Coupon coupon){
        if(couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }
}
