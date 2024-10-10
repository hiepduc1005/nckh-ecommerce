package com.ecommerce.vn.service.notification.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.vn.entity.customer.Customer;
import com.ecommerce.vn.entity.notification.Notification;
import com.ecommerce.vn.entity.seller.Seller;
import com.ecommerce.vn.repository.NotificationRepository;
import com.ecommerce.vn.service.notification.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void notifyCustomer(Customer customer, String message) {
        Notification notification = new Notification();
        notification.setCustomer(customer); // Gửi thông báo đến Customer
        notification.setMessage(message);
        notificationRepository.save(notification);
    }

    @Override
    public void notifySeller(Seller seller, String message) {
        Notification notification = new Notification();
        notification.setSeller(seller); // Gửi thông báo đến Seller
        notification.setMessage(message);
        notificationRepository.save(notification);
    }

    @Override
    public void notifyAllUsers(String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getCustomerNotifications(Customer customer) {
        return notificationRepository.findByCustomer(customer);
    }

    @Override
    public List<Notification> getSellerNotifications(Seller seller) {
        return notificationRepository.findBySeller(seller);
    }
}
