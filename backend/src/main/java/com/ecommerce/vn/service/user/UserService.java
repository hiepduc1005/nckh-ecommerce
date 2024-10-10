package com.ecommerce.vn.service.user;

import java.util.UUID;

import com.ecommerce.vn.entity.user.User;

public interface UserService {
	
	User findUserByUuId(UUID userId);
	
	User findUserByEmail(String email);
	
	User createUser(User user);
	
	User updateUser(User userUpdate);
	
	void deleteUser(UUID userId);
	
	User registerUser(String email,String username,String password);
	
	User loginUser(String email,String passowrd);
	

}
