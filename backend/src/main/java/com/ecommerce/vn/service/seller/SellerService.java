package com.ecommerce.vn.service.seller;

import java.util.List;
import java.util.UUID;

import com.ecommerce.vn.entity.seller.Seller;

public interface SellerService {
	
	Seller createSeller(Seller seller);
	
	Seller updateSeller(Seller sellerUpdate);
	
	Seller findSellerByUuid(UUID sellerId);

	Seller findSellerByShopName(String shopname);

	void deleteSellerByUuid(UUID sellerId);

	List<Seller> findAllSeller();	
}
