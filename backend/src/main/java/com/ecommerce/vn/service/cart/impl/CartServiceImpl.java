package com.ecommerce.vn.service.cart.impl;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.vn.entity.cart.Cart;
import com.ecommerce.vn.entity.cart.CartItem;
import com.ecommerce.vn.entity.user.User;
import com.ecommerce.vn.repository.CartItemRepository;
import com.ecommerce.vn.repository.CartRepository;
import com.ecommerce.vn.repository.UserRepository;
import com.ecommerce.vn.service.cart.CartService;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;  

    @Autowired
    private UserRepository userRepository;  

    @Autowired
    private CartItemRepository cartItemRepository;  

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUserId(UUID userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            return cartRepository.findByUserId(userId);
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public void addItemToCart(UUID cartId, CartItem cartItem) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            cartItem.setCart(cart);
            cartItemRepository.save(cartItem);
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    @Override
    public void removeItemFromCart(UUID cartId, UUID cartItemId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        Optional<CartItem> cartItemOpt = cartItemRepository.findById(cartItemId);

        if (cartOpt.isPresent() && cartItemOpt.isPresent()) {
            cartItemRepository.delete(cartItemOpt.get());
        } else {
            throw new RuntimeException("Cart or CartItem not found");
        }
    }

    @Override
    public void clearCart(UUID cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            cartItemRepository.deleteAll(cart.getCartItems());
        } else {
            throw new RuntimeException("Cart not found");
        }
    }

    @Override
    public Set<CartItem> getCartItems(UUID cartId) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            return cartOpt.get().getCartItems();
        }
        throw new RuntimeException("Cart not found");
    }
}

