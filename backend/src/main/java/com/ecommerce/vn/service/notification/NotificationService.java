package com.ecommerce.vn.service.notification;

import java.util.List;

import com.ecommerce.vn.entity.customer.Customer;
import com.ecommerce.vn.entity.notification.Notification;
import com.ecommerce.vn.entity.seller.Seller;

public interface NotificationService {
    
    void notifyCustomer(Customer customer, String message);

    void notifySeller(Seller seller, String message);

    void notifyAllUsers(String message);

    List<Notification> getCustomerNotifications(Customer customer);

    List<Notification> getSellerNotifications(Seller seller);

}
