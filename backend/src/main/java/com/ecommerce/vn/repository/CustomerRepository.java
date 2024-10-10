package com.ecommerce.vn.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecommerce.vn.entity.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByUserId(UUID userId); 

    // Phương thức tìm Customer bằng User ID và Email
    @Query("SELECT c FROM Customer c WHERE c.user.id = :userId AND c.user.email = :email")
    Optional<Customer> findByUserIdAndEmail(@Param("userId") UUID userId, @Param("email") String email);

}
