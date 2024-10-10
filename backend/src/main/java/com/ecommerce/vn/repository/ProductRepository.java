package com.ecommerce.vn.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.vn.entity.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{

    @Query(value = "SELECT * FROM product p WHERE p.product_name ILIKE %:keyword% OR p.description ILIKE %:keyword% OR p.short_description ILIKE %:keyword%", nativeQuery = true)
    List<Product> findByKeywordProducts(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE (:category IS NULL OR p.category = :category) AND (:minPrice IS NULL OR p.price >= :minPrice) AND (:maxPrice IS NULL OR p.price <= :maxPrice) AND (p.name LIKE %:keyword% OR p.description LIKE %:keyword% OR p.shortDescription LIKE %:keyword%)")
List<Product> filterProducts(@Param("category") UUID category, 
                             @Param("minPrice") BigDecimal minPrice, 
                             @Param("maxPrice") BigDecimal maxPrice, 
                             @Param("keyword") String keyword);



}
