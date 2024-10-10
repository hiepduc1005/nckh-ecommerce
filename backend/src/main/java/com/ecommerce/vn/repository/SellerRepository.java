package com.ecommerce.vn.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.vn.entity.seller.Seller;


@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID>{
    Optional<Seller> findByShopName(String shopName);
} 
