package com.ecommerce.vn.service.seller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.vn.entity.seller.Seller;
import com.ecommerce.vn.exception.ResourceAlreadyExistException;
import com.ecommerce.vn.exception.ResourceNotFoundException;
import com.ecommerce.vn.repository.SellerRepository;
import com.ecommerce.vn.service.seller.SellerService;

@Service


public class SellerServiceImpl implements SellerService{

   @Autowired
   private SellerRepository sellerRepository;

@Override
public Seller createSeller(Seller seller) {
    if(findSellerByUuid(seller.getId()) != null) {
			throw new ResourceAlreadyExistException("Seller","id", seller.getId());
		}

    return sellerRepository.save(seller);

    // throw new UnsupportedOperationException("Unimplemented method 'createSeller'");
}

@Override
public Seller updateSeller(Seller sellerUpdate) {

    try{
        findSellerByUuid(sellerUpdate.getId());

        return sellerRepository.save(sellerUpdate);
    }catch(Exception e) {
        throw new UnsupportedOperationException("Unimplemented method 'updateSeller'");
    }
}

@Override
public Seller findSellerByUuid(UUID sellerId) {
    return sellerRepository.findById(sellerId)
    .orElseThrow( () -> new ResourceNotFoundException("Seller", "sellerId", sellerId));
}

@Override
public Seller findSellerByShopName(String shopname) {
   return sellerRepository.findByShopName(shopname)
   .orElseThrow(() -> new ResourceNotFoundException("Seller", "shopname", shopname));
}

@Override
public void deleteSellerByUuid(UUID sellerId) {
    try {
        findSellerByUuid(sellerId);

        sellerRepository.deleteById(sellerId);
    }catch(Exception e){
        throw new ResourceNotFoundException("Seller", "id", sellerId);
    }
}

@Override
public List<Seller> findAllSeller() {
    return sellerRepository.findAll();
}

   
}
