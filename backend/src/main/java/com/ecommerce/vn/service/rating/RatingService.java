package com.ecommerce.vn.service.rating;

import java.util.List;
import java.util.UUID;

import com.ecommerce.vn.entity.rating.Rating;

public interface RatingService {

    Rating createRating(UUID productId, UUID sellerId, UUID customerId, Integer ratingValue, String comment);

    Rating updateRating(UUID ratingId, Integer ratingValue, String comment);

    void deleteRating(UUID ratingId);

    Rating getRatingById(UUID ratingId);

    List<Rating> getRatingsByProduct(UUID productId);

    List<Rating> getRatingsByCustomer(UUID customerId);

    List<Rating> getRatingsBySeller(UUID sellerId);
}