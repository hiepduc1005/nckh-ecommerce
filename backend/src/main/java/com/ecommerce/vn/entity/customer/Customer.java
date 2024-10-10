package com.ecommerce.vn.entity.customer;

import java.util.Set;
import java.util.UUID;

import com.ecommerce.vn.entity.order.Order;
import com.ecommerce.vn.entity.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "loyalty_points")
	private Integer loyaltyPoint;

	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getLoyaltyPoint() {
		return loyaltyPoint;
	}

	public void setLoyaltyPoint(Integer loyaltyPoint) {
		this.loyaltyPoint = loyaltyPoint;
	}

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
	
	
}
