package com.ecommerce.vn.service.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.ecommerce.vn.entity.coupon.Coupon;
import com.ecommerce.vn.entity.order.Order;
import com.ecommerce.vn.entity.order.OrderStatus;

public interface OrderService {
    Order createOrder(Order order);

    Order getOrderById(UUID orderId);

    List<Order> getAllOrders();

    Order updateOrder(UUID orderId, Order updatedOrder);

    void deleteOrder(UUID orderId);

    Order updateOrderStatus(UUID orderId, OrderStatus orderStatus);

    Order addCouponToOrder(UUID orderId, Coupon coupon);

    BigDecimal calculateTotalAmount(UUID orderId);

    Order addNotes(UUID orderId, String notes);
}
