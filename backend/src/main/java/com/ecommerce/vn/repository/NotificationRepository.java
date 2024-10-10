package com.ecommerce.vn.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.vn.entity.customer.Customer;
import com.ecommerce.vn.entity.notification.Notification;
import com.ecommerce.vn.entity.seller.Seller;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,UUID>{
    List<Notification> findByCustomer(Customer customer);

    List<Notification> findBySeller(Seller seller);

    List<Notification> findByIsGlobal(boolean isGlobal);
}
