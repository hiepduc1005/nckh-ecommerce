package com.ecommerce.vn.service.coupon.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import com.ecommerce.vn.entity.coupon.Coupon;
import com.ecommerce.vn.entity.seller.Seller;
import com.ecommerce.vn.exception.ResourceNotFoundException;
import com.ecommerce.vn.repository.CouponRepository;
import com.ecommerce.vn.repository.SellerRepository;
import com.ecommerce.vn.service.coupon.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

    SellerRepository sellerRepository;
    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    @Transactional
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    @Transactional
    public Coupon updateCoupon(UUID couponId, Coupon updatedCoupon) {
        Coupon existingCoupon = couponRepository.findById(couponId)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));
        
        // Cập nhật các trường của coupon
        existingCoupon.setCode(updatedCoupon.getCode());
        existingCoupon.setCoupondDescription(updatedCoupon.getCoupondDescription());
        existingCoupon.setDiscountValue(updatedCoupon.getDiscountValue());
        existingCoupon.setDiscountType(updatedCoupon.getDiscountType());
        existingCoupon.setCouponType(updatedCoupon.getCouponType());
        existingCoupon.setMinimumOrderAmount(updatedCoupon.getMinimumOrderAmount());
        existingCoupon.setMaxUsage(updatedCoupon.getMaxUsage());
        existingCoupon.setCouponStartDate(updatedCoupon.getCouponStartDate());
        existingCoupon.setCouponEndDate(updatedCoupon.getCouponEndDate());
        existingCoupon.setUpdateAt(updatedCoupon.getUpdateAt());
        existingCoupon.setUpdatedBy(updatedCoupon.getUpdatedBy());
        
        return couponRepository.save(existingCoupon);
    }

    @Override
    @Transactional
    public void deleteCoupon(UUID couponId) {
        Coupon coupon = couponRepository.findById(couponId)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));
        
        couponRepository.delete(coupon);
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(UUID couponId) {
        return couponRepository.findById(couponId)
            .orElseThrow(() -> new ResourceNotFoundException("Coupon", "couponId", couponId));
    }

    @Override
    @Transactional
    public Coupon createCouponForSeller(UUID sellerId, Coupon coupon) {
       Seller seller = sellerRepository.findById(sellerId)
            .orElseThrow(() -> new ResourceNotFoundException("Seller", "sellerId", sellerId));
    coupon.setSeller(seller); 

    coupon.setCreatedBy(sellerId);
    
        return couponRepository.save(coupon);
    }
}

