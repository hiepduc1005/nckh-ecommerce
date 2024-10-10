package com.ecommerce.vn.service.product.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ecommerce.vn.entity.product.Product;
import com.ecommerce.vn.exception.ResourceNotFoundException;
import com.ecommerce.vn.repository.ProductRepository;
import com.ecommerce.vn.service.product.ProductService;

public class ProductServiceImpl implements ProductService{
    // private static final Object ProductUpdate = null;
    ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        if(findProductByUuid(product.getId()) != null ){
            throw new ResourceNotFoundException("Product", "productId", product.getId());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(UUID productId, Product productUpdate) {
        try {
			findProductByUuid(productUpdate.getId());
			
			return productRepository.save(productUpdate);
		}catch (Exception e) {
			throw new ResourceNotFoundException("Product", "productId", productUpdate.getId());
		}
    }

    @Override
    public void deleteProduct(UUID productId) {
      try {
        findProductByUuid(productId);

        productRepository.deleteById(productId);
      }catch(Exception e){
            throw new ResourceNotFoundException("Product", "productId", productId);
      }
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByKeywordProducts(keyword); 
    }

    @Override
    public Page<Product> getProductsWithPaginationAndSorting(int page, int size, String sortBy) {
       Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    
       return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId)
        .orElseThrow( () -> new ResourceNotFoundException("Product", "productId", productId));
    }

    @Override
    public Product addProductImage(UUID productId, String imageUrl) {
        Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));

    if (product.getImagePath() == null || product.getImagePath().isEmpty()) {
        product.setImagePath(imageUrl);
    } else {
        product.setImagePath(product.getImagePath() + "," + imageUrl);
    }

    return productRepository.save(product);
    }

    // @Override
    // public List<Product> getWishlist(UUID userId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getWishlist'");
    // }

    // @Override
    // public void addToWishlist(UUID userId, UUID productId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'addToWishlist'");
    // }

    // @Override
    // public void removeFromWishlist(UUID userId, UUID productId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'removeFromWishlist'");
    // }

    // @Override
    // public List<Product> getRelatedProducts(UUID productId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getRelatedProducts'");
    // }

    @Override
    public Product findProductByUuid(UUID productId) {
        return productRepository.findById(productId)
        .orElseThrow( () -> new ResourceNotFoundException("Product", "productId", productId));
    }

    @Override
    public List<Product> filterProducts(UUID category, BigDecimal minPrice, BigDecimal maxPrice, String keyword) {
       return productRepository.filterProducts(category, minPrice, maxPrice, keyword);
    }
}
