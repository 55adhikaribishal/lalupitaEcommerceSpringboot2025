package com.example.SpringbootEcommerce2025.controller.admin;

import com.example.SpringbootEcommerce2025.entity.Coupon;
import com.example.SpringbootEcommerce2025.exceptions.ValidationException;
import com.example.SpringbootEcommerce2025.services.admin.category.coupon.AdminCouponService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/admin/coupons")
public class AdminCouponController {

    private final AdminCouponService adminCouponService;

    public AdminCouponController(AdminCouponService adminCouponService) {
        this.adminCouponService = adminCouponService;
    }

    @PostMapping
    public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon){
       try{
           Coupon createdCoupon =adminCouponService.createCoupon(coupon);
           return ResponseEntity.ok(createdCoupon);
       }catch(ValidationException ex){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
       }
    }

    @GetMapping
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        return ResponseEntity.ok(adminCouponService.getAllCoupons());
    }
}
