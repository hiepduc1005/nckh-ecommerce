package com.ecommerce.vn.service.customer.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.vn.entity.customer.Customer;
import com.ecommerce.vn.entity.order.Order;
import com.ecommerce.vn.exception.ResourceNotFoundException;
import com.ecommerce.vn.repository.CustomerRepository;
import com.ecommerce.vn.service.customer.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository; 

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Order> getCustomerOrders(UUID customerId, String email) {
        Customer customer = getCustomerById(customerId,null,email);
        return new ArrayList<>(customer.getOrders()); // Trả về danh sách đơn hàng
    }

    @Override
    public void addLoyaltyPoints(UUID customerId, int points, String email) {
        Customer customer = getCustomerById(customerId,null,email);
        customer.setLoyaltyPoint(customer.getLoyaltyPoint() + points);
        customerRepository.save(customer); 
    }

    @Override
    public void redeemLoyaltyPoints(UUID customerId, int points, String email) {
        // Đổi điểm thưởng cho khách hàng
        Customer customer = getCustomerById(customerId, null, email);
        if (customer.getLoyaltyPoint() < points) {
            throw new IllegalArgumentException("Not enough loyalty points"); 
        }
        customer.setLoyaltyPoint(customer.getLoyaltyPoint() - points);
        customerRepository.save(customer); 
    }

    @Override
    public Customer getCustomerById(UUID customerId, UUID userId, String email) {
         return customerRepository.findByUserIdAndEmail(userId, email)
         .orElseThrow(() -> new ResourceNotFoundException("Customer", "userId or email", userId));
    }

}
