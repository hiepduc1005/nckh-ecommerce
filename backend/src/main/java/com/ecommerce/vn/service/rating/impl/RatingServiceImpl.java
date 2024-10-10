package com.ecommerce.vn.service.rating.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.vn.exception.ResourceNotFoundException;
import com.ecommerce.vn.entity.customer.Customer;
import com.ecommerce.vn.entity.product.Product;
import com.ecommerce.vn.entity.rating.Rating;
import com.ecommerce.vn.entity.seller.Seller;
import com.ecommerce.vn.repository.CustomerRepository;
import com.ecommerce.vn.repository.ProductRepository;
import com.ecommerce.vn.repository.RatingRepository;
import com.ecommerce.vn.repository.SellerRepository;
import com.ecommerce.vn.service.rating.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Rating createRating(UUID productId, UUID sellerId, UUID customerId, Integer ratingValue, String comment) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        
        Seller seller = sellerRepository.findById(sellerId)
            .orElseThrow(() -> new ResourceNotFoundException("Seller", "id", sellerId));
        
        Customer customer = customerRepository.findById(customerId)
            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setSeller(seller);
        rating.setCustomer(customer);
        rating.setRatingValue(ratingValue);
        rating.setComment(comment);
        rating.setIsVerified(true); 

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(UUID ratingId, Integer ratingValue, String comment) {
        Rating rating = ratingRepository.findById(ratingId)
            .orElseThrow(() -> new ResourceNotFoundException("Rating", "id", ratingId));

        rating.setRatingValue(ratingValue);
        rating.setComment(comment);
        rating.setUpdatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(UUID ratingId) {
        Rating rating = ratingRepository.findById(ratingId)
            .orElseThrow(() -> new ResourceNotFoundException("Rating", "id", ratingId));
        
        ratingRepository.delete(rating);
    }

    @Override
    public Rating getRatingById(UUID ratingId) {
        return ratingRepository.findById(ratingId)
            .orElseThrow(() -> new ResourceNotFoundException("Rating", "id", ratingId));
    }

    @Override
    public List<Rating> getRatingsByProduct(UUID productId) {
        return ratingRepository.findByProductId(productId);
    }

    @Override
    public List<Rating> getRatingsByCustomer(UUID customerId) {
        return ratingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Rating> getRatingsBySeller(UUID sellerId) {
        return ratingRepository.findBySellerId(sellerId);
    }
}
