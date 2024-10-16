package com.ecommerce.vn.service.coupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.ecommerce.vn.entity.coupon.Coupon;

public interface CouponService {
    
    Coupon createCoupon(Coupon coupon);

    Coupon updateCoupon(UUID couponId, Coupon updatedCoupon);

    void deleteCoupon(UUID couponId);

    List<Coupon> getAllCoupons();

    Coupon getCouponById(UUID couponId);
  
    Coupon createCouponForSeller(UUID sellerId, Coupon coupon);
    
    List<Coupon> searchCoupons(String keyword);

    List<Coupon> getCouponsBySellerId(UUID sellerId);

    boolean isCouponValid(UUID couponId, BigDecimal cartTotal);

    void incrementUsageCount(UUID couponId);
    
    Coupon validateAndRetrieveCouponByCode(String couponCode,BigDecimal totalPrice);
    
    Coupon validateAndRetrieveCouponById(UUID couponId,BigDecimal totalPrice);

    BigDecimal getTotalPriceAfterDiscount(UUID couponId,BigDecimal totalPrice);

}