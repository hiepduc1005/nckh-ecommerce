package com.ecommerce.vn.service.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.ecommerce.vn.entity.product.Product;

public interface ProductService {

    Product createProduct(Product product);

    Product updateProduct(UUID productId, Product productUpdate);

    Product findProductByUuid(UUID productId);

    void deleteProduct(UUID productId);

    List<Product> searchProducts(String keyword);

    // Lọc sản phẩm theo danh mục, giá, và các thuộc tính khác
    List<Product> filterProducts(UUID category, BigDecimal minPrice, BigDecimal maxPrice, String keyword);

    // Phân trang và sắp xếp sản phẩm
    Page<Product> getProductsWithPaginationAndSorting(int page, int size, String sortBy);

    Product getProductById(UUID productId);

    // List<Review> getProductReviews(UUID productId);
    
    // Review addReview(UUID productId, Review review);

    // // Quản lý danh mục sản phẩm
    // List<Category> getAllCategories();
    
    // Category createCategory(Category category);

    // Quản lý khuyến mãi
    // Discount applyDiscount(UUID productId, Discount discount);

    // Xử lý hình ảnh sản phẩm
    Product addProductImage(UUID productId, String imageUrl);

    // // Sản phẩm yêu thích
    // List<Product> getWishlist(UUID userId);
    
    // void addToWishlist(UUID userId, UUID productId);
    
    // void removeFromWishlist(UUID userId, UUID productId);

    // Sản phẩm liên quan
    // List<Product> getRelatedProducts(UUID productId);
}
